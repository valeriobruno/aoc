package it.valeriobruno.aoc2019.crossed.wires;

import java.util.ArrayList;
import java.util.List;

public class WirePath {

	private List<String> moves;

	public WirePath(List<String> moves) {
		this.moves = moves;
	}

	public List<Segment> calculateSegments() {
		Coordinates startingPoint = new Coordinates(0, 0);
		List<Segment> segments = new ArrayList<>(moves.size());
		Segment s;

		for (String move : moves) {
			Long steps = Long.parseLong(move.substring(1));
			switch (move.charAt(0)) {
			case 'U':
				startingPoint = startingPoint.moveUp(steps);
				s = new Segment(Direction.DOWN, startingPoint, steps);
				segments.add(s);
				break;
			case 'D':
				s = new Segment(Direction.DOWN, startingPoint, steps);
				segments.add(s);
				startingPoint = startingPoint.moveDown(steps);
				break;
			case 'L':
				startingPoint = startingPoint.moveLeft(steps);
				s = new Segment(Direction.RIGHT, startingPoint, steps);
				segments.add(s);
				break;
			case 'R':
				s = new Segment(Direction.RIGHT, startingPoint, steps);
				segments.add(s);
				startingPoint = startingPoint.moveRight(steps);
				break;
			default:
				throw new RuntimeException("Invalid direction in move");
			}
		}
		return segments;
	}

	public long calculateSteps(Coordinates destination) {
		boolean found = false;
		long stepsToDest = 0l;
		Coordinates startingPoint = new Coordinates(0, 0);
		Coordinates nextPoint;

		theloop: for (String move : moves) {
			Long moveSteps = Long.parseLong(move.substring(1));
			switch (move.charAt(0)) {
			case 'U':
				nextPoint = startingPoint.moveUp(moveSteps);
				if (destination.getX() == startingPoint.getX() && destination.getY() >= startingPoint.getY()
						&& destination.getY() <= nextPoint.getY()) {
					found = true;
					stepsToDest += destination.getY() - startingPoint.getY();
					break theloop;
				} else {
					startingPoint = nextPoint;
					stepsToDest += moveSteps;
				}
				break;
			case 'D':
				nextPoint = startingPoint.moveDown(moveSteps);
				if (destination.getX() == startingPoint.getX() && destination.getY() <= startingPoint.getY()
						&& destination.getY() >= nextPoint.getY()) {
					found = true;
					stepsToDest += startingPoint.getY() - destination.getY();
					break theloop;
				} else {
					startingPoint = nextPoint;
					stepsToDest += moveSteps;
				}
				break;
			case 'L':
				nextPoint = startingPoint.moveLeft(moveSteps);
				if (destination.getY() == startingPoint.getY() && destination.getX() <= startingPoint.getX()
						&& destination.getX() >= nextPoint.getX()) {
					found = true;
					stepsToDest += startingPoint.getX() - destination.getX();
					break theloop;
				} else {
					startingPoint = nextPoint;
					stepsToDest += moveSteps;
				}
				break;
			case 'R':
				nextPoint = startingPoint.moveRight(moveSteps);
				if (destination.getY() == startingPoint.getY() && destination.getX() >= startingPoint.getX()
						&& destination.getX() <= nextPoint.getX()) {
					found = true;
					stepsToDest += destination.getX() - startingPoint.getX();
					break theloop;
				} else {
					startingPoint = nextPoint;
					stepsToDest += moveSteps;
				}
				break;
			default:
				throw new RuntimeException("Invalid direction in move");
			}
		}
		if (found)
			return stepsToDest;
		else
			return Long.MAX_VALUE;

	}

}
