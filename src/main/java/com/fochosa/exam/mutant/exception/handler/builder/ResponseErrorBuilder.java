package com.fochosa.exam.mutant.exception.handler.builder;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;

import com.fochosa.exam.mutant.exception.DNAException;
import com.fochosa.exam.mutant.exception.handler.model.ResponseError;
import com.fochosa.exam.mutant.util.BundleMessage;

public class ResponseErrorBuilder {

	private final transient BundleMessage bungleMessage;

	private static final String UNEXPECTED = "unexpected";

	private ResponseError responseError;

	public ResponseErrorBuilder(BundleMessage bungleMessage) {
		this.bungleMessage = bungleMessage;
		responseError = new ResponseError();
	}

	public ResponseError build() {
		loadMessage(this.responseError.getMessage(), responseError.getArgsMessage());
		return responseError;
	}

	private void loadMessage(String messageCode, Object... argsMessage) {
		if (StringUtils.isEmpty(messageCode)) {
			try {
				String message = bungleMessage.getMessageProperties(this.responseError.getErrorCode(), argsMessage);
				this.responseError.setMessage(message);
			} catch (NoSuchMessageException e) {
				this.responseError.setMessage("No found message error: " + messageCode);
			}
		}
	}

	public ResponseErrorBuilder witchBusinesException(DNAException e) {
		withErrorCode(e.getCode());
		withMessage(e.getMessage());
		withErrorMessage(StringUtils.defaultIfBlank(e.getErroMessage(), ExceptionUtils.getRootCauseMessage(e)));
		withStatusCode(e.getStatusCode());
		withArgsMessage(e.getArgsMessage());
		return this;
	}

	public ResponseErrorBuilder withErrorCode(String code) {
		responseError.setErrorCode(StringUtils.defaultIfBlank(code, UNEXPECTED));
		return this;
	}
	
	public ResponseErrorBuilder withMessage(String message) {
		responseError.setMessage(message);
		return this;
	}

	public ResponseErrorBuilder withMessageByCode(String messageCode, Object... argsMessage) {
		loadMessage(messageCode, argsMessage);
		return this;
	}

	public ResponseErrorBuilder withErrorMessage(String errorMessage) {
		responseError.setErrorMessage(StringUtils.defaultIfBlank(errorMessage, UNEXPECTED));
		return this;
	}

	public ResponseErrorBuilder withStatusCode(HttpStatus httpStatus) {
		httpStatus = ObjectUtils.defaultIfNull(httpStatus, HttpStatus.INTERNAL_SERVER_ERROR);
		responseError.setStatus(httpStatus.value());
		return this;
	}

	public ResponseErrorBuilder withArgsMessage(Object... argsMessage) {
		this.responseError.setArgsMessage(argsMessage);
		return this;
	}

}
