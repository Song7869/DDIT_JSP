package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.HandlerAdapter;
import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;

public class FrontController extends HttpServlet{
	
	private HandlerMapping handlerMapping;
	private HandlerAdapter handlerAdapter;
	private ViewResolver viewResolver;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String basePackages = config.getInitParameter("basePackages");
		String prefix = config.getInitParameter("prefix");
		String suffix = config.getInitParameter("suffix");
		handlerMapping = new RequestMappingHandlerMapping(basePackages.split("\\s+"));
		handlerAdapter = new RequestMappingHandlerAdapter();
		viewResolver = new InternalResourceViewResolver();
		((InternalResourceViewResolver)viewResolver).setPrefix(prefix);
		((InternalResourceViewResolver)viewResolver).setSuffix(suffix);
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestMappingInfo mappingInfo = handlerMapping.findCommandHanler(req);
		
		if(mappingInfo == null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, mappingInfo+"는 제공하지 않는 서비스임.");
			return;
		}
		
		String viewName = handlerAdapter.invokeHandler(mappingInfo, req, resp);
		
		if(viewName == null) {
			if(!resp.isCommitted()) {
				// 메소드 지원되지 않는 경우,
				// 개발자가 잘못 만든 경우,
				resp.sendError(500);
			}
			// backend controller 에서 응답이 이미 결정된 경우,
			return;
		}else {
			viewResolver.viewResolve(viewName, req, resp);
		}
	}
}
