package it.valeriobruno.aoc2019;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;


public class Day2Test {

	@Test
	public void test1() {
		List<Integer> status = 
		Arrays.asList( 1,0,0,0,99);
		
		Day2 program = new Day2(status);
		
		program.run();
		
		List<Integer> expected = Arrays.asList( 2,0,0,0,99);;
		
		assertEquals(expected,status);		
	}
	
	@Test
	public void test2() {
		//2,3,0,3,99
		List<Integer> status = 
		Arrays.asList( 2,3,0,3,99);
		
		Day2 program = new Day2(status);
		
		program.run();
		
		List<Integer> expected = Arrays.asList( 2,3,0,6,99);
		
		assertEquals(expected,status);
	}
	
	//2,4,4,5,99,0 becomes 2,4,4,5,99,9801
	@Test
	public void test3() {
		//2,3,0,3,99
		List<Integer> status = 
		Arrays.asList(2,4,4,5,99,0);
		
		Day2 program = new Day2(status);
		
		program.run();
		
		List<Integer> expected = Arrays.asList( 2,4,4,5,99,9801);
		
		assertEquals(expected,status);
	}
	
	//1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99
	@Test
	public void test4() {

		List<Integer> status = 
		Arrays.asList(1,1,1,4,99,5,6,0,99);
		
		Day2 program = new Day2(status);
		
		program.run();
		
		List<Integer> expected = Arrays.asList(30,1,1,4,2,5,6,0,99);
		
		assertEquals(expected,status);
	}
	
}
