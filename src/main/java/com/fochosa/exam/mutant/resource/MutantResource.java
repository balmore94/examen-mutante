package com.fochosa.exam.mutant.resource;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fochosa.exam.mutant.domain.DNASequence;
import com.fochosa.exam.mutant.service.MutantService;

@RestController
@RequestMapping("/mutant")
public class MutantResource {

	private static final Logger log = LoggerFactory.getLogger(MutantResource.class);

	@Autowired
	private MutantService mutantService;

	@PostMapping("/")
	public ResponseEntity<Void> isMutant(@RequestBody @Valid DNASequence dnaSequence) {
		log.debug("REQUEST to Detects whether a human is a mutant: {}", dnaSequence);

		boolean isMutant = mutantService.isMutantDNA(dnaSequence);
		if (isMutant) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

}
