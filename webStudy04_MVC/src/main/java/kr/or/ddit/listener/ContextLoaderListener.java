package kr.or.ddit.listener;

import java.util.LinkedHashSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.extern.slf4j.Slf4j;

/**
 * Application Lifecycle Listener implementation class ContextLoaderListener
 *
 */
@WebListener
@Slf4j
public class ContextLoaderListener implements ServletContextListener {
	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce)  { 
		ServletContext application = sce.getServletContext();
		String cPath = application.getContextPath();
		application.setAttribute("cPath", cPath);
		application.setAttribute("userList", new LinkedHashSet<>());
		log.info("{} 초기화 되었음.", cPath);
	}

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	
}








