package it.valeriobruno.aoc2019.intcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.valeriobruno.aoc2019.intcode.io.IntCodeInput;
import it.valeriobruno.aoc2019.intcode.io.IntCodeOutput;
import it.valeriobruno.aoc2019.intcode.io.IntCodeStandardInputOutput;

public class IntCodeComputer {

	private List<Integer> program;
	private IntCodeOutput outputSystem;
	private IntCodeInput inputSystem;
	
	private Integer lastOutput;
	private int pos;

	public IntCodeComputer(List<Integer> program, IntCodeInput inputSystem, IntCodeOutput outputSystem) {
		this.program = program;
		this.inputSystem = inputSystem;
		this.outputSystem = outputSystem;

	}

	public Integer run() {
		return execute();
	}

	private Integer execute() {
		while (true) {
			int nextIpIn;

			int instruction = program.get(pos);
			int opcode = instruction % 100;
			int m1 = (instruction % 1000) / 100;
			int m2 = (instruction % 10000) / 1000;
			int m3 = (instruction % 100000) / 10000;

			switch (opcode) {
			case 1:
				program.set(readParameter(pos, 3, 1), readParameter(pos, 1, m1) + readParameter(pos, 2, m2));
				nextIpIn = 4;
				break;
			case 2:
				program.set(readParameter(pos, 3, 1), readParameter(pos, 1, m1) * readParameter(pos, 2, m2));
				nextIpIn = 4;
				break;
			case 3:
				nextIpIn = 2;
				program.set(readParameter(pos, 1, 1), readInputNumber());
				break;
			case 4:
				nextIpIn = 2;
				lastOutput = readParameter(pos, 1, m1);
				outputSystem.writeInt(lastOutput);
				break;
			case 5:
				if (readParameter(pos, 1, m1) != 0) {
					int ip = readParameter(pos, 2, m2);
					nextIpIn = ip - pos;
				} else
					nextIpIn = 3;
				break;
			case 6:
				if (readParameter(pos, 1, m1) == 0) {
					int ip = readParameter(pos, 2, m2);
					nextIpIn = ip - pos;
				} else
					nextIpIn = 3;
				break;
			case 7:
				// if the first parameter is less than the second parameter, it
				// stores 1 in the position given by the third parameter.
				// Otherwise, it stores 0
				if (readParameter(pos, 1, m1) < readParameter(pos, 2, m2))
					program.set(readParameter(pos, 3, 1), 1);
				else
					program.set(readParameter(pos, 3, 1), 0);
				nextIpIn = 4;
				break;
			case 8:
				// if the first parameter is equal to the second parameter, it
				// stores 1 in the position given by the third parameter.
				// Otherwise, it stores 0.
				if (readParameter(pos, 1, m1) == readParameter(pos, 2, m2))
					program.set(readParameter(pos, 3, 1), 1);
				else
					program.set(readParameter(pos, 3, 1), 0);
				nextIpIn = 4;
				break;
			case 99:
				// System.out.println("Program finished");
				return lastOutput;
			default:
				throw new RuntimeException("Invalid opCode: " + opcode);
			}
			pos = pos + nextIpIn;

		}
	}

	private int readParameter(int ip, int parm, int mod) {
		int value;
		if (mod == 0)
			value = program.get(program.get(ip + parm));
		else
			value = program.get(ip + parm);

		return value;
	}

	private int readInputNumber() {
		return inputSystem.readInt();
	}

	public void dumpMemory() {
		for (int i = 0; i < program.size(); i++) {
			if (i % 4 == 0)
				System.out.println();

			System.out.print(program.get(i) + " ");
		}
		System.out.println("-------------------------------");
	}

	public static void main(String[] args) throws IOException {
		List<Integer> inputs = readProgramFromFile(Paths.get("./input-sunny.txt"));
	
		IntCodeStandardInputOutput io = new IntCodeStandardInputOutput();
		IntCodeComputer program = new IntCodeComputer(inputs,io, io);

		// program.dumpMemory();
		program.run();
		// program.dumpMemory();

	}

	public static List<Integer> readProgramFromFile(Path path) throws IOException {
		String line = Files.readAllLines(path).get(0);

		List<Integer> inputs = Stream.of(line.split(",")).map(x -> Integer.valueOf(x)).collect(Collectors.toList());
		return inputs;
	}

}
