package kr.or.iei.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardComment {

		private String CommentId; //댓글 아이디 PRIMARY KEY
		private String CommentContent; //댓글 내용
		private String ContentAt; //댓글 작성일 DATE DEFAULT 
		private String UpdateAt; // 댓글 수정일 DATE
		private String ParentCommentId; // 대댓글
		private String BoardId; // REFERENCES TBL_BOARD(BOARD_ID)
		private String MemberId; // REFERENCES TBL_MEMBER(MEMBER_ID)
		private int CommentLikeCount; //게시판 좋아요 횟수 NUMBER DEFAULT
		private String CommentNo;	//게시글 번호
}
