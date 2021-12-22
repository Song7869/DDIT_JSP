package kr.or.ddit.buyer.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.buyer.service.BuyerService;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;

@Controller
@RequestMapping("/buyer/buyerUpdate.do")
@SessionAttributes(names="buyer", types=BuyerVO.class)
public class BuyerUpdateController {
	
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
		return "/buyer/buyerUpdate.do";
	}
    
    
	@RequestMapping
	public String form(@RequestParam("what") String buyer_id, Model model){
		if(!model.containsAttribute("buyer")) {
			BuyerVO buyer = service.retrieveBuyer(buyer_id);
			model.addAttribute("buyer", buyer);
		}
		return "buyer/buyerForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String update(
		@Validated(UpdateGroup.class) @ModelAttribute("buyer") BuyerVO buyer
		, BindingResult errors
		, RedirectAttributes redirectAttributes
		, SessionStatus sessionStatus
	){
		
		String viewName = null;
		
		if (!errors.hasErrors()) {
			ServiceResult result = service.modifyBuyer(buyer);
			switch (result) {
			case OK:
				viewName = "redirect:/buyer/buyerView.do?what="+buyer.getBuyerId();
				sessionStatus.setComplete();
				break;
			default: // FAIL
				redirectAttributes.addFlashAttribute("message", "서버 오류");
				viewName = "redirect:/buyer/buyerUpdate.do?what="+buyer.getBuyerId();
				break;
			}
		} else {
			viewName = "redirect:/buyer/buyerUpdate.do?what="+buyer.getBuyerId();
			String errorModelName = BindingResult.class.getName() + ".buyer";
			redirectAttributes.addFlashAttribute( errorModelName , errors);
		}
		return viewName;
	}
}