package kr.or.ddit.servlet01;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.*;
import java.util.*;

//client 의 명령어 : http://localhost/dummy/image.do
//callback method

public class TimeServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException, ServletException
	{
//		1. Mime 설정
		resp.setContentType("text/html;charset=UTF-8");
//		2. tmpl 파일 읽기
		String tmplPath = req.getServletPath();
		InputStream is = getServletContext().getResourceAsStream(tmplPath);
		StringBuffer tmplSrc = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		String temp = null;
		while((temp = reader.readLine())!=null) {
			tmplSrc.append(String.format("%s\n", temp));
		}
//		3. 데이터 생성
		Calendar cal = Calendar.getInstance();		
		Object data = String.format("%tc", cal);
//		4. 데이터 치환
		String html = tmplSrc.toString().replace("%now%", data.toString());
//		5. 응답 전송
		
		PrintWriter out = null;
		try{
			out = resp.getWriter();
			out.print(html);
		}finally{
			if(out!=null)
				out.close();
		}
		
	}
}










