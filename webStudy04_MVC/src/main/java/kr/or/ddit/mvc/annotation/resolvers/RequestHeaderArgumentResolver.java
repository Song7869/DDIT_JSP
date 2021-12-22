package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 요청 헤더 하나의 값을 핸들러 메소드의 파라미터로 전달하기 위한 객체.
 *
 */
public class RequestHeaderArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		RequestHeader annotation = parameter.getAnnotation(RequestHeader.class);
		return (
				ClassUtils.isPrimitiveOrWrapper(parameterType) ||
				String.class.equals(parameterType)
			   ) && annotation!=null;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		RequestHeader annotation = parameter.getAnnotation(RequestHeader.class);
		String requestHeaderName = annotation.value();
		String requestHeader = req.getHeader(requestHeaderName);
		boolean required = annotation.required();
		if(required && StringUtils.isBlank(requestHeader)) {
			throw new BadRequestException(requestHeaderName+" 헤더 누락");
		}else if(!required && StringUtils.isBlank(requestHeader)) {
			requestHeader = annotation.defaultValue();
		}
		
		Object paramValue = null;
		if(byte.class.equals(parameterType) || Byte.class.equals(parameterType)) {
			paramValue = Byte.parseByte(requestHeader);
		}else if(short.class.equals(parameterType) || Short.class.equals(parameterType)) {
			paramValue = Short.parseShort(requestHeader);
		}else if(int.class.equals(parameterType) || Integer.class.equals(parameterType)) {
			paramValue = Integer.parseInt(requestHeader);
		}else if(long.class.equals(parameterType) || Long.class.equals(parameterType)) {
			paramValue = Long.parseLong(requestHeader);
		}else if(float.class.equals(parameterType) || Float.class.equals(parameterType)) {
			paramValue = Float.parseFloat(requestHeader);
		}else if(double.class.equals(parameterType) || Double.class.equals(parameterType)) {
			paramValue = Double.parseDouble(requestHeader);
		}else if(boolean.class.equals(parameterType) || Boolean.class.equals(parameterType)) {
			paramValue = Boolean.parseBoolean(requestHeader);
		}else if(char.class.equals(parameterType) || Character.class.equals(parameterType)) {
			paramValue = requestHeader.charAt(0);
		}else {
			paramValue = requestHeader;
		}
		return paramValue;
	}
}








