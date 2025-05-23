package kr.or.iei.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardComment {

		private String CommentId;
		private String CommentContent;
		private String ContentAt;
		private String UpdateAt;
		private String ParentCommentId;
		private String BoardId;
		private String MemberId;
		private String CommentLikeCount;
}
