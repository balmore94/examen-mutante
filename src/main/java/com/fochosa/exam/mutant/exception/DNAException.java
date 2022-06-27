package com.fochosa.exam.mutant.exception;

import org.springframework.http.HttpStatus;

public class DNAException extends RuntimeException {

	private static final long serialVersionUID = -1701103574391064913L;

	private String code;

	private HttpStatus statusCode;

	private String message;

	private String erroMessage;

	private Object[] argsMessage;

	public DNAException(String error, HttpStatus status) {
		super(error);
		this.code = error;
		this.statusCode = status;
	}

	public DNAException(String error, HttpStatus status, String errorMessage) {
		super(error);
		this.code = error;
		this.statusCode = status;
		this.erroMessage = errorMessage;
	}

	public String getCode() {
		return code;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getErroMessage() {
		return erroMessage;
	}

	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}

	public Object[] getArgsMessage() {
		return argsMessage;
	}

}
