package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationFilter implements Filter{
	public static final String ATTRNAME = "securedResources";
	private Map<String, String[]> securedResources;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 초기화", getClass().getSimpleName());
		securedResources = new LinkedHashMap<>();
		// properties -> map
		Properties props = new Properties();
		try(
			InputStream inStream = AuthenticationFilter.class.getResourceAsStream("/kr/or/ddit/securedResources.properties");
		) {
			props.load(inStream);
			for(Entry<Object, Object> entry : props.entrySet()) {
				String url = entry.getKey().toString();
				String roles = entry.getValue().toString();
				String[] array = roles.replaceAll("\\s+", "").split(",");
				Arrays.sort(array);
				securedResources.put(url.trim(), array);
				log.info("{} : {}", url, Arrays.toString(array));
			}
			
			filterConfig.getServletContext().setAttribute(ATTRNAME, securedResources);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 1. 보호자원에 대한 요청이 아닌 경우(true).
		// 2. 보호자원에 대한 요청인 경우.
		// 2-1. 인증된 유저(true).
		// 2-2. 미인증 유저(false).
		String uri = req.getRequestURI().split(";")[0];
		String contextPath = req.getContextPath();
		uri = uri.substring(contextPath.length());
		
		boolean pass = true;
		if(securedResources.containsKey(uri)) {
			Object authMember = req.getSession().getAttribute("authMember");
			pass = authMember!=null;
		}
		
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendRedirect(req.getContextPath() + "/login/loginForm.jsp");
		}
		
	}

	@Override
	public void destroy() {
		log.info("{} 소멸", getClass().getSimpleName());
	}

}










