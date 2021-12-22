package kr.or.ddit.util;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.member.dao.MemberDAOImplTest;
import kr.or.ddit.mvc.annotation.RequestMappingCondition;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetFieldsWithAnnotationWithClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFieldsAtClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMethodsWithAnnotationAtClass() throws InstantiationException, IllegalAccessException {
		Class targetClz = MemberDAOImplTest.class;
		Object commandHandler = targetClz.newInstance();
		Map<Method, RequestMapping> methods = ReflectionUtils.getMethodsWithAnnotationAtClass(targetClz, RequestMapping.class, String.class);
		methods.forEach((handlerMethod, annotation)->{
			String uri = annotation.value();
			RequestMethod requestMethod = annotation.method();
			RequestMappingCondition mappingCondition = new RequestMappingCondition(uri, requestMethod);
			RequestMappingInfo mappingInfo = new RequestMappingInfo(mappingCondition, commandHandler, handlerMethod);
			log.info("{} : {}", mappingCondition, mappingInfo);
		});
	}

//	@Test
	public void testGetMethodsAtClass() {
		Class targetClz = MemberDAOImplTest.class;
		List<Method> methods = ReflectionUtils.getMethodsAtClass(targetClz, String.class, HttpServletRequest.class, HttpServletResponse.class);
		methods.forEach((mtd)->{
			log.info("method : {}", mtd);
		});
	}

//	@Test
	public void testGetClassesWithAnnotationAtBasePackages() {
		Map<Class<?>, Controller> classes = ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, "kr.or.ddit");
		classes.forEach((clz, annotation)->{
			log.info("clz : {}, annotation : {}", clz, annotation);
		});
	}

//	@Test
	public void testGetAllClassesAtBasePackages() {
		List<Class<?>> classes = ReflectionUtils.getAllClassesAtBasePackages("kr.or.ddit");
		classes.forEach((clz)->{
			log.info("clz 정보 : {}", clz);			
		});
	}

}















