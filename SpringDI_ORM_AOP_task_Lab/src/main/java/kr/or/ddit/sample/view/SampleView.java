package kr.or.ddit.sample.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.service.SampleService;

public class SampleView {
	public static void main(String[] args) {
		ApplicationContext container = 
				new ClassPathXmlApplicationContext("kr/or.ddit/sample/conf/Sample-DI.xml");
		
		SampleService service = container.getBean(SampleService.class);
		String pk = "a001";
		StringBuffer info = service.retrieveSampleData(pk);
		System.out.println(info);
	}
}
