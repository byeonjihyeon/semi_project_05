package kr.or.iei.board.model.vo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardFile extends Board {
	
	private String fileNo;
	private String fileType;
	private String fileTypeId;
	private String fileName;
	private String filePath;
	
}
