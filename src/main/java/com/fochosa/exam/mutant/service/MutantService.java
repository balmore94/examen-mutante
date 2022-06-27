package com.fochosa.exam.mutant.service;

import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fochosa.exam.mutant.config.MutantProperties;
import com.fochosa.exam.mutant.domain.DNAResult;
import com.fochosa.exam.mutant.domain.DNASequence;
import com.fochosa.exam.mutant.exception.DNAStructureLengthException;
import com.fochosa.exam.mutant.exception.InvalidNitrogenousBaseException;
import com.fochosa.exam.mutant.repository.DNAResultRepository;
import com.fochosa.exam.mutant.service.detector.DetectorMutant;

@Service
public class MutantService {
	
	private static final Logger log = LoggerFactory.getLogger(MutantService.class);

	private static final Pattern NITROGENOUS_BASE_PATTERN = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);

	@Autowired
	private MutantProperties mutantProperties;

	@Autowired
	private DNAResultRepository dnaResultRepository;

	public boolean isMutantDNA(DNASequence dna) {
		boolean dnaMudant = isDNAMutant(dna);

		DNAResult result = new DNAResult();
		result.setDna(dna);
		result.setMutant(dnaMudant);
		this.dnaResultRepository.save(result);

		return dnaMudant;

	}

	private boolean isDNAMutant(DNASequence dnaSequence) {
		log.debug("Start processin to decote if a dnaSequence '{}' is a mutant' DNA", dnaSequence);

		if (dnaSequence.getDna().size() <= this.mutantProperties.getSequenceToMudant()) {
			log.debug("Minimum length must be greater than {} to belong to mutant.",
					this.mutantProperties.getSequenceToMudant());
			return false;
		}

		char[][] dna = loadDNAStructure(dnaSequence);
		DetectorMutant mutantDetector = new DetectorMutant(dna, mutantProperties.getCountSequencesMatchMutant(),
				mutantProperties.getSequenceToMudant());
		return mutantDetector.isMutante();

	}

	private char[][] loadDNAStructure(DNASequence dnaSequence) {
		log.debug("Load the DNA structure into a Two-dimensional vectors.");
		int vectorLength = dnaSequence.getDna().size();
		char[][] dna = new char[vectorLength][vectorLength];

		for (int i = 0; i < vectorLength; i++) {
			String dnaRow = dnaSequence.getDna().get(i);
			validateDNAConsistency(vectorLength, dnaRow);
			dna[i] = dnaRow.toUpperCase().toCharArray();
		}
		return dna;
	}

	private void validateDNAConsistency(int vectorLength, String dnaRow) {
		if (dnaRow.length() != vectorLength) {
			log.error("The length of the DNA sequences must be the same size. Expected {}, found {}: {} ", vectorLength,
					dnaRow.length(), dnaRow);
			throw new DNAStructureLengthException(vectorLength, dnaRow.length());
		} else if (!NITROGENOUS_BASE_PATTERN.matcher(dnaRow).matches()) {
			log.error("The only valid characters are A, T, C e G. Found {}", dnaRow);
			throw new InvalidNitrogenousBaseException(dnaRow);
		}
	}

}
