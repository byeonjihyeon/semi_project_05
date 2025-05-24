package kr.or.iei.gym.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
	private String paymentId; 	//payment
	private String memberId;	//m i
	private String ticketPrice;	// tp
	private String payMethod; // p m
	private String cardName; // c c n
	private String paymentDate; // date
	private String merchantId; // merchantId
}
