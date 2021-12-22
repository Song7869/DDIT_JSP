package kr.or.ddit.servlet03;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.model.util.ArrayInfoUtil;

@WebServlet("/sampleProcess.do")
public class SampleProcessServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String textParam = request.getParameter("textParam");
		String numberParam = request.getParameter("numberParam");
		String radioParam = request.getParameter("radioParam");
		String[] checkParam = request.getParameterValues("checkParam");
		String singleSelect = request.getParameter("singleSelect");
		String[] multiSelect = request.getParameterValues("multiSelect");
		
		System.out.printf("textParam : %s\n", textParam);
		System.out.printf("numberParam : %s\n", numberParam);
		System.out.printf("radioParam : %s\n", radioParam);
		System.out.printf("checkParam : %s\n", Arrays.toString(checkParam));
		System.out.printf("singleSelect : %s\n", singleSelect);
		System.out.printf("multiSelect : %s\n", Arrays.toString(multiSelect));
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		for(Entry<String, String[]> entry : parameterMap.entrySet()) {
			String name = entry.getKey();
			String[] values = entry.getValue();
			System.out.printf("%s : %s\n", name, Arrays.toString(values));
		}
		
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String[] values = request.getParameterValues(name);
			System.out.printf("%s : %s\n", name, Arrays.toString(values));
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get callback");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post callback");
	}
}













