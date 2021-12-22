package kr.or.ddit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

public class ValidateUtils {
	private static Validator validator;
	static {
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory(); 
//		validator = factory.getValidator();
		validator = Validation.byDefaultProvider()
		        .configure()
		        .messageInterpolator(
		                new ResourceBundleMessageInterpolator(
		                        new PlatformResourceBundleLocator( "kr.or.ddit.msgs.errorMessage" )
		                )
		        )
		        .buildValidatorFactory()
		        .getValidator();
		
	}
	public static <T> boolean validate(T target, Map<String, List<String>> errors, Class...groups) {
		boolean valid = true;
		Set<ConstraintViolation<T>> violations = validator.validate(target, groups);
		valid = violations.size() == 0;
		if(!valid) {
			for(ConstraintViolation<T> violation : violations) {
				Path path = violation.getPropertyPath();
				String message = violation.getMessage();
				List<String> already = errors.get(path.toString());
				if(already==null) {
					already = new ArrayList<>();
					errors.put(path.toString(), already);
				}
				already.add(message);
			}
		}
		return valid;
	}
}
