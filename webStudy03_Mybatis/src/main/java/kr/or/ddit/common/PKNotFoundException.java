package kr.or.ddit.common;

/**
 * Primary Key 로 조회한 데이터가 존재하지 않을때 발생하는 예외.
 *
 */
public class PKNotFoundException extends RuntimeException{

	public PKNotFoundException() {
		super();
		
	}

	public PKNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public PKNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public PKNotFoundException(String message) {
		super(message);
		
	}

	public PKNotFoundException(Throwable cause) {
		super(cause);
		
	}

}
