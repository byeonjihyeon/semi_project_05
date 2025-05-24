package kr.or.iei.gym.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GymApplyFile {
	private String fileNo;
	private String fileName;
	private String filePath;
	private String fileSavePath;
	private String gymId;
	private String insertGymNo;
	
	
	public String getfileUrl() {
		return fileSavePath + filePath;
	}
}
