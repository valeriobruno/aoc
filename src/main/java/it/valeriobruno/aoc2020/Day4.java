package it.valeriobruno.aoc2020;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day4 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(Paths.get("./input4.txt"));

		List<Passport> passports = new LinkedList<>();

		StringBuilder passBuilder = new StringBuilder();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();

			if ("".equals(line)) {

				Passport passport = new Passport(passBuilder.toString());
				passports.add(passport);
				passBuilder = new StringBuilder();
			} else {
				if (passBuilder.toString().length() > 0)
					passBuilder.append(" ");

				passBuilder.append(line);
			}

		}

		if (passBuilder.toString().length() > 0) {
			Passport passport = new Passport(passBuilder.toString());
			passports.add(passport);
		}

		System.out.println(passports.size());
		List<Passport> validPassports = passports.stream().filter(p -> p.hasAllValidFields()).collect(Collectors.toList());

		System.out.println(validPassports.size());

	}
}
