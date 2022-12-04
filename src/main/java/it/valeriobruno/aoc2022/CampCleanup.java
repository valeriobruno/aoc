package it.valeriobruno.aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CampCleanup {

    static class CleanPair
    {
        static Pattern pattern = Pattern.compile("(\\d+)-(\\d+),(\\d+)-(\\d+)");
        int lstart,lend,rstart,rend;

        CleanPair(String line)
        {
            Matcher matcher = pattern.matcher(line);
            if(matcher.matches())
            {
                lstart = Integer.parseInt(matcher.group(1));
                lend = Integer.parseInt(matcher.group(2));
                rstart = Integer.parseInt(matcher.group(3));
                rend = Integer.parseInt(matcher.group(4));
            }
            else throw new RuntimeException("Error");
        }

        public boolean fullyOverlap()
        {
            return (lstart <= rstart && lend>= rend) || (rstart <= lstart && rend>=lend);
        }

        public boolean partiallyOverlap()
        {
            return !((lstart <rstart && lend < rstart) || (lstart>rend && lend > rend));
        }

    }


    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("input-2022-4.txt"));

        long howManyFullyContains = Arrays.stream(input.split("\n")).map(CleanPair::new).filter(CleanPair::fullyOverlap).count();
        System.out.println(howManyFullyContains);

        long howManyPartiallyContains = Arrays.stream(input.split("\n")).map(CleanPair::new).filter(CleanPair::partiallyOverlap).count();
        System.out.println(howManyPartiallyContains);
    }
}
