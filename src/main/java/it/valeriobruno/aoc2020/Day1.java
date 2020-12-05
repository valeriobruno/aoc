package it.valeriobruno.aoc2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public class Day1 {
	
	public static List<Integer> findSumOfTwo(Set<Integer> inputs, int total)
	{
		for (Integer x : inputs)
		{
			int other = total - x;			
			if(inputs.contains(Integer.valueOf(other))) {
					System.out.printf("%d + %d = %d\n",x,other, total);
					System.out.printf("%d * %d = %d\n",x,other, x*other);
					
					return Arrays.asList(x,other);
					
					
			}
		}
		return null;
	}
	
	public static List<Integer> findSumOfThree(Set<Integer> inputs, int total)
	{
		for (Integer x : inputs)
		{
			int totalMinusX = total - x;
			List<Integer> otherTwo = findSumOfTwo(inputs,totalMinusX);
			if( otherTwo == null)
				continue;
			
		
			System.out.printf("%d+%d+%d=%d\n",x,otherTwo.get(0), otherTwo.get(1), x+otherTwo.get(0)+ otherTwo.get(1));		
			System.out.printf("%d*%d*%d=%d\n",x,otherTwo.get(0), otherTwo.get(1), x*otherTwo.get(0)* otherTwo.get(1));
			return Arrays.asList(x,otherTwo.get(0), otherTwo.get(1));
			
		}
		throw new RuntimeException("Invalid input");
	}
	
	
	public static void main(String[] args) throws IOException {
		Set<Integer> inputs = Files.readAllLines(Paths.get("./input1-1.txt")).stream().map( x -> Integer.valueOf(x)).collect(Collectors.toSet());
	
		findSumOfThree(inputs, 2020);
		
		
	}
	
}
