package kr.or.iei.common;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListData <T> { //제네릭
	
	ArrayList<T> list; //제네릭
	String pageNavi; //페이지네이션 
	int currentPage; //현재 페이지
	int pageSize; //한화면에 보여줄 갯수
}
