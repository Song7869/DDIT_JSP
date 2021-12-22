package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.mvc.fileupload.StandardMultipartHttpServletRequest;

public class ReqestPartArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		Class<?> parameterType = parameter.getType();
		return annotation!=null && (
			MultipartFile.class.equals(parameterType)
			|| Part.class.equals(parameterType)
			|| (
				parameterType.isArray() && parameterType.getComponentType().equals(MultipartFile.class)	
			)
		);
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req instanceof StandardMultipartHttpServletRequest) {
			RequestPart annotation = parameter.getAnnotation(RequestPart.class);
			String partName = annotation.value();
			boolean required = annotation.required();
			
			Class<?> parameterType = parameter.getType();
			Object parameterValue = null;
			
			if(MultipartFile.class.equals(parameterType)) {
				parameterValue = ((StandardMultipartHttpServletRequest) req).getFile(partName);
			}else if(Part.class.equals(parameterType)){
				parameterValue = req.getPart(partName);
			}else {
				List<MultipartFile> files = ((StandardMultipartHttpServletRequest) req).getFiles(partName);
				if(files!=null) {
					parameterValue = files.toArray(new MultipartFile[files.size()]);
				}
			}
			
			if(required && parameterValue==null) {
				throw new BadRequestException("필수 파일이 업로드되지 않았음.");
			}
			
			return parameterValue;
		}else {
			throw new BadRequestException("파일이 포함된 멀티파트 요청이 아님.");
		}
	}

}










