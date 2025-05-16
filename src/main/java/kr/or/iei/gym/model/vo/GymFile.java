package kr.or.iei.gym.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GymFile {
	private String fileNo;
	private String fileName;
	private String filePath;
	private String gymId;
}
