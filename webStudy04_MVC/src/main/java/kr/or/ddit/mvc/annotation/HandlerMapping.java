package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
	/**
	 * 현재 요청을 처리할 수 있는 Command Handler 검색
	 * @param req
	 * @return
	 */
	public RequestMappingInfo findCommandHanler(HttpServletRequest req);
}
