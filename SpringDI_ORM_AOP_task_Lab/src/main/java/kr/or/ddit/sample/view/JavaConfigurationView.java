package kr.or.ddit.sample.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.ddit.sample.conf.JavaConfiguration;
import kr.or.ddit.sample.service.SampleService;

public class JavaConfigurationView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new AnnotationConfigApplicationContext(JavaConfiguration.class);
		SampleService service2 = context.getBean("sampleService2", SampleService.class);
		SampleService service2_1 = context.getBean("sampleService2", SampleService.class);
		System.out.println(service2 == service2_1);
		context.registerShutdownHook();
		
	}
}
