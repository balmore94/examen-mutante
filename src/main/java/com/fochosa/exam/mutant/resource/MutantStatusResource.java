package com.fochosa.exam.mutant.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fochosa.exam.mutant.domain.DNAStatus;
import com.fochosa.exam.mutant.service.DNAStatusService;

@RestController
@RequestMapping("/stats")
public class MutantStatusResource {

	private static final Logger log = LoggerFactory.getLogger(MutantStatusResource.class);

	@Autowired
	private DNAStatusService dnaStatusService;

	@GetMapping("/")
	public ResponseEntity<DNAStatus> statisticsDNAMutant() {
		log.debug("REQUEST to get the statistics of verifications the exposed method (humans, mutants and ratio).");
		DNAStatus status = dnaStatusService.getStatistics();
		return ResponseEntity.ok(status);
	}

}
