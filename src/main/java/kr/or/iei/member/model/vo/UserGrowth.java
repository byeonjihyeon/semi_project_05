package kr.or.iei.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserGrowth extends Member{
	
	private String memberTall;
	private String memberWeight;
	private String memberHopeWeight;
	private String HistoryId;
	private String recordDate;
}
