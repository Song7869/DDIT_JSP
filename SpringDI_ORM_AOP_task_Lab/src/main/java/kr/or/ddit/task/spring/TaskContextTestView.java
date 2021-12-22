package kr.or.ddit.task.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TaskContextTestView {
	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("kr/or.ddit/task/task-context.xml");
		context.registerShutdownHook();
		
	}
}
