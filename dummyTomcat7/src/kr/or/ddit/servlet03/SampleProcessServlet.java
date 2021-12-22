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

import com.sun.media.sound.RealTimeSequencerProvider;

@WebServlet("/sampleProcess.do")
public class SampleProcessServlet extends HttpServlet{
	

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get callback");
		
		request.setCharacterEncoding("UTF-8");
		String textParam = request.getParameter("textParam");
		String numberParam = request.getParameter("numberParam"); // 형변환 가능 여부 오류(화이트 스페이스 포함-length)
		String radioParam = request.getParameter("radioParam"); // null 값 오류-length
		String[] checkParam = request.getParameterValues("checkParam");
		String singleSelect = request.getParameter("singleSelect");
		String[] multiSelect = request.getParameterValues("multiSelect");
		

		Map<String, String[]> parameterMap = request.getParameterMap();//엔트리
		for(Entry<String, String[]> entry: parameterMap.entrySet()) {
			String name = entry.getKey(); // 파라미터의 이름
			String[] values = entry.getValue(); // 파라미터의 값
			System.out.printf("%s : %s\n", name, Arrays.toString(values));
		}
		
		Enumeration<String> names= request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String[] values = request.getParameterValues(name);
			System.out.printf("%s : %s\n", name, Arrays.toString(values));
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post callback");
		
		request.setCharacterEncoding("UTF-8");
		String textParam = request.getParameter("textParam");
		String numberParam = request.getParameter("numberParam"); // 형변환 가능 여부 오류(화이트 스페이스 포함-length)
		String radioParam = request.getParameter("radioParam"); // null 값 오류-length
		String[] checkParam = request.getParameterValues("checkParam");
		String singleSelect = request.getParameter("singleSelect");
		String[] multiSelect = request.getParameterValues("multiSelect");
		

		Map<String, String[]> parameterMap = request.getParameterMap();//엔트리
		for(Entry<String, String[]> entry: parameterMap.entrySet()) {
			String name = entry.getKey(); // 파라미터의 이름
			String[] values = entry.getValue(); // 파라미터의 값
			System.out.printf("%s : %s\n", name, Arrays.toString(values));
		}
		
		Enumeration<String> names= request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String[] values = request.getParameterValues(name);
			System.out.printf("%s : %s\n", name, Arrays.toString(values));
		}
	}
	

	
	
}
