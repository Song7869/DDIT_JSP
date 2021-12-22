package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/factorial.do")
public class FactorialServlet extends HttpServlet{
	
	private long factorial(int operand){
		if(operand < 0) 
			throw new IllegalArgumentException("연산 수행 불가");
		if(operand<=1){
			return operand;			
		}else{
			return operand * factorial(operand-1);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/04/factorial.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accept = request.getHeader("Accept");
		
		String param = request.getParameter("operand");
		int sc = HttpServletResponse.SC_OK;
		String message = null;
		long result = -1;
		if(param!=null && !param.isEmpty()){
			try{
				int operand = Integer.parseInt(param);
				result = factorial(operand);
				request.setAttribute("result", result);
			}catch(RuntimeException e){
				sc = HttpServletResponse.SC_BAD_REQUEST;
				message = e.getMessage();
			}
		}
		if(sc!=HttpServletResponse.SC_OK){
			response.sendError(sc, message);
			return;
		}
		if(accept.contains("json")) {
//			Marshalling
			String pattern = "{\"%s\":%d}";
			String json = String.format(pattern, "result", result);
			
			response.setContentType("application/json;charset=UTF-8");
//			Serialization
			PrintWriter out = response.getWriter();
			out.print(json);
		}else {
			request.getRequestDispatcher("/WEB-INF/views/04/factorial.jsp").forward(request, response);
		}
	}
}







