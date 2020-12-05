package it.valeriobruno.aoc2019.crossed.wires;

public class Segment
{
	private final Direction direction;
	private final Coordinates startingPoint;
	private final long length;
	
	public Segment(Direction direction, Coordinates startingPoint, long length) {
		this.direction = direction;
		this.startingPoint = startingPoint;
		this.length = length;
	}

	public Direction getDirection() {
		return direction;
	}

	public Coordinates getStartingPoint() {
		return startingPoint;
	}

	public long getLength() {
		return length;
	}
	
	public Coordinates crosses(Segment other)
	{

		
		if(this.direction.equals(other.direction))
		{
			Segment smaller;
			Segment longer;
			
			if(this.length > other.length)
			{
				longer = this;
				smaller =other;
			}else
			{

				longer = other;
				smaller = this;	
			}
			
			if(this.direction.equals(Direction.DOWN) && this.startingPoint.getX() == other.startingPoint.getX())
			{
				long smallerStart = smaller.startingPoint.getY();
				long smallerEnd = smaller.startingPoint.getY()+smaller.length;
				long longerStart = longer.startingPoint.getY();
				long longerEnd = longerStart+longer.length;
				if( (smallerStart < longerStart && smallerStart > longerEnd) ||
						(smallerEnd < longerStart && smallerEnd > longerEnd)
						)
					throw new RuntimeException("Overlapping lines");
			}
			else if(this.direction.equals(Direction.RIGHT) && this.startingPoint.getY() == other.getStartingPoint().getY())
			{
				//TODO
				throw new RuntimeException("Maybe Overlapping lines");
			}
		}
		else
		{
			Segment horizontal = this.direction == Direction.RIGHT ? this : other;
			Segment vertical = this.direction == Direction.DOWN ? this : other;
			
			if(horizontal.getStartingPoint().getX() <= vertical.startingPoint.getX() && 
					vertical.startingPoint.getX()<= horizontal.getStartingPoint().getX()+horizontal.getLength() &&
				vertical.startingPoint.getY() >= horizontal.startingPoint.getY() && 
				horizontal.startingPoint.getY() >= vertical.startingPoint.getY()-vertical.length)
				return new Coordinates(vertical.startingPoint.getX(), horizontal.startingPoint.getY());
		}
		
		return null;
	}
}