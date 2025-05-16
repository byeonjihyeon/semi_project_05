package kr.or.iei.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//자유 게시판 테이블
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
	private String boardId;				//게시판 번호
	private String boardTitle;			//게시판 제목
	private String boardContent;		//게시판 내용
	private String createdAt;			//게시판 작성일
	private String updateAt;			//게시판 수정일
	private int viewCount;				//조회수
	private String memberId;			//작성자 (회원아이디)
	private int boardLikeCount;			//게시판 좋아요 횟수
}
