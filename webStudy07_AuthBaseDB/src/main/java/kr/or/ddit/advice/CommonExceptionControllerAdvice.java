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
@ControllerAdvice(annotations=Controller.class)
public class CommonExceptionControllerAdvice {
// 모든 컨트롤러에서 현재 시각을 뷰로 전달해야 한다.
	@ModelAttribute("now")
	public Calendar getCalendar() {
		return Calendar.getInstance();
	}
// 모든 컨트롤러에서 발생하는 PKNotFoundException에 대한 공통 처리
	@ExceptionHandler(PKNotFoundException.class)
	public String exceptionHandler(PKNotFoundException e) {
		log.error(e.getMessage(), e);
		return "errors/pkNotFound";
	}
}














