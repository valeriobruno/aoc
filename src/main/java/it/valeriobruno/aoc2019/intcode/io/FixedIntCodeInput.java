package it.valeriobruno.aoc2019.intcode.io;

import java.util.ArrayList;
import java.util.List;

public class FixedIntCodeInput implements IntCodeInput, IntCodeOutput {

	private final List<Integer> inputs;
	private final List<Integer> outputs;
	
	
	public FixedIntCodeInput(List<Integer> inputs) {
		this.inputs = inputs;
		this.outputs = new ArrayList<>();
	}

	@Override
	public int readInt() {
		return inputs.remove(0);
	}

	@Override
	public void writeInt(int i) {
		outputs.add(i);

	}

	public List<Integer> getOutput() {
		return outputs;	
	}

}
