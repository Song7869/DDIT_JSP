package kr.or.ddit;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HelloMaven {
	public static void main(String[] args) throws JsonProcessingException {
		
		List<String> list = new ArrayList<>();
		list.add("value1");
		list.add("value2");
		list.add("value3");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(list);
		System.out.println("Hello! Maven!");
		System.out.println(json);
	}
}
