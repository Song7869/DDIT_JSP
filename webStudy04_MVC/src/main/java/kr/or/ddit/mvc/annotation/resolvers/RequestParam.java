package kr.or.ddit.mvc.annotation.resolvers;

import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;

@Target(PARAMETER)
@Retention(RUNTIME)
public @interface RequestParam {
	String value();
	boolean required() default true;
	String defaultValue() default "";
}
