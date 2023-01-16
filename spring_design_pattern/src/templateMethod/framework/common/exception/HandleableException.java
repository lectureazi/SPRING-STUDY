package templateMethod.framework.common.exception;

import templateMethod.framework.common.code.ErrorCode;

public class HandleableException extends RuntimeException{

	private static final long serialVersionUID = 8482101260767502904L;
	
	public HandleableException() {
		
	}
	
	public HandleableException(String message) {
		super(message);
	}
	
	public HandleableException(ErrorCode error) {
		super(error.MESSAGE);
	}
	
}
