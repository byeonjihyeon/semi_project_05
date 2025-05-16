package kr.or.iei.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
	private String memberId;				//회원 아이디
	private String memberPw;				//회원 비밀번호
	private String memberName;				//회원 이름
	private String memberAddr;				//회원 주소
	private String memberEmail;				//회원 이메일
	private String memberNickname;			//회원 닉네임
	private String memberGrade;				//회원 등급 (포인트에 따라 변경)
	private String memberPhone;				//회원 전화번호(010-0000-0000)
	private String memberDate;				//회원 가입일
	private int reportedCnt;				//신고 당한 횟수
}
