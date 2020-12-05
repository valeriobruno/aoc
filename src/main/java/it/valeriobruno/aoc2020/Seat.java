package it.valeriobruno.aoc2020;

public class Seat implements Comparable<Seat>
{
	int left=0;
	int right=8;
	int top=0;
	int bot=128;
	
	public Seat() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Seat(int left, int top) {
		super();
		this.left = left;
		this.top = top;
	}



	public void keepFront()
	{
		bot = top+(bot-top) /2;			
	}
	
	public void keepBack()
	{
		top = bot-(bot-top) /2;
		
	}
	
	public void keepLeft()
	{
		right = left+(right-left)/2;
	}
	
	public void keepRight()
	{
		left =right-( right-left)/2;
	}

	public int getLeft() {
		return left;
	}

	public int getTop() {
		return top;
	}

	public int compareTo(Seat o) {
		Integer thisval = seatId();
		Integer oval = o.seatId(); 
		return thisval.compareTo(oval);
	}
	
	public int seatId()
	{
		return top*8+left;
	}
	
	static Seat moveTo(CharSequence line)
	{
		Seat seat = new Seat();
		
		for(int i=0;i<line.length();i++)
		{
			char b = line.charAt(i);
			
			switch(b)
			{
			case 'L':
				seat.keepLeft();
				break;
			case 'R':
				seat.keepRight();
				break;
			case 'F':
				seat.keepFront();
				break;
			case 'B':
				seat.keepBack();
				break;					
			}
		}
			return seat;
	}
	
	@Override
	public String toString() {
		return top+" "+left+":"+seatId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + left;
		result = prime * result + top;
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
		Seat other = (Seat) obj;
		if (left != other.left)
			return false;
		if (top != other.top)
			return false;
		return true;
	}
	
	
	
}