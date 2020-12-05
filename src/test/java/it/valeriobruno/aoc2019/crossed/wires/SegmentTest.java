package it.valeriobruno.aoc2019.crossed.wires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SegmentTest {

	@Test
	public void testSegmentsWithSameDirectionNeverCrosses() {
		Segment s1 = new Segment(Direction.DOWN, new Coordinates(0, 100), 200);
		Segment s2 = new Segment(Direction.DOWN, new Coordinates(1, 100), 200);

		assertNull(s1.crosses(s2));
		assertNull(s2.crosses(s1));

		s1 = new Segment(Direction.RIGHT, new Coordinates(1000, 0), 2000);
		s2 = new Segment(Direction.RIGHT, new Coordinates(1000, 1), 2000);

		assertNull(s1.crosses(s2));
		assertNull(s2.crosses(s1));
	}

	@Test()
	public void testOverWritingSegments() {
		final Segment s1 = new Segment(Direction.DOWN, new Coordinates(0, 100), 200);
		final Segment s2 = new Segment(Direction.DOWN, new Coordinates(0, 10), 200);
		
		assertThrows(RuntimeException.class, () -> s1.crosses(s2));
		
		
	}
	
	@Test
	public void testNaturalCross()
	{
		Segment s1 = new Segment(Direction.DOWN, new Coordinates(10, 100), 200);
		Segment s2 = new Segment(Direction.RIGHT, new Coordinates(0, 80), 200);
		
		Coordinates crossing = s1.crosses(s2);
		
		assertEquals(new Coordinates(10, 80),crossing);
		assertEquals(crossing, s2.crosses(s1));
		
		
	}

}
