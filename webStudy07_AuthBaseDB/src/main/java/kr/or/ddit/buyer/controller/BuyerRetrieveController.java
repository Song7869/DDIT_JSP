package kr.or.ddit.buyer.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SearchVO;

@Controller
@RequestMapping("/buyer")
public class BuyerRetrieveController {
	@Inject
    BuyerService service;   
	
    @RequestMapping("buyerList.do")
	public String list(
		@RequestParam(value="page", required=false, defaultValue="1") int currentPage
		, @ModelAttribute("searchVO") SearchVO simpleSearch 
		, Model model
	){
		PagingVO<BuyerVO> pagingVO = new PagingVO<>(5, 3);
		pagingVO.setSearchVO(simpleSearch);
		
		pagingVO.setTotalRecord(service.retrieveBuyerCount(pagingVO));
		
		pagingVO.setCurrentPage(currentPage);
		List<BuyerVO> buyerList = service.retrieveBuyerList(pagingVO);
		pagingVO.setDataList(buyerList);
		
		model.addAttribute("pagingVO", pagingVO);
		return "buyer/buyerList";
	}
    
    @RequestMapping("buyerView.do")
	public String view(@RequestParam("what") String buyer_id, Model model){
		BuyerVO buyer = service.retrieveBuyer(buyer_id);
		model.addAttribute("buyer", buyer);
		return "buyer/buyerView";
	}
}
