package com.sh.hairball.board.enrollboard.model.exception;

public class EnrollBoardException extends RuntimeException{

	public EnrollBoardException() {
		super();
	}

	public EnrollBoardException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EnrollBoardException(String message, Throwable cause) {
		super(message, cause);
	}

	public EnrollBoardException(String message) {
		super(message);
	}

	public EnrollBoardException(Throwable cause) {
		super(cause);
	}

}
