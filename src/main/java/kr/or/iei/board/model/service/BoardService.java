package kr.or.iei.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.iei.board.model.dao.BoardDao;
import kr.or.iei.board.model.vo.Board;
import kr.or.iei.board.model.vo.BoardAdmin;
import kr.or.iei.board.model.vo.BoardFile;
import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.common.ListData;

public class BoardService {
	private BoardDao dao;
	
	public BoardService() {
		dao = new BoardDao();
	}
	
	//게시판 리스트
	public ListData<Board> selectBoardList(int reqPage, String gubun, String sortGubun) {
		Connection conn = JDBCTemplate.getConnection();
		
		//한 페이지에 보여줄 게시글의 갯수
		int viewNoticeCnt = 10;
		
		//끝 행번호
		int end = reqPage * viewNoticeCnt;
		
		//시작 행번호
		int start = end - viewNoticeCnt + 1;
		
		ArrayList<Board> list = dao.selectBoardList(conn, start, end, gubun, sortGubun);
		
		//페이지네이션 작업 < 1 2 3 4 5 >
		
		//전체 게시글 갯수 조회(totCnt)
		int totCnt = dao.selectTotalCount(conn, gubun);
		
		//전체 페이지수
		int totPage = 0;
		
		if(totCnt % viewNoticeCnt > 0) {
			totPage = totCnt / viewNoticeCnt + 1;
		}else {
			totPage = totCnt / viewNoticeCnt;
		}
		
		//페이지네이션 사이즈 1 2 3 4 5
		int pageNaviSize = 5;
		
		//페이지네이션의 시작 번호
		int pageNo = ((reqPage-1) / pageNaviSize) * pageNaviSize + 1;
		
		//페이지 하단에 보여줄 페이지네이션 HTML 코드 작성
		String pageNavi = "<ul class='pagination circle-style'>";
		
		//이전 버튼
		if(pageNo != 1) {
			pageNavi += "<li>";
			
			if(gubun.equals("B")) {
				pageNavi += "<a class='page-item' href='/board/list?reqPage=" + (pageNo - 1) +"'>";				
			}else if(gubun.equals("G")) {
				pageNavi += "<a class='page-item' href='/board/alist?reqPage=" + (pageNo - 1) +"'>";
			}
			pageNavi += "<span class='material-icons'>이전</span>";
			pageNavi += "</a></li>";
		}
		
		
		for(int i=0; i<pageNaviSize; i++) {
			pageNavi += "<li>";
			
			//페이지 번호 작성중, 사용자가 요청한 페이지일 때, 클래스를 다르게 지정하여 시각적인 효과
			if(pageNo == reqPage) {
				if(gubun.equals("B")) {
					pageNavi += "<a class='page-item active-page' href='/board/list?reqPage=" + pageNo +"'>";
				}else if(gubun.equals("G")) {
					pageNavi += "<a class='page-item active-page' href='/board/alist?reqPage=" + pageNo +"'>";
				}
			}else {
				if(gubun.equals("B")) {
					pageNavi += "<a class='page-item' href='/board/list?reqPage=" + pageNo +"'>";
				}else if(gubun.equals("G")) {
					pageNavi += "<a class='page-item' href='/board/alist?reqPage=" + pageNo +"'>";
				}
			}
			
			//시작태그와 종료태그 사이에 작성되는 값
			pageNavi += pageNo; 
			pageNavi += "</a></li>";
			
			pageNo++;
			
			//설정한 pageNaviSize만큼 항상 그리지 않고, 마지막 페이지 그렸으면 더 이상 생성하지 않도록 처리
			if(pageNo > totPage) { 
				break;
			}
		}	
			
		//다음 버튼
		if(pageNo <= totPage) {
			pageNavi += "<li>";
			if(gubun.equals("B")) {
				pageNavi += "<a class='page-item' href='/board/list?reqPage=" + pageNo +"'>";
			}else if(gubun.equals("G")) {
				pageNavi += "<a class='page-item' href='/board/alist?reqPage=" + pageNo +"'>";
			}
			
			pageNavi += "<span class='material-icons'>다음</span>";
			pageNavi += "</a></li>";
		}
		
		pageNavi += "</ul>";
		
		
		//서블릿으로 리턴해야 하는값 => 게시글 리스트와 페이지 하단에 보여줄 페이지네이션(pageNavi)
		
		ListData<Board> listData = new ListData<Board>();
		listData.setList(list);
		listData.setPageNavi(pageNavi);
		
		JDBCTemplate.close(conn);
		return listData;
			
	}

