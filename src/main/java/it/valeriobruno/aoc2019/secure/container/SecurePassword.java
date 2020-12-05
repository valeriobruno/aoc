package it.valeriobruno.aoc2019.secure.container;

import java.util.regex.Pattern;

public class SecurePassword {

	Pattern[] patterns = { Pattern.compile("(\\A00[^0].*)|(.*[^0]00[^0].*)|(.*[^0]00\\Z)"),
			Pattern.compile("(\\A11[^1].*)|(.*[^1]11[^1].*)|(.*[^1]11\\Z)"),
			Pattern.compile("(\\A22[^2].*)|(.*[^2]22[^2].*)|(.*[^2]22\\Z)"),
			Pattern.compile("(\\A33[^3].*)|(.*[^3]33[^3].*)|(.*[^3]33\\Z)"),
			Pattern.compile("(\\A44[^4].*)|(.*[^4]44[^4].*)|(.*[^4]44\\Z)"),
			Pattern.compile("(\\A55[^5].*)|(.*[^5]55[^5].*)|(.*[^5]55\\Z)"),
			Pattern.compile("(\\A66[^6].*)|(.*[^6]66[^6].*)|(.*[^6]66\\Z)"),
			Pattern.compile("(\\A77[^7].*)|(.*[^7]77[^7].*)|(.*[^7]77\\Z)"),
			Pattern.compile("(\\A88[^8].*)|(.*[^8]88[^8].*)|(.*[^8]88\\Z)"),
			Pattern.compile("(\\A99[^9].*)|(.*[^9]99[^9].*)|(.*[^9]99\\Z)") };

	public boolean digitsNeverDecrease(String pass) {
		char lastChar = pass.charAt(0);
		for (int i = 1; i < 6; i++) {
			char nextChar = pass.charAt(i);
			if (lastChar > nextChar)
				return false;
			else
				lastChar = nextChar;
		}

		return true;
	}

	public boolean twoAdjacientDigitsAreSame(String pass) {
		char lastChar = pass.charAt(0);
		for (int i = 1; i < 6; i++) {
			char nextChar = pass.charAt(i);
			if (nextChar == lastChar)
				return true;
			else
				lastChar = nextChar;
		}
		return false;
	}

	public boolean onlyTwoAdjacientDigitsAreSame(String pass) {
		boolean found = false;

		for (Pattern p : patterns) {
			if (p.matcher(pass).matches()) {
				found = true;
				break;
			}
		}
		return found;
	}

	public static void main(String[] args) {

		SecurePassword sp = new SecurePassword();
		int counter = 0;

		for (int pass = 158126; pass <= 624574; pass++) {
			String strPass = String.valueOf(pass);

			if (sp.digitsNeverDecrease(strPass) && sp.onlyTwoAdjacientDigitsAreSame(strPass)) {
				counter++;
			}
		}

		System.out.println(counter);
		System.out.println(sp.digitsNeverDecrease("112233") && sp.onlyTwoAdjacientDigitsAreSame("112233"));
		System.out.println(sp.digitsNeverDecrease("123444") && sp.onlyTwoAdjacientDigitsAreSame("123444"));
		System.out.println(sp.digitsNeverDecrease("111122") && sp.onlyTwoAdjacientDigitsAreSame("111122"));

	}

}
