package it.valeriobruno.aoc2019.crossed.wires;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
public class CoordinatesTest {

	@Test
	public void testMovements() {
		Coordinates c0 = new Coordinates(0, 0);
		
		Coordinates c1 = c0.moveLeft(12);
		assertEquals(-12l, c1.getX());
		assertEquals(0l, c1.getY());
		
		Coordinates c2 = c1.moveRight(32);
		assertEquals(20l, c2.getX());
		assertEquals(0l, c2.getY());
		
		Coordinates c3 = c2.moveDown(45);
		assertEquals(20l, c3.getX());
		assertEquals(-45l, c3.getY());
		
		Coordinates c4 = c3.moveUp(102);
		assertEquals(20l, c4.getX());
		assertEquals(57l, c4.getY());
		
		
	}

	@Test
	public void testInsideSegment()
	{
		Coordinates coord = new Coordinates(34, 22);
		
		Segment seg = new Segment(Direction.DOWN, new Coordinates(33, 200), 100000);		
		assertFalse(coord.isInside(seg));
		
		seg = new Segment(Direction.DOWN, new Coordinates(34, 20),100);
		assertFalse(coord.isInside(seg));
		
		seg = new Segment(Direction.DOWN,new Coordinates(34, 40),21);
		assertTrue(coord.isInside(seg));
		
		seg = new Segment(Direction.RIGHT, new Coordinates(30, 21), 100000);
		assertFalse(coord.isInside(seg));
		
		seg = new Segment(Direction.RIGHT, new Coordinates(30, 22), 100000);
		assertTrue(coord.isInside(seg));
		
		seg = new Segment(Direction.RIGHT, new Coordinates(35, 22), 100000);
		assertFalse(coord.isInside(seg));
	}
}
