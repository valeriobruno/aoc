package aoc2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day3 {

	static class TheMap
	{
		char [][] greenfield;
		
		static TheMap fromLines(List<String> lines)
		{
			TheMap map = new TheMap();
			map.greenfield = new char[lines.get(0).length()][lines.size()];
			
			for(int y=0;y<lines.size();y++)
				for(int x=0;x<lines.get(0).length();x++)
				{
					map.greenfield[x][y] = lines.get(y).charAt(x);
				}
			return map;
		}
		
		boolean isTree(int x, int y)
		{
			x = x % greenfield.length;
			y = y % greenfield[0].length;
			return greenfield[x][y] == '#';
		}
		
		boolean isTree(Position p)
		{
			return isTree(p.x,p.y);
		}
	}
	
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("./input3.txt"));
		//.stream().map(s -> s.toCharArray()).collect(Collectors.toList());
		TheMap map = TheMap.fromLines(lines);
		
		/*
		 * Right 1, down 1.
Right 3, down 1. (This is the slope you already checked.)
Right 5, down 1.
Right 7, down 1.
Right 1, down 2
		 */
		long s1 = countTree(map,1,1);
		long s2 =countTree(map,3,1);
		long s3 =countTree(map,5,1);
		long s4 =countTree(map,7,1);
		long s5 =countTree(map,1,2);
		
		System.out.println(s1*s2*s3*s4*s5);
	
		
		
	}
	private static int countTree(TheMap map, int deltax,int deltay) {
		Position position = new Position(0,0);
		
		int treeCounter = 0;
		while(position.y < map.greenfield[0].length)
		{
			
			if( map.isTree(position))
				treeCounter++;
			position = position.move(deltax,deltay);
		}
		
		System.out.println(treeCounter);
		return treeCounter;		
	}
}
