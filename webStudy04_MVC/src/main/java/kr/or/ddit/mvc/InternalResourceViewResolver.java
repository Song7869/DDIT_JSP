package kr.or.ddit.mvc;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InternalResourceViewResolver implements ViewResolver {
	private String prefix;
	private String suffix;
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public void viewResolve(String viewName, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			req.getRequestDispatcher(Objects.toString(prefix, "")+viewName+Objects.toString(suffix, "")).forward(req, resp);
		}
	}

}










