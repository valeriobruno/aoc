package aoc2020;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.TreeSet;

public class Day5 {

	public static void main(String[] args) throws IOException {

		TreeSet<Seat> allSeats = new TreeSet<Seat>();

		Scanner scanner = new Scanner(Paths.get("./input5.txt"));
		while (scanner.hasNextLine()) {

			CharSequence line = scanner.nextLine();
			Seat seat = Seat.moveTo(line);

			allSeats.add(seat);
		}
		System.out.println(allSeats.first().seatId());

		System.out.println(allSeats.last().seatId());

		
			for (int y = 0; y < 128; y++)
				for (int x = 0; x < 8; x++){
				Seat s = new Seat(x, y);
				if (!allSeats.contains(s)) {
					System.out.println(s);
				}
			}

	}

}
