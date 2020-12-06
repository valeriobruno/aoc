package it.valeriobruno.aoc2020;

import com.google.common.collect.Sets;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.BiFunction;

public class Day6 {

    public static Set<Character> parseLineQuestions(String line)
    {
        if(line.equals(""))
            throw new RuntimeException("Empty line");

        HashSet<Character> personAnswers = new HashSet<>();

        for(int i=0;i<line.length();i++ )
            personAnswers.add(line.charAt(i));

        return personAnswers;
    }

    public static Set<Character> parseGroupQuestions(Scanner inputScanner, BiFunction<Set<Character>,Set<Character>,Set<Character>> mergeInSameGroup)
    {
        Set<Character> groupAnswers = null;

        while(inputScanner.hasNextLine())
        {
            String line = inputScanner.nextLine();
            if(line.equals(""))
                return groupAnswers == null ? new HashSet<>(): groupAnswers;
            else
            {
                if(groupAnswers == null)
                    groupAnswers = parseLineQuestions(line);
                else groupAnswers = mergeInSameGroup.apply(groupAnswers, parseLineQuestions(line));
            }
        }
        return groupAnswers;
    }

    public static int count(Scanner inputScanner, BiFunction<Set<Character>,Set<Character>,Set<Character>> mergeInSameGroup)
    {
        int total =0;
        while(inputScanner.hasNextLine())
        {
            Set<Character> group = parseGroupQuestions(inputScanner, mergeInSameGroup);
            total +=    group.size();
        }
        return total;
    }

    public static int countQuestionsRepliedByAnybody(Scanner inputScanner)
    {
        return count(inputScanner, Sets::union);
    }

    public static int countQuestionsRepliedByEverybody(Scanner inputScanner)
    {
        return count(inputScanner, Sets::intersection);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputScanner = new Scanner(new File("./input6.txt"));

        System.out.println(countQuestionsRepliedByEverybody(inputScanner));
    }
}
