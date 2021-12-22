package kr.or.ddit.validate.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy= {FileMimeCheckValidator.class})
public @interface FileMimeChecker {
	String mime();
	
	String message() default "{kr.or.ddit.validate.constraints.FileMimeChecker.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
