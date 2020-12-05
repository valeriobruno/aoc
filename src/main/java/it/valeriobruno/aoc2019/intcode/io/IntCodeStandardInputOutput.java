package it.valeriobruno.aoc2019.intcode.io;

import java.util.Scanner;

public class IntCodeStandardInputOutput implements IntCodeInput, IntCodeOutput {

	private Scanner inputReader;
	
	public IntCodeStandardInputOutput() {
		inputReader = new Scanner(System.in);
	}
	
	@Override
	public int readInt() {
		System.out.print("\nInsert a number: ");
		return inputReader.nextInt();
	}

	@Override
	public void writeInt(int i) {
		System.out.println("Output: "+i);

	}

}
