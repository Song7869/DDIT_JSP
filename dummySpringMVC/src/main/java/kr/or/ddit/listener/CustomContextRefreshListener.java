package kr.or.ddit.listener;

import javax.servlet.ServletContext;

import org.omg.IOP.ServiceContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomContextRefreshListener {
	
	@EventListener(value=ContextRefreshedEvent.class)
	public void eventHandler(ContextRefreshedEvent event) {
		WebApplicationContext context = (WebApplicationContext) event.getApplicationContext();
		log.info("{} 컨테이너 초기화 되었음.", context);
		
		ServletContext application = context.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		
	}
	
}
