package kr.or.ddit.servlet04;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/aad")
public class CalculateServlet extends HttpServlet{
	
	private double pl(int left, int right) {
		double result = left+right;
		System.out.println("더하기");
		System.out.println("결과 : "+result);
		return result;
	}
	
	private double min(int left, int right) {
		double result = left-right;
		System.out.println("빼기");
		System.out.println("결과 : "+result);
		return result;
	}
	
	private double mul(int left, int right) {
		double result = left*right;
		System.out.println("곱하기");
		System.out.println("결과 : "+result);
		return result;
	}
	
	private double div(int left, int right) {
		double result = left/right;
		System.out.println("나누기");
		System.out.println("결과 : "+result);
		return result;
	}
	
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/04/calculate.jsp").forward(req, resp);
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String lop = req.getParameter("leftOp");
		String op = req.getParameter("operator");
		String rop = req.getParameter("rightOp");
		if(lop!=null && !lop.isEmpty() || op!=null && !op.isEmpty()||rop!=null && !rop.isEmpty()) {
				
		int leftOp = Integer.parseInt(req.getParameter("leftOp"));
		String operator = req.getParameter("operator");
		int rightOp = Integer.parseInt(req.getParameter("rightOp"));
		
		double result = 0;
		
		if(operator.equals("+")) {
			result = pl(leftOp, rightOp);
		}
		else if(operator.equals("-")) {
			result = min(leftOp, rightOp);
		}
		else if(operator.equals("*")) {
			result = mul(leftOp, rightOp);
		}
		else if(operator.equals("/")) {
			result = div(leftOp, rightOp);
		}
		System.out.println("결과 : "+result);
		
		req.setAttribute("lo", leftOp);
		req.setAttribute("op", operator);
		req.setAttribute("ro", rightOp);
		req.setAttribute("result", result);
		req.getRequestDispatcher("/WEB-INF/views/04/calculate.jsp").forward(req, resp);
		}
		else {
			
		}
	}
	
	
}
