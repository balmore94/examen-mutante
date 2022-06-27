package com.fochosa.exam.mutant.exception;

import org.springframework.http.HttpStatus;

public class InvalidNitrogenousBaseException extends DNAException {

	private static final long serialVersionUID = -1701103574391064913L;
	
	private static final String error = "dna.invalid.nitrogenous.base";

	public InvalidNitrogenousBaseException(String dnaRow) {
		super(error, HttpStatus.BAD_REQUEST,
				"The only valid characters are A, T, C e G. Found invalida char in " + dnaRow);
	}

}
