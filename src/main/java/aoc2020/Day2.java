package aoc2020;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {

	static class Policy
	{
		int min;
		int max;
		char letter;
		String pwd;
		
		public boolean isValid()
		{
			int counter=0;
			for (int i=0;i<pwd.length();i++)
			{
				if(pwd.charAt(i) == letter)
					counter ++;
			}
			
			return counter >= min && counter <=max;
		}
		
		public boolean isValid2()
		{
		
			
			return Boolean.logicalXor(pwd.charAt(min-1) ==letter ,pwd.charAt(max-1) ==letter);
		}
		
		static Policy fromInput(String s)
		{
			String[]  blocks = s.split(" ");
			
			Policy p = new Policy();
			p.min = Integer.parseInt(blocks[0].split("-")[0]);
			p.max = Integer.parseInt(blocks[0].split("-")[1]);
			p.letter = blocks[1].charAt(0);
			
			p.pwd = blocks[2];
			return p;
			
		}
		
	}
	public static void main(String[] args) throws IOException {
		List<Policy> policies = Files.readAllLines(Paths.get("./input2.txt")).stream().map(s -> Policy.fromInput(s)).filter(p ->p.isValid2()).collect(Collectors.toList());

		System.out.print(policies.size());
		
	}

}
