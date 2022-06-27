package com.fochosa.exam.mutant.service.detector;

public class DetectorContext {

	char[][] dna;
	private int sequenceToMudant;
	private int countSequencesMatchMutant;
	private int matchs;

	public int getMatchs() {
		return matchs;
	}


	public void setMatchs(int matchs) {
		this.matchs = matchs;
	}

	public char[][] getDna() {
		return dna;
	}

	public void setDna(char[][] dna) {
		this.dna = dna;
	}
	
	public int getSequenceToMudant() {
		return sequenceToMudant;
	}

	public void setSequenceToMudant(int sequenceToMudant) {
		this.sequenceToMudant = sequenceToMudant;
	}

	public int getCountSequencesMatchMutant() {
		return countSequencesMatchMutant;
	}

	public void setCountSequencesMatchMutant(int countSequencesMatchMutant) {
		this.countSequencesMatchMutant = countSequencesMatchMutant;
	}

}
