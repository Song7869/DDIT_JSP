package kr.or.ddit;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextHierarchy({
	@ContextConfiguration("file:webapp/WEB-INF/spring/*-context.xml"),
	@ContextConfiguration("file:webapp/WEB-INF/spring/appServlet/serlvet-context.xml")
})
@WebAppConfiguration
@Transactional
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestWebAppConfiguration {

}
