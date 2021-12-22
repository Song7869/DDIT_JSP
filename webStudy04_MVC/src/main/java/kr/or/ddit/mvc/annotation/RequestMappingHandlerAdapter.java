package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.resolvers.BadRequestException;
import kr.or.ddit.mvc.annotation.resolvers.HandlerMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttributeArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ReqestPartArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestHeaderArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletSpecArgumentResovler;

public class RequestMappingHandlerAdapter implements HandlerAdapter {
	List<HandlerMethodArgumentResolver> argumentResolvers;
	
	public RequestMappingHandlerAdapter() {
		super();
		argumentResolvers = new ArrayList<>();
		argumentResolvers.add(new ModelAttributeArgumentResolver());
		argumentResolvers.add(new ServletSpecArgumentResovler());
		argumentResolvers.add(new RequestParamArgumentResolver());
		argumentResolvers.add(new RequestHeaderArgumentResolver());
		argumentResolvers.add(new ReqestPartArgumentResolver());
	}
	
	private HandlerMethodArgumentResolver findArgumentResolver(Parameter parameter){
		HandlerMethodArgumentResolver finded = null;
		for(HandlerMethodArgumentResolver resolver : argumentResolvers) {
			if(resolver.isSupported(parameter)) {
				finded = resolver;
				break;
			}
		}
		return finded;
	}


	@Override
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object commandHandler = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
		Parameter[] parameters = handlerMethod.getParameters();
		try {
			Object[] parameterValues = null;
			if(parameters.length > 0) {
				parameterValues = new Object[parameters.length];
				for(int i=0; i<parameterValues.length; i++) {
					HandlerMethodArgumentResolver finded = findArgumentResolver(parameters[i]);
					if(finded!=null)
						parameterValues[i] = finded.argumentResolve(parameters[i], req, resp);
					else
						throw new RuntimeException(String.format("현재 파라미터[%s]는 처리할 수 없는 형태임.", parameters[i].toString()));
				}
			}
			String viewName = (String) handlerMethod.invoke(commandHandler, parameterValues);
			return viewName;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ServletException(e);
		} catch (BadRequestException e) {
			resp.sendError(400, e.getMessage());
			return null;
		}
	}
}




