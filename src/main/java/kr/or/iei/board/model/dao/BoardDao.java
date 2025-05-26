package kr.or.iei.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import kr.or.iei.board.model.vo.Board;
import kr.or.iei.board.model.vo.BoardComment;
import kr.or.iei.board.model.vo.BoardFile;
import kr.or.iei.common.JDBCTemplate;

public class BoardDao {
	
	//게시판 리스트
	public ArrayList<Board> selectBoardList(Connection conn, int start, int end, String gubun, String sortGubun) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Board> list = new ArrayList<Board>();
		
		//게시글 번호, 제목, 작성자아이디, 작성일, 조회수, 좋아요수 select 
		String query = "select board_id, board_title, member_id, created_at, view_count, board_like_count from (select rownum rnum, A.* FROM (SELECT * FROM TBL_board a where board_type = ? ORDER BY created_at "+sortGubun+",  VIEW_COUNT "+sortGubun+", BOARD_LIKE_COUNT "+sortGubun+") A ) WHERE RNUM >=? AND RNUM <=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			
			//gubun == G == 공지사항
			//gubun == B == 자유게시판
			pstmt.setString(1, gubun);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board b = new Board();
				b.setBoardId(rset.getString("board_id")); 
				b.setBoardTitle(rset.getString("board_title"));
				b.setMemberId(rset.getString("member_id"));
				b.setCreatedAt(rset.getString("created_at"));
				b.setViewCount(rset.getInt("view_count"));
				b.setBoardLikeCount(rset.getInt("board_like_count"));
				
				list.add(b);
			} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	
	//게시글 전체글수 조회
	public int selectTotalCount(Connection conn, String gubun) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int totCnt = 0;
		
		String query = "select count(*) as cnt from tbl_board where board_type = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gubun);
			rset = pstmt.executeQuery();
			rset.next();
			totCnt = rset.getInt("cnt");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return totCnt;
	}


	
		
	//게시글 번호 생성
	public String selectBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String boardNo = "";
		
		//게시글 번호 생성
		String query = "select to_char(sysdate, 'yymmdd') || lpad(seq_board.nextval, 5, '0') board_no from dual";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				boardNo = rset.getString("board_no");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return boardNo;
	}
	
	
	//조회수
	public int boardViewCount(Connection conn, String boardId) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = "update tbl_board set read_count = read_count + 1 where board_id = ?";
		
		
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, boardId);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}

		return result;
	}
	
	
	//게시글 작성
	public int insertBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;

		int result = 0;
		
		String query = "insert into tbl_board values (?, ?, ?, ?, ?,sysdate, sysdate ,default, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setString(1, board.getBoardId());
			pstmt.setString(2, board.getBoardType());
			pstmt.setString(3, board.getMemberId());
			pstmt.setString(4, board.getBoardTitle());
			pstmt.setString(5, board.getBoardContent());
			pstmt.setInt(6, board.getBoardLikeCount());
			
			result = pstmt.executeUpdate();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}

				
		return result;
	}
	
	//파일 첨부
	public int insertBoardFile(Connection conn, String boardType ,BoardFile file) {
		PreparedStatement pstmt =null;
		
			int result = 0;
			
			
			String query ="insert into tbl_file values(seq_tbl_file_no.nextval, ?, ?, ?, ?)";
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, boardType);
				System.out.println(boardType);
				pstmt.setString(2, file.getFileTypeId());
				System.out.println(file.getFileTypeId());
				pstmt.setString(3, file.getFileName());
				System.out.println(file.getFileName());
				pstmt.setString(4, file.getFilePath());
				System.out.println(file.getFilePath());
				result = pstmt.executeUpdate();
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}

		return result;
	}
	
	//게시글 하나 조회
	public Board selectOneBoard(Connection conn, String boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset= null;
		
		String query = "select * from tbl_board where board_id = ?";
		Board oneB = null;
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				oneB = new Board();
				
				oneB.setBoardId(rset.getString("board_id"));
				oneB.setBoardTitle(rset.getString("board_title"));
				oneB.setBoardContent(rset.getString("BOARD_CONTENT"));
				oneB.setMemberId(rset.getString("member_id"));
				oneB.setBoardType(rset.getString("board_type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return oneB;
	}

	//게시글 수정하기
	public int updateBoard(Connection conn, Board board) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = "update tbl_board set board_title = ?, board_content =? where member_id = ? and board_id =?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setString(3, board.getMemberId());
			pstmt.setString(4, board.getBoardId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	//게시글 하나의 첨부된 파일리스트 추출
	public ArrayList<BoardFile> selectBoardFileList(Connection conn, String boardId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<BoardFile> fileList = new ArrayList<BoardFile>();
		String query = "select * from tbl_file where file_type_id = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardId);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BoardFile file = new BoardFile();
				file.setFileNo(rset.getString("file_no"));
				file.setFileTypeId(boardId); //주의
				file.setFileName(rset.getString("file_name"));
				file.setFilePath(rset.getString("file_path"));
				file.setFileTypeId(rset.getString("file_type_id"));
				file.setFileType(rset.getString("file_type"));
				fileList.add(file);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return fileList;
		
	}
	
	//게시글과 관련된 파일 삭제 
	public int deleteBoardFile(Connection conn, String fileNo) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		String query = "delete from tbl_file where file_no = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, fileNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
/*
  	// 스트링으로할려했었다가 어레이리스트로 변경
	public int updateListBoard(Connection conn, String boardId) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = "update tbl_file set board_id = ?";
		
		try {
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, boardId);
	
		result = pstmt.executeUpdate();
				
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
*/
	//게시글 삭제
	public int deleteBoard(Connection conn, String boardId) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = "delete from tbl_board where board_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertComment(Connection conn, BoardComment comment) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into tbl_comment values (?, ?, default, default, ?, ?, ?, ?, ?,)";
		
		try {
			pstmt =	conn.prepareStatement(query);
			
			pstmt.setString(1, comment.getCommentId());
			pstmt.setString(2, comment.getCommentContent());
			pstmt.setString(3, comment.getCommentNo());
			pstmt.setString(4, comment.getParentCommentId());
			pstmt.setString(5, comment.getBoardId());
			pstmt.setString(6, comment.getMemberId());
			pstmt.setString(7, comment.getCommentNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
}
