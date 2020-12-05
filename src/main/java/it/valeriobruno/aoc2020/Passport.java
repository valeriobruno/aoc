package it.valeriobruno.aoc2020;

import java.util.HashMap;

public class Passport {

	HashMap<String, String> data;

	public Passport(String data) {
		this.data = new HashMap<String, String>();
		String[] pairs = data.split(" ");

		for (String entry : pairs) {
			String[] kv = entry.split(":");
			this.data.put(kv[0], kv[1]);
		}
	}

	public boolean hasAllFields() {
		/*
		 * byr (Birth Year) iyr (Issue Year) eyr (Expiration Year) hgt (Height) hcl
		 * (Hair Color) ecl (Eye Color) pid
		 */
		return data.containsKey("byr") && data.containsKey("iyr") && data.containsKey("eyr")
				&& data.containsKey("hgt") && data.containsKey("hcl") && data.containsKey("ecl")
				&& data.containsKey("pid");
	}

	Integer extract4DigitNum(String field) {
		Integer result = null;

		String byr = data.get(field);
		if (byr != null)
			if (byr.matches("\\d\\d\\d\\d")) {
				result = Integer.parseInt(byr);
			}

		return result;
	}

	// byr (Birth Year) - four digits; at least 1920 and at most 2002.
	boolean hasValidByr() {
		Integer byrNum = extract4DigitNum("byr");
		return byrNum != null && byrNum >= 1920 && byrNum <= 2002;
	}

	// iyr (Issue Year) - four digits; at least 2010 and at most 2020.
	boolean hasValidIyr() {
		Integer num = extract4DigitNum("iyr");
		return num != null && num >= 2010 && num <= 2020;
	}

	// eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
	boolean hasValidEyr() {
		Integer num = extract4DigitNum("eyr");
		return num != null && num >= 2020 && num <= 2030;
	}

	/*
	 * hgt (Height) - a number followed by either cm or in: If cm, the number must
	 * be at least 150 and at most 193. If in, the number must be at least 59 and at
	 * most 76.
	 */
	boolean hasValidHgt() {
		boolean valid = false;

		String hgt = data.get("hgt");
		if (hgt != null && hgt.matches("\\d+(cm|in)")) {
			int height = Integer.parseInt(hgt.substring(0, hgt.length() - 2));

			if (hgt.indexOf("cm") > 0)
				valid = height >= 150 && height <= 193;
			else
				valid = height >= 59 && height <= 76;
		}
		
		return valid;
	}
	// * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f. ecl
	boolean hasValidHcl()
	{
		String hcl = data.get("hcl");
		return hcl != null && hcl.matches("#[0-9a-f]{6}+");
	}
	
	//  * (Eye Color) - exactly one of: amb blu brn gry grn hzl oth. 
	boolean hasValidEcl()
	{
		String ecl = data.get("ecl");
		return ecl != null && (ecl.equals("amb")||ecl.equals("blu")||ecl.equals("brn")||
				ecl.equals("gry")||ecl.equals("grn")||ecl.equals("hzl")||ecl.equals("oth"));
	}
	
	//pid (Passport ID) - a nine-digit number, including leading zeroes
	boolean hasValidPid()
	{
		String pid = data.get("pid");
		return pid != null && pid.matches("\\d\\d\\d\\d\\d\\d\\d\\d\\d");
	}

	public boolean hasAllValidFields() {
		return hasValidByr() && hasValidIyr() && hasValidEyr() && hasValidHgt() && hasValidHcl()
				&& hasValidEcl() && hasValidPid();
	}

	public String toString() {
		return data.toString();
	}
}