package it.valeriobruno.aoc2019.crossed.wires;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
public class CrossedWiresTest {

	@Test
	public void testCloserToOrigin1() {
		WirePath p1 = new WirePath(Arrays.asList("R75","D30","R83","U83","L12","D49","R71","U7","L72"));
		WirePath p2 = new WirePath(Arrays.asList("U62","R66","U55","R34","D71","R55","D58","R83"));
		
		CrossedWires cw = new CrossedWires(p1, p2);
		Coordinates crossing = cw.findCrossingCloserToOrigin();

		assertEquals(159l, crossing.getManhattanDistance());
	}


	@Test
	public void testCloserToOrigin2() {
		WirePath p1 = new WirePath(Arrays.asList("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"));
		WirePath p2 = new WirePath(Arrays.asList("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"));
		
		CrossedWires cw = new CrossedWires(p1, p2);
		Coordinates crossing = cw.findCrossingCloserToOrigin();

		assertEquals(135l, crossing.getManhattanDistance());
	}

	
	
	@Test
	public void testMinSteps1() {
		WirePath p1 = new WirePath(Arrays.asList("R75","D30","R83","U83","L12","D49","R71","U7","L72"));
		WirePath p2 = new WirePath(Arrays.asList("U62","R66","U55","R34","D71","R55","D58","R83"));
		
		CrossedWires cw = new CrossedWires(p1, p2);
		Coordinates crossing = cw.findCrossingReachableInLeastSteps();
		TreeSet<Coordinates> crossings = cw.calculateCrossings();
		List<Long> steps = crossings.stream().map(c -> cw.stepsToCrossing(c)).collect(Collectors.toList());
		assertEquals(610l, cw.stepsToCrossing(crossing));
	}
	
	@Test
	public void testMinSteps2() {
		WirePath p1 = new WirePath(Arrays.asList("R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"));
		WirePath p2 = new WirePath(Arrays.asList("U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"));
		
		CrossedWires cw = new CrossedWires(p1, p2);
		Coordinates crossing = cw.findCrossingReachableInLeastSteps();

		assertEquals(410l, cw.stepsToCrossing(crossing));
	}

}
