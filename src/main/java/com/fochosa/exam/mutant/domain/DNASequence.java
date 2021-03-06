package com.fochosa.exam.mutant.domain;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;

public class DNASequence {

	@NotNull
	private List<String> dna;

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dna == null) ? 0 : dna.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DNASequence other = (DNASequence) obj;
		if (dna == null) {
			if (other.dna != null)
				return false;
		} else if (!CollectionUtils.isEqualCollection(dna, other.dna))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DNASequence [dna=" + dna + "]";
	}

}
