package kr.or.ddit.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.or.ddit.common.PKNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(1)
@RestControllerAdvice(annotations=RestController.class)
public class CommonExceptionRestControllerAdvice {
	@ExceptionHandler(PKNotFoundException.class)
	public Map<String, Object> exceptionHandler(PKNotFoundException e) {
		log.error(e.getMessage(), e);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", 500);
		resultMap.put("message", e.getMessage());
		return resultMap;
	}
}
