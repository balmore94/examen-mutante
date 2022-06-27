package com.fochosa.exam.mutant.exception.handler.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseError implements Serializable {
	
	private static final long serialVersionUID = 7086555032131362215L;

	private Integer status;
	private String errorCode;
	private String message;
	private String errorMessage;
	private Long timestamp;
	private Object[] argsMessage;

	public ResponseError() {

	}

	public ResponseError(Integer status, String errorCode, String message, String errorMessage) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
		this.errorMessage = errorMessage;
		this.setTimestamp(Calendar.getInstance().getTimeInMillis());
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Object[] getArgsMessage() {
		return argsMessage;
	}

	public void setArgsMessage(Object[] argsMessage) {
		this.argsMessage = argsMessage;
	}

	@Override
	public String toString() {
		return "ResponseError [status=" + status + ", errorCode=" + errorCode + ", message=" + message
				+ ", errorMessage=" + errorMessage + ", timestamp=" + timestamp + ", argsMessage="
				+ Arrays.toString(argsMessage) + "]";
	}
}
