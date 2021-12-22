package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

/**
 * 어떤 명령(mappingCondition)을 어느 핸들러(commandHandler(POJO)+handlerMethod)가 처리할 수 있는지를 캡슐화한 객체
 *
 */
public class RequestMappingInfo {
	private RequestMappingCondition mappingCondition;
	private Object commandHandler;
	private Method handlerMethod;
	
	public RequestMappingInfo(RequestMappingCondition mappingCondition, Object commandHandler, Method handlerMethod) {
		super();
		this.mappingCondition = mappingCondition;
		this.commandHandler = commandHandler;
		this.handlerMethod = handlerMethod;
	}

	@Override
	public String toString() {
		return "RequestMappingInfo [commandHandler=" + commandHandler + ", handlerMethod=" + handlerMethod + "]";
	}

	public RequestMappingCondition getMappingCondition() {
		return mappingCondition;
	}

	public Object getCommandHandler() {
		return commandHandler;
	}

	public Method getHandlerMethod() {
		return handlerMethod;
	}
	
}
