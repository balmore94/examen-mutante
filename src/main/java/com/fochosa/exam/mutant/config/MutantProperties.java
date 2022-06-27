package com.fochosa.exam.mutant.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mutant", ignoreUnknownFields = false)
public class MutantProperties {

	private static final int DEFAULT_SEQUENCE_SIZE_MUTANT = 4;

	private static final int DEFAULT_COUNT_SEQUENCES_MATCH_MUTANT = 2;

	private int sequenceToMudant = DEFAULT_SEQUENCE_SIZE_MUTANT;

	private int countSequencesMatchMutant = DEFAULT_COUNT_SEQUENCES_MATCH_MUTANT;

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
	
	@Override
	public String toString() {
		return "MutantProperties [sequenceToMudant=" + sequenceToMudant + ", countSequencesMatchMutant="
				+ countSequencesMatchMutant + "]";
	}

}
