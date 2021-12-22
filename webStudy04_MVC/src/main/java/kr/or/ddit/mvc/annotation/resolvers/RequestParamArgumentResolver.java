package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 요청 파라미터 하나의 값을 핸들러 메소드의 파라미터로 전달하기 위한 객체.
 *
 */
public class RequestParamArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		RequestParam annotation = parameter.getAnnotation(RequestParam.class);
		return (
				ClassUtils.isPrimitiveOrWrapper(parameterType) ||
				String.class.equals(parameterType)
			   ) && annotation!=null;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		RequestParam annotation = parameter.getAnnotation(RequestParam.class);
		String requestParamName = annotation.value();
		String requestParam = req.getParameter(requestParamName);
		boolean required = annotation.required();
		if(required && StringUtils.isBlank(requestParam)) {
			throw new BadRequestException(requestParamName+" 파라미터 누락");
		}else if(!required && StringUtils.isBlank(requestParam)) {
			requestParam = annotation.defaultValue();
		}
		
		Object paramValue = null;
		if(byte.class.equals(parameterType) || Byte.class.equals(parameterType)) {
			paramValue = Byte.parseByte(requestParam);
		}else if(short.class.equals(parameterType) || Short.class.equals(parameterType)) {
			paramValue = Short.parseShort(requestParam);
		}else if(int.class.equals(parameterType) || Integer.class.equals(parameterType)) {
			paramValue = Integer.parseInt(requestParam);
		}else if(long.class.equals(parameterType) || Long.class.equals(parameterType)) {
			paramValue = Long.parseLong(requestParam);
		}else if(float.class.equals(parameterType) || Float.class.equals(parameterType)) {
			paramValue = Float.parseFloat(requestParam);
		}else if(double.class.equals(parameterType) || Double.class.equals(parameterType)) {
			paramValue = Double.parseDouble(requestParam);
		}else if(boolean.class.equals(parameterType) || Boolean.class.equals(parameterType)) {
			paramValue = Boolean.parseBoolean(requestParam);
		}else if(char.class.equals(parameterType) || Character.class.equals(parameterType)) {
			paramValue = requestParam.charAt(0);
		}else {
			paramValue = requestParam;
		}
		return paramValue;
	}
}








