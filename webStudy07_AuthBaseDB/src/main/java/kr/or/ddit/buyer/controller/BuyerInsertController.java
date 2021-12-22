package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/buyer/buyerInsert.do")
@SessionAttributes(names="buyer", types=BuyerVO.class)
public class BuyerInsertController{
	@Inject
    private BuyerService service;
	@Inject
    private OthersDAO othersDAO;
	
	@ModelAttribute("lprodList")
	public List<Map<String, Object>> lprodList() {
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
		return lprodList;
	}
	
	@ModelAttribute("currentAction")
	public String currentAction() {
		return "/buyer/buyerInsert.do";
	}
    
	@RequestMapping
	public String form(){
		return "buyer/buyerForm";
	}
	
	@ModelAttribute("buyer")
	public BuyerVO buyer() {
		return new BuyerVO();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String insert(
		@Validated(InsertGroup.class) @ModelAttribute("buyer") BuyerVO buyer
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, SessionStatus sessionStatus
	) throws IOException{
		
		String viewName = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.createBuyer(buyer);
			switch (result) {
			case OK:
				viewName = "redirect:/buyer/buyerView.do?what="+buyer.getBuyerId();
				sessionStatus.setComplete();
				break;
			default: // FAIL
				redirectAttributes.addFlashAttribute("message", "서버 오류");
				String errorModelName = BindingResult.class.getName() + ".buyer";
				redirectAttributes.addFlashAttribute( errorModelName , errors);
				viewName = "redirect:/buyer/buyerInsert.do";
				break;
			}
		} else {
			viewName = "redirect:/buyer/buyerInsert.do";
		}
		return viewName;
	}
}