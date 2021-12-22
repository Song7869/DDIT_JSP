package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;

public class ModelAttributeArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
		
		return !ClassUtils.isPrimitiveOrWrapper(parameterType) 
					&& annotation!=null;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
		try {
			Object model = parameterType.newInstance();
			String attributeName = annotation.value();
			req.setAttribute(attributeName, model);
			Map<String, String[]> paramterMap = req.getParameterMap();
			BeanUtils.populate(model, paramterMap);
			
			return model;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}

	}

}
