package it.valeriobruno.aoc2020;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.valeriobruno.aoc2020.Passport;

class Day4Test {

	@Test
	void testValid() {
		Passport p = new Passport("pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980 " + "hcl:#623a2f");
		assertTrue(p.hasAllValidFields());

		p = new Passport("eyr:2029 ecl:blu cid:129 byr:1989 " + "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm");
		assertTrue(p.hasAllValidFields());

		p = new Passport(
				"hcl:#888785 " + "hgt:164cm byr:2001 iyr:2015 cid:88 " + "pid:545766238 ecl:hzl " + "eyr:2022");

		assertTrue(p.hasAllValidFields());

		p = new Passport("iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719");
		assertTrue(p.hasAllValidFields());
	}

}
