package kr.or.iei.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.iei.board.model.dao.BoardDao;
import kr.or.iei.board.model.vo.Board;
import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.common.ListData;

public class BoardService {
	private BoardDao dao;
	
	public BoardService() {
		dao = new BoardDao();
	}
	
	
	public ListData<Board> selectBoardList(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		//한 페이지에 보여줄 게시글의 갯수
		int viewNoticeCnt = 10;
		
		//끝 행번호
		int end = reqPage * viewNoticeCnt;
		
		//시작 행번호
		int start = end - viewNoticeCnt + 1;
		
		ArrayList<Board> list = dao.selectBoardList(conn, start, end);
		
		//페이지네이션 작업 < 1 2 3 4 5 >
		
		//전체 게시글 갯수 조회(totCnt)
		int totCnt = dao.selectTotalCount(conn);
		
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
			pageNavi += "<a class='page-item' href='/board/list?reqPage=" + (pageNo - 1) +"'>";
			pageNavi += "<span class='material-icons'>이전</span>";
			pageNavi += "</a></li>";
		}
		
		
		for(int i=0; i<pageNaviSize; i++) {
			pageNavi += "<li>";
			
			//페이지 번호 작성중, 사용자가 요청한 페이지일 때, 클래스를 다르게 지정하여 시각적인 효과
			if(pageNo == reqPage) {
				pageNavi += "<a class='page-item active-page' href='/board/list?reqPage=" + pageNo +"'>";
			}else {
				pageNavi += "<a class='page-item' href='/board/list?reqPage=" + pageNo +"'>";
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
			pageNavi += "<a class='page-item' href='/board/list?reqPage=" + pageNo +"'>";
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


	public ListData<Board> SelectBoadrList2(int reqPage) {
		Connection conn = JDBCTemplate.getConnection();
		
		//한 페이지에 보여줄 게시글의 갯수
		int viewNoticeCnt = 10;
		
		//끝 행번호
		int end = reqPage * viewNoticeCnt;
		
		//시작 행번호
		int start = end - viewNoticeCnt + 1;
		
		ArrayList<Board> list = dao.selectBoardList(conn, start, end);
		
		//페이지네이션 작업 < 1 2 3 4 5 >
		
		//전체 게시글 갯수 조회(totCnt)
		int totCnt = dao.selectTotalCount(conn);
		
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
			pageNavi += "<a class='page-item' href='/board/list?reqPage=" + (pageNo - 1) +"'>";
			pageNavi += "<span class='material-icons'>이전</span>";
			pageNavi += "</a></li>";
		}
		
		
		for(int i=0; i<pageNaviSize; i++) {
			pageNavi += "<li>";
			
			//페이지 번호 작성중, 사용자가 요청한 페이지일 때, 클래스를 다르게 지정하여 시각적인 효과
			if(pageNo == reqPage) {
				pageNavi += "<a class='page-item active-page' href='/board/list?reqPage=" + pageNo +"'>";
			}else {
				pageNavi += "<a class='page-item' href='/board/list?reqPage=" + pageNo +"'>";
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
			pageNavi += "<a class='page-item' href='/board/list?reqPage=" + pageNo +"'>";
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

}
