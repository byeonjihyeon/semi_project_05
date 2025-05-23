package kr.or.iei.gym.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usage {//이용내역 테이블
	private String usageNo;
	private String enrollDate;
	private String memberIdRef;
	private String ticketIdRef;
	private String gymIdRef;
	private String leftDate;
	private String tickPeriod;
}
