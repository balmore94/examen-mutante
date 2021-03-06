package com.fochosa.exam.mutant.service.detector;

import java.util.LinkedList;
import java.util.List;

public class DetectorMutant {

	private DetectorContext context;
	List<MutantDetectorProcessor> processors = new LinkedList<>();

	public DetectorMutant(char[][] dna, int countSequencesMatchMutant, int sequenceToMudant) {
		// @formatter:off
		context = DetectorMutantBuilder
			.newInstance()
			.withDNA(dna)
			.withCountSequencesMatchMutant(countSequencesMatchMutant)
			.withSequenceToMudant(sequenceToMudant)
			.getContext();
		// @formatter:on

		loadProcessors();
	}

	private void registerProcessor(MutantDetectorProcessor processor) {
		processors.add(processor);
	}

	void loadProcessors() {
		MutantDetectorProcessor horizontal = new HorizontalSequenceProcessor(context);
		registerProcessor(horizontal);
		MutantDetectorProcessor vertical = new VerticalSequenceProcessor(context);
		registerProcessor(vertical);
		MutantDetectorProcessor diagnonal = new DianonalSequenceProcessor(context);
		registerProcessor(diagnonal);
		MutantDetectorProcessor diagnonalUP = new DianonalUpSequenceProcessor(context);
		registerProcessor(diagnonalUP);
	}

	public boolean isMutante() {
		for (MutantDetectorProcessor processor : processors) {
			// TODO Evoluir para processamento assincrono e adicionar observable para
			// interroper os processos
			processor.searchMutanteSequences();
			if (processor.hasMatchSequencesMutant()) {
				break;
			}
		}

		// TODO - implementar controller observable para esperar o retorno dos processos
		// TODO - remover os breaks espealhados
		return context.getMatchs() >= context.getCountSequencesMatchMutant();
	}

}
