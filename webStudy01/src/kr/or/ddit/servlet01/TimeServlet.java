//context path : /time
//service uri : /time/now.do

//content : 
//<h4>당신의 접속 시간은 : ... 입니다. </h4>



package kr.or.ddit.servlet01;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.*;
import java.util.*;


public class TimeServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws IOException, ServletException
	{

		 //1. mime 설정
		String mime = "text/html;charset=UTF-8";
		resp.setContentType(mime);
		
		 //2. tmpl 파일을 읽는다.
		String tmplpath = req.getServletPath();
		InputStream is = getServletContext().getResourceAsStream(tmplpath);
		StringBuffer tmplSrc = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader reader = new BufferedReader(isr);
		String temp = null;
		while((temp = reader.readLine()) != null) {
			tmplSrc.append(String.format("%s\n", temp));
		}
		
		 //3. 데이터 만들기
		Calendar cal = Calendar.getInstance();		
		Object data = String.format("%tc", cal);
		
		 //4. 데이터 치환(데이터 넣기)
		String html = tmplSrc.toString().replace("%now%", data.toString());
		
		 //5. 응답에 전송
		PrintWriter out = null;
		try{
			out = resp.getWriter();
			out.print(html);
		}finally{
			if(out != null){
			out.close();
			}
		}
		
		}

	}
