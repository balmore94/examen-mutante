package com.fochosa.exam.mutant.repository.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.stereotype.Repository;

import com.fochosa.exam.mutant.domain.DNAResult;
import com.fochosa.exam.mutant.domain.DNAStatus;
import com.fochosa.exam.mutant.repository.DNAStatusRepository;

@Repository
public class DNAStatusRepositoryImpl implements DNAStatusRepository {

	private static final Logger log = LoggerFactory.getLogger(DNAStatusRepositoryImpl.class);

	@Autowired
	private transient MongoTemplate template;

	@Override
	public DNAStatus getSummaryStatus() {
		log.debug("Repository, find summary status");

		DNAStatus status = findSumResult();
		BigDecimal ratio = calcRatio(status);
		status.setRatio(ratio);

		return status;

	}

	private BigDecimal calcRatio(DNAStatus status) {
		BigDecimal ratio = BigDecimal.ZERO;

		if (status.getMutantCount() != 0) {
			if (status.getHumanCount() == 0) {
				ratio = BigDecimal.ONE;
			} else {
				BigDecimal mutant = BigDecimal.valueOf(status.getMutantCount());
				BigDecimal human = BigDecimal.valueOf(status.getHumanCount());
				ratio = mutant.divide(human, 2, RoundingMode.HALF_UP);
			}
		}
		return ratio;
	}

	private DNAStatus findSumResult() {
		// @formatter:off
		Aggregation aggregation = Aggregation.newAggregation(
		group()
			.sum(ConditionalOperators
					.when(ComparisonOperators.valueOf("isMutant").equalToValue(true)).then(1).otherwise(0)).as("mutantCount")
			.sum(ConditionalOperators
					.when(ComparisonOperators.valueOf("isMutant").equalToValue(false)).then(1).otherwise(0)).as("humanCount")
			.count().as("total")
		);

		AggregationResults<DNAStatus> result = this.template.aggregate(aggregation, DNAResult.class, DNAStatus.class);
		// @formatter:on

		DNAStatus status = result.getUniqueMappedResult();
		return status == null ? new DNAStatus() : status;
	}

}