	//게시판 작성 (파일 포함)
	public int insertBoard(Board board, ArrayList<BoardFile> fileList) {
		Connection conn = JDBCTemplate.getConnection();
		
		//새로운 게시글 번호 추출
		String boardId = dao.selectBoardNo(conn);
		
		//게시글 번호 세팅
		board.setBoardId(boardId);
		
		int result = dao.insertBoard(conn, board);
				
		if(result > 0) {
			for(BoardFile file : fileList) {
				file.setFileTypeId(boardId);
				
				result = dao.insertBoardFile(conn, board.getBoardType(), file);
				
				if(result < 1) {
					JDBCTemplate.rollback(conn);
					JDBCTemplate.close(conn);
					return 0;
				}
			}
			JDBCTemplate.commit(conn);
			
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}


	public Board selectOneBoard(String boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		//1. 게시글 정보 조회
		Board oneB = dao.selectOneBoard(conn, boardNo);
		
		//2. 게시글에 대한 파일 정보 조회
		ArrayList<BoardFile> fileList = dao.selectBoardFileList(conn, boardNo);
		oneB.setFileList(fileList);
		
		JDBCTemplate.close(conn);
		return oneB;
	}



	public ArrayList<BoardFile> updateBoard(Board board, ArrayList<BoardFile> fileList, String[] delFileNoList) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		//게시글 수정
		//(1)게시글 정보 업데이트
		int result = dao.updateBoard(conn, board);
		
		ArrayList<BoardFile> preFileList = null;
		
		if(result > 0) {
			//(2)게시글에 대한 전체 파일 리스트 조회
			//리스트에서 삭제 대상 파일 정보만 남기고, remove
			//ex) 리스트에 저장된 파일 객체의 파일 번호 : 1, 2, 3, 4, 5
			preFileList = dao.selectBoardFileList(conn, board.getBoardId());
			
			
			if(delFileNoList != null) {
				String delFileNoStr = String.join("|", delFileNoList);
				//preFileList에서 삭제할 파일 정보만 남기고 remove
				//arraylist는 자동으로 길이가 조정되기때문에 뒤에서부터 처리
				for (int i=preFileList.size()-1; i>=0; i--) {
					String preFileNo = String.valueOf(preFileList.get(i).getFileNo());
					
					if(delFileNoStr.indexOf(preFileNo) > -1) {
						result += dao.deleteBoardFile(conn, preFileNo); //게시글에 대한 개별 파일 삭제
					}else {
						//기존 파일이 삭제 대상 파일이 아닐 때
						preFileList.remove(i); //서버에서 삭제되지 않도록 리스트에서 제거
					}
				}
			}
			for(int i=0; i<fileList.size(); i++) {
				BoardFile insFile = fileList.get(i);
				result += dao.insertBoardFile(conn, board.getBoardType(), insFile);
			}
			
		}
		//추가된 첨부파일수 + 1 : 추가된 첨부파일수 + 삭제된 첨부파일수 + 1
		int updTotalCnt = delFileNoList == null ? fileList.size() + 1 : fileList.size() + delFileNoList.length + 1;
		if(updTotalCnt == result) {
			JDBCTemplate.commit(conn);
			JDBCTemplate.close(conn);
			return preFileList;
			
		}else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
	
		
			return null;
		}
	}

	public ArrayList<BoardFile> deleteBoard(String boardId) {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<BoardFile> delFileList = dao.selectBoardFileList(conn, boardId);
		
		int result = dao.deleteBoard(conn, boardId);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
			JDBCTemplate.close(conn);
			return delFileList;
		}
		
		JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return null;
	}

/*
	public String updateListBoard(String boardId) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.updateListBoard(conn, boardId);
		
		
		if(result >0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return null;
	}


	*/


	
	
}
