package it.valeriobruno.aoc2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14ExtendedPolimeryzation {

    public static final int ITERATIONS = 10;
    private String initialPolymer;
    private Map<String, String> rules;

    private Map<Character,Long> frequencyMap;

    private Day14ExtendedPolimeryzation() {
        rules = new HashMap<>();
        frequencyMap = new HashMap<>();
    }

    public static Day14ExtendedPolimeryzation parseInput(String filename) throws IOException
    {
        Day14ExtendedPolimeryzation instance = new Day14ExtendedPolimeryzation();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        instance.initialPolymer = reader.readLine();
        reader.readLine();

        Pattern rulePattern = Pattern.compile("([A-Z][A-Z]) -> ([A-Z])");
        String line;
        while ((line = reader.readLine()) != null) {
            Matcher matcher = rulePattern.matcher(line);
            if(matcher.matches())
                instance.rules.put(matcher.group(1), matcher.group(2));
            else throw new RuntimeException("Invalid input file");
        }

        instance.setFrequency(instance.initialPolymer);

        return instance;
    }

    private void setFrequency(String polymer) {
        frequencyMap = new HashMap<>();
        for (int j = 0; j < polymer.length(); j++)
        {
            frequencyMap.put(polymer.charAt(j),1+frequencyMap.getOrDefault(polymer.charAt(j),0L));
        }
    }

    private long calculateValue() {
        long max = frequencyMap.values().stream().max(Comparator.comparingLong(x -> x)).get();
        long min = frequencyMap.values().stream().min(Comparator.comparingLong(x -> x)).get();
        return max-min;

    }

    private void iterate()
    {
        String polymer = this.initialPolymer;
        for (int i = 0; i < ITERATIONS; i++) {

            StringBuilder nextPolymer = new StringBuilder();
            for (int j = 0; j < polymer.length() - 1; j++) {
                String pair = polymer.substring(j, j + 2);
                String extraChar = this.rules.getOrDefault(pair, "");

                if(j==0)
                    nextPolymer.append(polymer.charAt(j));
                nextPolymer.append(extraChar);
                nextPolymer.append(polymer.charAt(j + 1));

            }
            polymer = nextPolymer.toString();
            System.out.printf("Step %d: %s\n", i+1, polymer);
        }

        this.setFrequency(polymer);

    }

    public static void main(String[] args) throws IOException {

        Day14ExtendedPolimeryzation ep = Day14ExtendedPolimeryzation.parseInput("input-2021-14-sample.txt");

        ep.iterate();

        System.out.println(ep.calculateValue());
    }


}
