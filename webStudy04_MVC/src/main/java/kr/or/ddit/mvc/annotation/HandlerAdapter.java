package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
	/**
	 * 리플렉션을 기반으로 command handler 를 호출하는 역할
	 * @param mappingInfo
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException TODO
	 * @throws IOException TODO
	 */
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
