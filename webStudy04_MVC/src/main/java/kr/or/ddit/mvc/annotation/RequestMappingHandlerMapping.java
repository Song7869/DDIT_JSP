package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.util.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestMappingHandlerMapping implements HandlerMapping {
	
	private Map<RequestMappingCondition, RequestMappingInfo> handlerMap;
	
	public RequestMappingHandlerMapping(String...basePackages) {
		handlerMap = new LinkedHashMap<>();
		Map<Class<?>, Controller> classes = ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackages);
		classes.forEach((handlerClass, tmp)->{
			try {
				Object commandHandler = handlerClass.newInstance();
				Map<Method, RequestMapping> methods = ReflectionUtils.getMethodsWithAnnotationAtClass(handlerClass, RequestMapping.class, String.class);
				methods.forEach((handlerMethod, annotation)->{
					String uri = annotation.value();
					RequestMethod requestMethod = annotation.method();
					RequestMappingCondition mappingCondition = new RequestMappingCondition(uri, requestMethod);
					RequestMappingInfo mappingInfo = new RequestMappingInfo(mappingCondition, commandHandler, handlerMethod);
					handlerMap.put(mappingCondition, mappingInfo);
					log.info("{} : {}", mappingCondition, mappingInfo);
				});
			}catch (Exception e) {
				throw new RuntimeException(e);
			}			
		});
	}

	@Override
	public RequestMappingInfo findCommandHanler(HttpServletRequest req) {
		String uri = req.getRequestURI().split(";")[0];
		String contextPath = req.getContextPath();
		uri = uri.substring(contextPath.length());
		String method = req.getMethod().toUpperCase();
		RequestMethod requestMethod = RequestMethod.valueOf(method);
		
		RequestMappingCondition mappingCondition = new RequestMappingCondition(uri, requestMethod);
		
		return handlerMap.get(mappingCondition);
	}

}
