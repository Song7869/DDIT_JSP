package kr.or.ddit.BuyQuest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.BuyQuest.service.BuyService;
import kr.or.ddit.BuyQuest.service.BuyServiceImpl;
import kr.or.ddit.vo.BuyQuestVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/BQList.do")
public class BuyQuestListServlet extends HttpServlet{
	
	private BuyService service = new BuyServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	List<BuyQuestVO> BQList = service.retrieveBQList();
	String test = BQList.get(1).getBuyerId();
	log(test);
	req.setAttribute("BQ", BQList);
	
	String viewName = "BuyQuest";
	
	String prefix = "/WEB-INF/views/";
	String suffix = ".jsp";
	req.getRequestDispatcher(prefix+viewName+suffix).forward(req, resp);
	
	}

}
