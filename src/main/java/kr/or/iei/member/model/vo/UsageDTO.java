package kr.or.iei.member.model.vo;

import kr.or.iei.gym.model.vo.GymFile;
import kr.or.iei.gym.model.vo.Payment;
import kr.or.iei.gym.model.vo.Usage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsageDTO {
    private GymFile gymFile;
    private Usage usage;
    private Payment payment;
    
  
}
