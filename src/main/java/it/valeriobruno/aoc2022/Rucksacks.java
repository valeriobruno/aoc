package it.valeriobruno.aoc2022;

import com.google.common.collect.Sets;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Rucksacks {

    List<String> splitCompartments(String row)
    {
        ArrayList<String> results = new ArrayList<>(2);
        results.add(row.substring(0,row.length()/2));
        results.add(row.substring(row.length()/2));

        return results;
    }

    Set<Character> findCommonItems(String... strings)
    {
        Set<Character> left = new HashSet<>();
        Set<Character> finalLeft = left;
        strings[0].chars().forEach(c -> finalLeft.add((char) c));

        for(int x=1;x<strings.length;x++)
        {
            Set<Character> right = new HashSet<>();
            strings[x].chars().forEach( c -> right.add((char) c));

            left= Sets.intersection(left,right);
        }

        return left;
    }
    
    int priorityOfCommonItem(List<String> pair)
    {
        Set<Character> commonItems = findCommonItems(pair.toArray(pair.toArray(new String[0])));

        int totalPriority=0;
        for (Character commonItem : commonItems)
            totalPriority += priority(commonItem);

        //System.out.println(totalPriority);
        return totalPriority;
    }

    private int priority(Character a) {

         if(a>= 'A'&& a <='Z')
             return a -'A' +27;
         else if(a>= 'a'&& a <='z')
             return a -'a' +1;
         else throw new RuntimeException("Error");
    }

    void parseInput (String input)
    {
       int total =  Arrays.stream(input.split("\n")).map(this::splitCompartments).map( this::priorityOfCommonItem).reduce(Integer::sum).get();

       System.out.printf("priority of shared items %d\n",total);
    }

    void parseInput2 (String input)
    {
        List<List<String>> groups = extractGroups(input);

        int total =
                groups.stream().map(this::priorityOfCommonItem).reduce(Integer::sum).get();

        System.out.printf("priority of shared items %d\n",total);
    }

    @NotNull
    List<List<String>> extractGroups(String input) {
        String[] lines = input.split("\n");
        LinkedList<List<String>> groups = new LinkedList<>();
        List<String> group= null;

        for (int x=0;x<lines.length;x++) {
            if(x %3 ==0) {
                group = new ArrayList<>(3);
                groups.add(group);
            }
            group.add(lines[x]);
        }
        return groups;
    }


    public static void main(String[] args) throws IOException {
        Rucksacks rs = new Rucksacks();

        String input = Files.readString(Paths.get("input-2022-3.txt"));
        rs.parseInput(input);
        rs.parseInput2(input);
    }
}
