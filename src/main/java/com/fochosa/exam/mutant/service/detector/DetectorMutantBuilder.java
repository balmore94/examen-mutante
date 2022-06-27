package com.fochosa.exam.mutant.service.detector;

public class DetectorMutantBuilder {

	private DetectorContext context;

	DetectorMutantBuilder() {
		this.context = new DetectorContext();
	}

	public static DetectorMutantBuilder newInstance() {
		return new DetectorMutantBuilder();
	}

	public DetectorMutantBuilder withDNA(char[][] dna) {
		this.context.setDna(dna);
		return this;
	}

	public DetectorMutantBuilder withSequenceToMudant(int sequenceToMudant) {
		this.context.setSequenceToMudant(sequenceToMudant);
		return this;
	}

	public DetectorMutantBuilder withCountSequencesMatchMutant(int countSequencesMatchMutant) {
		this.context.setCountSequencesMatchMutant(countSequencesMatchMutant);
		return this;
	}

	public DetectorContext getContext() {
		return this.context;
	}
}
