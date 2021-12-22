package kr.or.ddit.sample.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.service.SampleService;

public class AutoDIView {
	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("classpath:kr/or.ddit/various/conf/AutoDI.xml");
		context.registerShutdownHook();
		
		String[] beanNames = context.getBeanDefinitionNames();
		for(String name : beanNames) {
			System.out.println(name);
		}
		
		
		SampleService service = context.getBean(SampleService.class);
		System.out.println(service.retrieveSampleData("a001"));
	}
}
