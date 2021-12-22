package kr.or.ddit.mvc.fileupload;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultipartFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 초기화", getClass().getSimpleName());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String contentType = req.getContentType();
		if(contentType!=null && contentType.startsWith("multipart/")) {
			StandardMultipartHttpServletRequest wrapper = 
					new StandardMultipartHttpServletRequest(req);
			chain.doFilter(wrapper, response);
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		log.info("{} 소멸", getClass().getSimpleName());
	}

}











