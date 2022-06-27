package com.fochosa.exam.mutant.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fochosa.exam.mutant.domain.DNAStatus;
import com.fochosa.exam.mutant.repository.DNAStatusRepository;

@Service
public class DNAStatusService {
	private static final Logger log = LoggerFactory.getLogger(DNAStatusService.class);

	@Autowired
	private DNAStatusRepository dnaStatusRepository;

	public DNAStatus getStatistics() {
		log.debug("Find statistics of verifications the exposed method (humans, mutants and ratio)");
		return dnaStatusRepository.getSummaryStatus();
	}

}