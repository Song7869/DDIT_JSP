package kr.or.ddit.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class TestVO {
	private String prop1;
	private int prop2;
	
	private MultipartFile prop3;
}
