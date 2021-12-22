package kr.or.ddit.validate.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelNumberValidator implements ConstraintValidator<TelNumber, String>{

	private TelNumber constraintAnnotation;
	
	@Override
	public void initialize(TelNumber constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean valid = value==null || value.isEmpty();
		if(!valid)
			valid = value.matches(constraintAnnotation.regex());
		return valid;
	}

}
