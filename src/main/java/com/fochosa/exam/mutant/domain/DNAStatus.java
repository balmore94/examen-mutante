package com.fochosa.exam.mutant.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DNAStatus {
	@JsonProperty("count_mutant_dna")
	private Long mutantCount = 0L;
	@JsonProperty("count_human_dna")
	private Long humanCount = 0L;
	private BigDecimal ratio;
	@JsonIgnore
	private Long total = 0L;

	public Long getMutantCount() {
		return mutantCount;
	}

	public void setMutantCount(Long mutantCount) {
		this.mutantCount = mutantCount;
	}

	public Long getHumanCount() {
		return humanCount;
	}

	public void setHumanCount(Long humanCount) {
		this.humanCount = humanCount;
	}

	public BigDecimal getRatio() {
		return ratio;
	}

	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "DNAStatus [mutantCount=" + mutantCount + ", humanCount=" + humanCount + ", ratio=" + ratio + ", total="
				+ total + "]";
	}

}
