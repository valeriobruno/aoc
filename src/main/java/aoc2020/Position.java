package aoc2020;

public class Position
{
	int x,y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position move(int deltax,int deltay)
	{
		Position newPos = new Position(this.x+deltax,this.y+deltay);
		return newPos;
	}
	
}