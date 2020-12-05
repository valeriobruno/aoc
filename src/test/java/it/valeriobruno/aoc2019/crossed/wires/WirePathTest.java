package it.valeriobruno.aoc2019.crossed.wires;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class WirePathTest {

	@Test
	public void test() {
		WirePath path = new WirePath(Arrays.asList("D30","R83","U83","L12"));
		List<Segment> segments = path.calculateSegments();
		 assertEquals(4, segments.size());
		 
		 //start from 0,0
		 Segment s = segments.get(0);
		 assertEquals(30, s.getLength());
		 assertEquals(Direction.DOWN, s.getDirection());
		 assertEquals(new Coordinates(0,0), s.getStartingPoint());

		 //start from 0,-30
		 s = segments.get(1);
		 assertEquals(83, s.getLength());
		 assertEquals(Direction.RIGHT, s.getDirection());
		 assertEquals(new Coordinates(0,-30), s.getStartingPoint());

		 //starts from 83,-30
		 s = segments.get(2);
		 assertEquals(83, s.getLength());
		 assertEquals(Direction.DOWN, s.getDirection());
		 assertEquals(new Coordinates(83,53), s.getStartingPoint());

		 //starts from 83,53
		 s = segments.get(3);
		 assertEquals(12, s.getLength());
		 assertEquals(Direction.RIGHT, s.getDirection());
		 assertEquals(new Coordinates(71,53), s.getStartingPoint());

		 
	}

}
