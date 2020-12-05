package it.valeriobruno.aoc2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day2 {
	
	private List<Integer> inputs;


	public Day2(List<Integer> inputs) {
		this.inputs = inputs;
		
	}
	
	public void run()
	{
		execute(0);
	}
	
	private void execute(int pos)
	{
		switch(inputs.get(pos))
		{
		case 1:
			System.out.println("x"+inputs.get(pos+3)+" = x"+inputs.get(pos+1)+" + x"+inputs.get(pos+2));
			inputs.set(inputs.get(pos+3), inputs.get(inputs.get(pos+1))+inputs.get(inputs.get(pos+2)));
			break;
		case 2:
			System.out.println("x"+inputs.get(pos+3)+" = x"+inputs.get(pos+1)+" * x"+inputs.get(pos+2));
			inputs.set(inputs.get(pos+3), inputs.get(inputs.get(pos+1))*inputs.get(inputs.get(pos+2)));
			break;
		case 99:
			return;
		default:
			throw new RuntimeException("Invalid opCode");
		}
		execute(pos+4);
	}
	
	public void dumpMemory()
	{
		for(int i=0;i<inputs.size();i++)
		{
			if(i % 4 == 0)
				System.out.println();
			
			System.out.print(inputs.get(i)+" ");
		}
		System.out.println("-------------------------------");
	}
	
	
 public static void main(String[] args) throws IOException {
	String line = Files.readAllLines(Paths.get("./input.txt")).get(0);
	
	List<Integer> inputs = Stream.of(line.split(",")).map( x -> Integer.valueOf(x)).collect(Collectors.toList());
	inputs.set(1,12);
	inputs.set(2,2);
	
	
	Day2 program = new Day2(inputs);

	program.dumpMemory();
	program.run();
	program.dumpMemory();
	
}
}
