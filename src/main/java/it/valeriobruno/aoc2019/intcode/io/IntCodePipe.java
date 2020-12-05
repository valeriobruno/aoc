package it.valeriobruno.aoc2019.intcode.io;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class IntCodePipe  implements IntCodeInput, IntCodeOutput{

	BlockingQueue<Integer> data;
	
	public IntCodePipe() {
		data = new ArrayBlockingQueue<>(100);
	}
	
	@Override
	public void writeInt(int i) {
		try {
			data.put(i);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int readInt() {
		try {
			return data.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
