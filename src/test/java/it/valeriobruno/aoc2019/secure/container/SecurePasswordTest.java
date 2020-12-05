package it.valeriobruno.aoc2019.secure.container;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SecurePasswordTest {

	@Test
	public void test() {
		SecurePassword sp = new SecurePassword();
		
		assertFalse(sp.patterns[1].matcher("111111").matches());		
		assertTrue(sp.patterns[1].matcher("110111").matches());
		assertTrue(sp.patterns[1].matcher("111011").matches());
		assertTrue(sp.patterns[1].matcher("110011").matches());
		assertTrue(sp.patterns[1].matcher("011011").matches());
		assertTrue(sp.patterns[1].matcher("001100").matches());
		
		assertFalse(sp.patterns[1].matcher("111101").matches());		
		assertFalse(sp.patterns[1].matcher("011101").matches());		
		assertFalse(sp.patterns[1].matcher("011111").matches());		
	}

}
