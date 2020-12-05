package it.valeriobruno.aoc2019.crossed.wires;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CrossedWires {

	private WirePath path1;
	private WirePath path2;

	public CrossedWires(WirePath path1, WirePath path2) {
		this.path1 = path1;
		this.path2 = path2;
	}

	public Coordinates findCrossingCloserToOrigin() {
		TreeSet<Coordinates> crossings = calculateCrossings();

		return crossings.first();
	}

	public Coordinates findCrossingReachableInLeastSteps() {
		TreeSet<Coordinates> crossings = calculateCrossings();

		TreeSet<Coordinates> crossingBySteps = new TreeSet<>(new LessStepsComparator());
		crossingBySteps.addAll(crossings);
		return crossingBySteps.first();
	}

	public long stepsToCrossing(Coordinates destination) {
		return path1.calculateSteps(destination) + path2.calculateSteps(destination);
	}

	public TreeSet<Coordinates> calculateCrossings() {
		List<Segment> s1 = path1.calculateSegments();
		List<Segment> s2 = path2.calculateSegments();

		TreeSet<Coordinates> crossings = new TreeSet<>();

		List<Segment> verticals = s1.stream().filter(s -> s.getDirection() == Direction.DOWN)
				.collect(Collectors.toList());
		List<Segment> horizontals = s2.stream().filter(s -> s.getDirection() == Direction.RIGHT)
				.collect(Collectors.toList());

		for (Segment v : verticals)
			for (Segment h : horizontals) {
				Coordinates crossing = v.crosses(h);
				if (crossing != null)
					crossings.add(crossing);
			}

		verticals = s2.stream().filter(s -> s.getDirection() == Direction.DOWN).collect(Collectors.toList());
		horizontals = s1.stream().filter(s -> s.getDirection() == Direction.RIGHT).collect(Collectors.toList());

		for (Segment v : verticals)
			for (Segment h : horizontals) {
				Coordinates crossing = v.crosses(h);
				if (crossing != null)
					crossings.add(crossing);
			}
		crossings.remove(new Coordinates(0, 0));
		return crossings;
	}

	class LessStepsComparator implements Comparator<Coordinates> {
		
		@Override
		public int compare(Coordinates c1, Coordinates c2) {
			long d1 = stepsToCrossing(c1);
			long d2 = stepsToCrossing(c2);

			if(d1 > d2)
				return 1;
			else if (d2 > d1)
				return -1;
			else return 0;
		}
	}
	
	public static void main(String[] args) throws IOException {

		List<String> wires = Files.readAllLines(Paths.get("./input-crossed-wires.txt"));

		WirePath path1 = new WirePath(Arrays.asList(wires.get(0).split(",")));

		WirePath path2 = new WirePath(Arrays.asList(wires.get(1).split(",")));

		CrossedWires cw = new CrossedWires(path1, path2);

		System.out.println(cw.findCrossingCloserToOrigin().getManhattanDistance());

		System.out.println(cw.stepsToCrossing(cw.findCrossingReachableInLeastSteps()));

	}
}
