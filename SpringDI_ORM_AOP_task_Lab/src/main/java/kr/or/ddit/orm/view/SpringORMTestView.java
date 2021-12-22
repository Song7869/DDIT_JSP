package kr.or.ddit.orm.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.orm.dao.LprodDAO;

public class SpringORMTestView {
	public static void main(String[] args) {
		
		
		ConfigurableApplicationContext context =
				new ClassPathXmlApplicationContext("kr/or.ddit/orm/conf/*-context.xml");
		context.registerShutdownHook();
		
		LprodDAO dao = context.getBean(LprodDAO.class);
		System.out.println(dao.getClass());
		System.out.println(dao.selectLprodList());
	}
}
