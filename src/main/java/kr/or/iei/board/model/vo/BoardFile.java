package kr.or.iei.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardFile {
	
	private String fileNo;
	private String fileType;
	private String fileTypeId;
	private String fileName;
	private String filePath;
	
}
