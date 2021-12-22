package kr.or.ddit.validate.constraints;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;

@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy= {TelNumberValidator.class})
public @interface TelNumber {
	String regex() default "\\d{2,3}-\\d{3,4}-\\d{4}";
	
	String message() default "{kr.or.ddit.validate.constraints.TelNumber.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
