package kr.or.ddit;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

// /sample/test
@Controller
public class SampleController {
	
	//스프링에서 메서드를 생략하면 모든 메서드를 다 받아
	@RequestMapping(value="/sample/test", method=RequestMethod.GET)
	public String test(Model model) {
		
		Date now = new Date();
		model.addAttribute("now", now);
		
		return "sample/view";
	}
	
	@RequestMapping("/file/upload")
	public String form() {
		
		return "sample/form";
	}
	
	@RequestMapping(value="/file/upload", method=RequestMethod.POST)
	public String process(
			@RequestParam String uploader,
			@RequestPart MultipartFile uploadFile
			) {
		
		System.out.println(uploader);
		System.out.println(uploadFile);
		
		return "redirect:/file/upload";
	}

	
}

