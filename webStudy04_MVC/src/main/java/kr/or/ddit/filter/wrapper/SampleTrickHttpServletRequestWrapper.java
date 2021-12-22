package kr.or.ddit.filter.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SampleTrickHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public SampleTrickHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		if("what".equals(name)) {
			return "P101000001";
		}else {
			return super.getParameter(name);
		}
	}
}
