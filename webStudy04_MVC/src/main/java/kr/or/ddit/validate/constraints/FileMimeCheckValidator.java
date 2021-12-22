package kr.or.ddit.validate.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import kr.or.ddit.mvc.fileupload.MultipartFile;

public class FileMimeCheckValidator implements ConstraintValidator<FileMimeChecker, MultipartFile>{

	private FileMimeChecker constraintAnnotation;
	
	@Override
	public void initialize(FileMimeChecker constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}
	
	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
		boolean valid = file==null;
		if(!valid) {
			String fileMime = file.getContentType();
			String checkMime = constraintAnnotation.mime();
			valid = fileMime.indexOf(checkMime) >= 0;
		}
		return valid;
	}

}
