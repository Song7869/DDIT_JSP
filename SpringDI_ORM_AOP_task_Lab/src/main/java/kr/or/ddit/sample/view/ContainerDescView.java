package kr.or.ddit.sample.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.sample.service.SampleService;

public class ContainerDescView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				new GenericXmlApplicationContext("classpath:kr/or.ddit/sample/conf/DI-Desc.xml");
		
		SampleService service1 = context.getBean("sampleService1", SampleService.class);
		SampleService service1_1 = context.getBean("sampleService1", SampleService.class);
		SampleService service1_2 = context.getBean("sampleService1", SampleService.class);
		System.out.println(service1_1 == service1_2);
		System.out.println(service1 == service1_2);
		SampleService service2 = context.getBean("sampleService2", SampleService.class);
		SampleService service2_1 = context.getBean("sampleService2", SampleService.class);
		System.out.println(service2 == service2_1);
		
		System.out.println(service1 == service2);
//		String pk = "a001";
//		System.out.println(service1.retrieveSampleData(pk));
//		System.out.println(service2.retrieveSampleData(pk));
		context.close();
	}
}
