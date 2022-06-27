package com.fochosa.exam.mutant.exception;

import org.springframework.http.HttpStatus;

public class DNAStructureLengthException extends DNAException {

	private static final long serialVersionUID = -1701103574391064913L;

	private static final String error = "dna.sequence.inconsistent.length";
	
	public DNAStructureLengthException(int expected, int found) {
		super(error, HttpStatus.BAD_REQUEST);
		String msgError = "The length of the DNA sequences must be the same size. Expected " + expected + ", found "
				+ found;
		super.setErroMessage(msgError);
	}

}
