package com.fochosa.exam.mutant.service.detector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MutantDetectorProcessor {

	private static final Logger log = LoggerFactory.getLogger(MutantDetectorProcessor.class);
	protected final DetectorContext context;

	public MutantDetectorProcessor(DetectorContext context) {
		this.context = context;
	}

	protected abstract void searchMutanteSequences();

	protected abstract void moveNext(Coordinate coordinate);

	protected abstract boolean hasNext(Coordinate coordinate, int actualSequence);

	protected boolean findMutantSequence(Coordinate coordidate) {
		char currentChar = coordidate.dna[coordidate.row][coordidate.column];
		int sequence = 1;
		while (hasNext(coordidate, sequence)) {
			moveNext(coordidate);

			if (currentChar != coordidate.curruntChar) {
				sequence = 1;
				currentChar = coordidate.curruntChar;
			} else if (++sequence >= context.getSequenceToMudant()) {
				log.debug("New mutant sequences end at: {}", coordidate);
				this.newSequenceMatch();

				if (hasMatchSequencesMutant()) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

	protected void newSequenceMatch() {
		context.setMatchs(context.getMatchs() + 1);

	}

	public boolean hasMatchSequencesMutant() {
		log.debug("Analyze if has more than {} mutant sequences. Actutal: {} ",
				context.getCountSequencesMatchMutant() - 1, context.getMatchs());
		int count = context.getCountSequencesMatchMutant();
		return context.getMatchs() >= count;
	}

	protected static class Coordinate {
		char dna[][];
		int subIndex;
		int row;
		int column;
		char lastChar;
		char curruntChar;
		int safeIndex;
		int size;

		public Coordinate(char[][] dna, int subIndex, int row, int column) {
			super();
			this.dna = dna;
			this.safeIndex = dna.length - 1;
			this.size = dna.length;
			this.subIndex = subIndex;
			this.row = row;
			this.column = column;
			this.lastChar = dna[row][column];
		}

		@Override
		public String toString() {
			return "Coordinate [row=" + row + ", column=" + column + ", lastChar=" + lastChar + ", curruntChar="
					+ curruntChar + "]";
		}

		public static Coordinate at(char[][] dna, int row, int column) {
			return new Coordinate(dna, 0, row, column);
		}

		public static Coordinate at(char[][] dna, int row, int column, int index) {
			return new Coordinate(dna, index, row, column);
		}

	}

}
