package kr.or.iei.gym.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GymApplication {
	private String insertGymNo; //헬스장 등록신청번호
	private String screeningDate; //헬스장 심사신청일자
	private String judgeId; //반려 코드
	private String gymId; //헬스장 아디
	private String fileNo;
}
