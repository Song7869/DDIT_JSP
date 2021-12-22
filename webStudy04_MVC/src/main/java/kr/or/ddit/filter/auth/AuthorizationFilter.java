package kr.or.ddit.filter.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorizationFilter implements Filter{

	private ServletContext application;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 초기화", getClass().getSimpleName());
		application = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Map<String, String[]> securedResource = (Map) application.getAttribute(AuthenticationFilter.ATTRNAME);
		//1. 보호 자원에 대한 요청이 아닌 경우(true).
		//2. 보호 자원에 대한 요청인 경우(이미 인증된 유저).
		// 2-1. 허가된 유저(true)
		// 2-2. 허가되지 않은 유저(false)
		String uri = req.getRequestURI().split(";")[0];
		String contextPath = req.getContextPath();
		uri = uri.substring(contextPath.length());
		boolean pass = true;
		if(securedResource.containsKey(uri)) {
			MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
			String userRole = authMember.getMemRole();
			String[] roles = securedResource.get(uri);
			pass = Arrays.binarySearch(roles, userRole)>=0;
		}
		
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, uri+" 에 접근할 권한이 없음.");
		}
	}

	@Override
	public void destroy() {
		log.info("{} 소멸", getClass().getSimpleName());
	}

}










