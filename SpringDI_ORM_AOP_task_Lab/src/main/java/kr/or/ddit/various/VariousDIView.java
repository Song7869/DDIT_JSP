package kr.or.ddit.various;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.conf.JavaConfiguration;
import kr.or.ddit.vo.VariousDIVO;

public class VariousDIView {
	public static void main(String[] args) {
		ConfigurableApplicationContext parent =
				new AnnotationConfigApplicationContext(JavaConfiguration.class);
		
		ConfigurableApplicationContext child =
				new ClassPathXmlApplicationContext(new String[]{"kr/or.ddit/various/conf/VariousDI.xml"}, parent);
		
		parent.registerShutdownHook();
		child.registerShutdownHook();
		
		VariousDIVO vo1 = child.getBean("vo1", VariousDIVO.class);
		VariousDIVO vo2 = child.getBean("vo2", VariousDIVO.class);
		StringBuffer info = vo2.getService().retrieveSampleData("a001");
		System.out.println(vo1);
		System.out.println(vo2);
		System.out.println(info);
	}
}












