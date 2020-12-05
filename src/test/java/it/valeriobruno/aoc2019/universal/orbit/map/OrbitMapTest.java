package it.valeriobruno.aoc2019.universal.orbit.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OrbitMapTest {

	@Test
	public void testStepCount() {
		OrbitMap map = new OrbitMap();
		map.addOrbit("COM", "B");
		map.addOrbit("B", "C");
		map.addOrbit("C", "D");
		map.addOrbit("D", "E");
		map.addOrbit("E", "F");
		map.addOrbit("B", "G");
		map.addOrbit("G", "H");
		map.addOrbit("D", "I");
		map.addOrbit("E", "J");
		map.addOrbit("J", "K");
		map.addOrbit("K", "L");
		map.addOrbit("K", "YOU");
		map.addOrbit("I", "SAN");
		
		assertEquals(4, map.minTransfers("YOU", "SAN"));
	}

}
