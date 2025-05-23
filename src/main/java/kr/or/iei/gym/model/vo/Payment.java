package kr.or.iei.gym.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
	private String paymentId;
	private String memberId;
	private String ticketPrice;
	private String payMethod;
	private String cardName;
	private String paymentDate;
	private String merchantId;
}
