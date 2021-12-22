package kr.or.ddit.advice;

import java.util.Calendar;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.common.PKNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(2)
@ControllerAdvice(annotations=Controller.class) //모든 컨트롤러에 대해서 (Advice)weaving 할 수 있게 되었다.
public class CommonExceptionControllerAdvice {
// ex ) 모든 컨트롤러에서 모든 뷰로 현재시각을 뷰로 전달해야한다.
	//핵심 : 모든 컨트롤러
	//횡단 : 현재시각을 뷰로 전달해야한다.
	//AOP가 없으면 일일이 컨트롤러에 addAttribute 해줘야함
	
	@ModelAttribute("now")
	public Calendar getCalendar() {
		return Calendar.getInstance();
	}
	
	
	//모든 컨트롤러에서 발생하는 PKNotFoundException 에 대한 공통 처리
	//핵심 : 모든 컨트롤러
	//횡단 : PKE 처리
	@ExceptionHandler(PKNotFoundException.class)
	public String exceptionHandler(PKNotFoundException e) {
		//마치 캐치블럭인거마냥 역할을 한다.
		log.error(e.getMessage(), e);
		return "errors/pkNotFound";
		//논리적인 뷰 네임 -> 어뎁터 -> 뷰(리졸버...파일즈리졸버.. 인터널....) -> 
	}
	
	
	
}
