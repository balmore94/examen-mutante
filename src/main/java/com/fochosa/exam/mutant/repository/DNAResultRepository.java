package com.fochosa.exam.mutant.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fochosa.exam.mutant.domain.DNAResult;

@Repository
public interface DNAResultRepository extends MongoRepository<DNAResult, DNAResult> {

}
