package kr.or.ddit.resource;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ResourceLoaderDesc {
	public static void main(String[] args) throws IOException {
//		ClassPathResource res = new ClassPathResource("log4j2.xml");
//		System.out.println(res.exists());
		ConfigurableApplicationContext context = 
						new ClassPathXmlApplicationContext();
		Resource cpRes = context.getResource("classpath:log4j2.xml");
		System.out.println(cpRes.exists());
		
		Resource fsRes = context.getResource("file:D:/contents/cat1.jpg");
		System.out.println(fsRes.getFile().getCanonicalPath());
		
		Resource webRes = context.getResource("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
		System.out.println(webRes.contentLength());
		context.close();
		
	}
}
