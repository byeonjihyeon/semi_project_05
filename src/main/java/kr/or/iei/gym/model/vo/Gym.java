package kr.or.iei.gym.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Gym {
	private String gymId;
	private String gymPw;
	private String gymName;
	private String phone;
	private String email;
	private String gymAddr;
	private int gymMemberCnt;
	private String gymEnrollDate;
	private String approvalCode;
	private String openTime;
	private String detail;
	private String convenience;
	private String facilities;	//헬스장 상세탭에 나타날 편의시설 컬럼
	private GymTicket ticket;	//헬스장의 이용권
	private List<GymFile> fileList;	//헬스장의 첨부파일 리스트
	private GymApplication gymApplication; //헬스장 등록신청내역
}
