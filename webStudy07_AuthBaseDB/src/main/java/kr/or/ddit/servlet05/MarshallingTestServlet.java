package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.TestVO;

/**
 * Marshalling : Native Data -> 공용표현방식
 * UnMarshalling : 공용표현방식 -> Native Data
 * 
 * 이기종 시스템 간의 데이터 전달 구조(경량 표현 JSON)
 * N.D -> JSON -> 0101...... -> JSON -> N.D
 * Marshalling-> serilization -> deserilization -> UnMarshalling
 *
 */
@WebServlet("/marshallingTest.do")
public class MarshallingTestServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String contentType = req.getHeader("Content-Type");
		TestVO testVO = null;
		if(contentType.contains("json")) {
			testVO = getDataFromJSON(req);
		}else {
			testVO = getDataFromParameter(req);
		}
		System.out.println(testVO.toString());
	}

	private TestVO getDataFromJSON(HttpServletRequest req) throws IOException{
		try(
			InputStream is = req.getInputStream();
		){
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(is, TestVO.class);
		}
	}

	private TestVO getDataFromParameter(HttpServletRequest req) {
		String[] p1 = req.getParameterValues("p1");
		String[] p2 = req.getParameterValues("p2");
		String p3 = req.getParameter("p3");
		System.out.printf("p1 : %s, p2 : %s, p3 : %s\n", Arrays.toString(p1), Arrays.toString(p2), p3);
		
		return null;
	}
}





















