package kr.or.ddit.servlet01;

import java.util.Calendar;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/01/now.tmpl")
public class TimeServletWithTemplate extends AbstractTemplateServlet{

	@Override
	protected String getMimeType() {
		return "text/html;charset=UTF-8";
	}

	@Override
	protected void makeData(HttpServletRequest req) {
		Calendar cal = Calendar.getInstance();
		req.setAttribute("now", cal);
		req.setAttribute("title", "서블릿에서 만든 제목");
	}
}













