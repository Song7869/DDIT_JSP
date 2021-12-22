package kr.or.ddit.sample.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.service.SampleServiceImpl;

public class AopContextTestView {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new ClassPathXmlApplicationContext("kr/or.ddit/sample/conf/aop-context.xml");
		
		context.registerShutdownHook();
		SampleService service = context.getBean(SampleService.class);
		System.err.println(service.getClass().getName());
		String pk = "a001";
		StringBuffer info = service.retrieveSampleData(pk);
		System.out.println(info);
	}

}
