package kr.or.ddit.servlet06;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getServerTime.do")
public class GetServerTimeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String language = req.getParameter("language");
		Locale clientLocale = req.getLocale();
		
		if(language != null && !language.isEmpty()) {
			clientLocale = Locale.forLanguageTag(language);
		}
		
		Calendar now = Calendar.getInstance();
		String content = String.format(clientLocale,"%tc", now);
		resp.setContentType("text/plain;charset=UTF-8");
		
		try(PrintWriter out = resp.getWriter();
				){
			
			out.print(content);
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
