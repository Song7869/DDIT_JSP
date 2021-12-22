package kr.or.ddit.various;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.vo.CollectionDIVO;

public class CollectionDIView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/SpringDILab/src/main/resources/kr/or.ddit/various/conf/CollectionDI.xml");
		context.registerShutdownHook();
		
		CollectionDIVO cV01 = context.getBean("cV01", CollectionDIVO.class);
		System.out.println(cV01);
		
		CollectionDIVO cV02 = context.getBean("cV02", CollectionDIVO.class);
		System.out.println(cV02);
		
		CollectionDIVO cV03 = context.getBean("cV03", CollectionDIVO.class);
		System.out.println(cV03);
		
		CollectionDIVO cV04 = context.getBean("cV04", CollectionDIVO.class);
		System.out.println(cV04);
	}
}
