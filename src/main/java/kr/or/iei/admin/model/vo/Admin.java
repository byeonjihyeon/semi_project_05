package kr.or.iei.admin.model.vo;

import kr.or.iei.member.model.vo.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//부모: Member 클래스
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin extends Member {
	private String jobCode;		//업무코드
	private String url;			//메뉴 url
	private String selYN;		//조회 권한
	private String insYN;		//삽입 권한
	private String updYN;		//수정 권한
	private String delYN;		//삭제 권한
	private String regDate;		//등록일
}
