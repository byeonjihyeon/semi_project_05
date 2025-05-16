package kr.or.iei.gym.model.vo;

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
}
