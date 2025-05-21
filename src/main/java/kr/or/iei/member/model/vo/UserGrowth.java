package kr.or.iei.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserGrowth extends Member{
	
	private int memberTall;
	private int memberWeight;
	private int memberHopeWeight;
	private String HistoryId;
}
