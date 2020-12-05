package it.valeriobruno.aoc2019.crossed.wires;

public class Coordinates implements Comparable<Coordinates>
{
	private long x,y;

	public Coordinates(long x, long y) {
		this.x = x;
		this.y = y;
	}
	
	public  Coordinates moveDown(long steps) {
		return new Coordinates(x, y-steps);
	}
	
	public  Coordinates moveUp(long steps) {
		return new Coordinates(x, y+steps);
	}
	public  Coordinates moveLeft(long steps) {
		return new Coordinates(x-steps, y);
	}
	
	public  Coordinates moveRight(long steps) {
		return new Coordinates(x+steps, y);
	}

	public long getX() {
		return x;
	}

	public long getY() {
		return y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (x ^ (x >>> 32));
		result = prime * result + (int) (y ^ (y >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{" + x + " " + y + "}";
	}
	
	public long getManhattanDistance()
	{
		return Math.abs(x)+Math.abs(y);
	}

	@Override
	public int compareTo(Coordinates o) {
		long diff = 
				this.getManhattanDistance() - o.getManhattanDistance();
		if(diff >0l)
			return 1;
		else if (diff <0l)
			return -1;
		else return 0;
	}
	
	public boolean isInside(Segment s)
	{
		boolean res = false;
		
		long segX = s.getStartingPoint().getX();
		long segY = s.getStartingPoint().getY();
		if(s.getDirection().equals(Direction.DOWN))
		{
			if(x == segX &&
					segY >= y && y >= segY-s.getLength() )
				res = true;				
		}
		else
		{
			if(y == segY && segX <= x && x <= segX+s.getLength())
				res = true;
		}
		
		return res;
	}
	
	
	
}