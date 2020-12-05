package it.valeriobruno.aoc2019.ampification.circuits;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ThrustersTest {

	@Test
	public void testRunPhaseSequence() {


		Thrusters thru = new Thrusters(Arrays.asList(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0));
		int res = thru.runPhaseSequence(Arrays.asList(4,3,2,1,0));
		assertEquals(43210,res);
	}
	

	@Test
	public void testFindMax1() {


		Thrusters thru = new Thrusters(Arrays.asList(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0));
		int res = thru.findMaxPhaseSequence(0,4);
		assertEquals(43210,res);
	}
	
	@Test
	public void testFindMax2() {


		Thrusters thru = new Thrusters(Arrays.asList(3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0));
		int res = thru.findMaxPhaseSequence(0,4);
		assertEquals(54321,res);
	}
	
	@Test
	public void testFindMax3() {


		Thrusters thru = new Thrusters(Arrays.asList(3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,
				1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0));
		int res = thru.findMaxPhaseSequence(0,4);
		assertEquals(65210,res);
	}

	@Test
	public void testFindMax4
	()
	{
		Thrusters thru = new Thrusters(Arrays.asList(3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,
27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5));
		
		assertEquals(139629729, thru.findMaxPhaseSequence(5, 9));
	}

}
