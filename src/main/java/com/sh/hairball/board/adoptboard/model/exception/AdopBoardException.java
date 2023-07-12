package com.sh.hairball.board.adoptboard.model.exception;

public class AdopBoardException extends RuntimeException {

	public AdopBoardException() {
		super();
	}

	public AdopBoardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AdopBoardException(String message, Throwable cause) {
		super(message, cause);
	}

	public AdopBoardException(String message) {
		super(message);
	}

	public AdopBoardException(Throwable cause) {
		super(cause);
	}

}
