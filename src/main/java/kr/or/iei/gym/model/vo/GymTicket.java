package kr.or.iei.gym.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GymTicket {
	String oneMonth;
	String threeMonth;
	String sixMonth;
	String oneYear;
	String oneDay;
	String gymId;	//해당 헬스장 아이디
}
