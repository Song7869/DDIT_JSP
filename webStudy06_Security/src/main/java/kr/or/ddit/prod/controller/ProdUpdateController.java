package kr.or.ddit.prod.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.ProdVO;

// /prod/prodUpdate.do viewName = prod/prodForm
@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController {
	@Inject
	private ProdService service;
	
	@RequestMapping
	public String form(@RequestParam(value="what", required=true) String prodId, Model model){
		ProdVO prod = service.retrieveProd(prodId);
		model.addAttribute("prod", prod);
		return "prod/prodForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String process(@Validated(UpdateGroup.class) @ModelAttribute("prod") ProdVO prod, 
			BindingResult errors,
			Model model ){
		String viewName = null;
		if(!errors.hasErrors()) {
			ServiceResult result = service.modifyProd(prod);
			if(ServiceResult.OK.equals(result)) {
				viewName = "redirect:/prod/prodView.do?what="+prod.getProdId();
			}else {
				model.addAttribute("message", "서버 오류");
				viewName = "prod/prodForm";
			}
		}else {
			viewName = "prod/prodForm";
		}
		return viewName;
	} 
}














