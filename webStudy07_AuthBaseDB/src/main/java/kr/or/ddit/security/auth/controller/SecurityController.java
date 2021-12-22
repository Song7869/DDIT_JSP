package kr.or.ddit.security.auth.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.security.auth.service.SecurityService;
import kr.or.ddit.security.auth.vo.AuthorityVO;
import kr.or.ddit.security.auth.vo.ResourceVO;

@Controller
@RequestMapping("/security")
public class SecurityController {
	@Inject
	private SecurityService service;
	
	@GetMapping({"", "{authority}"})
	public String view(@PathVariable(required=false) AuthorityVO authority, Model model) {
		List<AuthorityVO> authorities = service.retrieveAllAuthorities();
		model.addAttribute("authorities", authorities);
		List<ResourceVO> resourceList = service.retrieveAllResources();
		model.addAttribute("resourceList", resourceList);
		return "security/authForm";
	}
	
	@GetMapping(value="{authority}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<ResourceVO> getResourcesForAuthority(@PathVariable(required=true) AuthorityVO authority){
		return service.retrieveResourceListForAuthority(authority);
	}
	
	@PostMapping
	public String update(@Valid AuthorityVO roleVO, Errors errors, RedirectAttributes redirectAttributes) {
		String goPage = "redirect:/security";
		if(!errors.hasErrors()) {
			ServiceResult result = service.updateResourceRole(roleVO);
			if(!ServiceResult.OK.equals(result)) {
				redirectAttributes.addFlashAttribute("message", "설정 변경 실패");
			}
			goPage += "/"+roleVO.getAuthority();
		}
		return goPage;
	}
}
