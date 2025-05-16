package kr.or.iei.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.board.model.vo.Board;
import kr.or.iei.common.JDBCTemplate;

public class BoardDao {
	
	public ArrayList<Board> selectBoardList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Board> list = new ArrayList<Board>();
		
		//게시글 번호, 제목, 작성자아이디, 작성일, 조회수, 좋아요수 select 
		String query = "select board_id, board_title, member_id, created_at, view_count, board_like_count from (select rownum rnum, A.* FROM (SELECT * FROM TBL_board A ORDER BY created_at DESC) A ) WHERE RNUM >=? AND RNUM <=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
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

	public int selectTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int totCnt = 0;
		
		String query = "select count(*) as cnt from tbl_board";
		
		try {
			pstmt = conn.prepareStatement(query);
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
}
