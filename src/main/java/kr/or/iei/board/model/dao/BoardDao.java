package kr.or.iei.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.board.model.service.BoardFile;
import kr.or.iei.board.model.vo.Board;
import kr.or.iei.board.model.vo.BoardFile;
import kr.or.iei.common.JDBCTemplate;

public class BoardDao {
	
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


	public Board selectOneBoard(Connection conn, String boardId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Board board = null;
		
		String query = "select board_title, board_content, board_";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new Board();
				board.setBoardId(rset.getString("board_id"));
				board.setBoardTitle(rset.getString("board_title"));
				board.setBoardContent(rset.getString("board_content"));
				board.setUpdateAt(rset.getString("update_at"));
				board.setBoardLikeCount(rset.getInt("boardlike_count"));
			}
			

	public String selectBoardNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String boardNo = "";
		
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

		
		return board;
	}

	public int boardLikeCount(Connection conn, String boardId) {
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		String query = "";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardId);
			result = pstmt.executeUpdate();

		return boardNo;
	}

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

	public int insertBoardFile(Connection conn, BoardFile file) {
		PreparedStatement pstmt =null;
		
			int result = 0;
			
			String query ="insert into tbl_file values(seq_tbl_file.nextval, ?, ?, ?, ?)";
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, file.getFileType());
				pstmt.setString(2, file.getFileTypeId());
				pstmt.setString(3, file.getFileName());
				pstmt.setString(4, file.getFilePath());
				result = pstmt.executeUpdate();
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(pstmt);
			}

		return result;
	}


	public ArrayList<BoardFile> selectBoardFileList(Connection conn, String boardId) {
		PreparedStatement pstmt = null;
		// TODO Auto-generated method stub
		return null;
	}

	
		
		
	



}
