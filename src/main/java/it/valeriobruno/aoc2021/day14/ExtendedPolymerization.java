package it.valeriobruno.aoc2021.day14;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtendedPolymerization {

    static class Rule implements Comparable<Rule>{
        char leftChar;
        char rightChar;
        char newChar;

        public Rule(char leftChar, char rightChar, char newChar) {
            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.newChar = newChar;
        }

        @Override
        public int compareTo(@NotNull Rule o) {
            int diff = this.leftChar - o.leftChar;
            if(diff == 0)
                diff = this.rightChar - o.rightChar;

            return diff;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Rule rule = (Rule) o;

            if (leftChar != rule.leftChar) return false;
            return rightChar == rule.rightChar;
        }

        @Override
        public int hashCode() {
            int result = leftChar;
            result = 31 * result + (int) rightChar;
            return result;
        }

        @Override
        public String toString() {
            return leftChar+""+rightChar;
        }
    }
    public static final int ITERATIONS = 40;
    private String initialPolymer;
    private final TreeSet<Rule> rules;

    private Map<Character,Long> frequencyMap;


    private ExtendedPolymerization() {
        rules = new TreeSet<>();
        frequencyMap = new HashMap<>();
    }

    public static ExtendedPolymerization parseInput(String filename) throws IOException
    {
        ExtendedPolymerization instance = new ExtendedPolymerization();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        instance.initialPolymer = reader.readLine();
        reader.readLine();

        Pattern rulePattern = Pattern.compile("([A-Z][A-Z]) -> ([A-Z])");
        String line;
        while ((line = reader.readLine()) != null) {
            Matcher matcher = rulePattern.matcher(line);
            if(matcher.matches()) {
                Rule rule = new Rule(matcher.group(1).charAt(0),matcher.group(1).charAt(1),matcher.group(2).charAt(0));
                instance.rules.add(rule);
            }
            else throw new RuntimeException("Invalid input file");
        }

        instance.setFrequency(instance.initialPolymer);

        return instance;
    }

    private void setFrequency(String polymer) {
        frequencyMap = new HashMap<>();
        for (int j = 0; j < polymer.length(); j++)
        {
            increaseFrequency(polymer.charAt(j),1);
        }
    }

    private void increaseFrequency(char c, long multiplier) {
        frequencyMap.put(c,multiplier+frequencyMap.getOrDefault(c,0L));
    }

    private long calculateValue() {
        long max = frequencyMap.values().stream().max(Comparator.comparingLong(x -> x)).get();
        long min = frequencyMap.values().stream().min(Comparator.comparingLong(x -> x)).get();
        return max-min;

    }


    public void iterate()
    {
        iterate(ITERATIONS,new HashMap<>());
    }

    private void iterate(int i,Map<Rule,Long>previouslyAppliedRules)
    {
        System.out.printf("iteration %d\n",i);
        Map<Rule,Long> appliedRules = new HashMap<>();

        if(previouslyAppliedRules.isEmpty()) {
            //first iteration: decide the rules from the input string
            setFrequency(this.initialPolymer);

            for (int j = 0; j < initialPolymer.length() - 1; j++) {

                Rule protoRule = new Rule(initialPolymer.charAt(j),initialPolymer.charAt(j+1), '0');
                applyRule(appliedRules, protoRule,1L);
            }
        }else{
            // Nth iteration: determine the rules from those previously applied.
            for(Rule rule : previouslyAppliedRules.keySet())
            {
                    Rule proto1 = new Rule(rule.leftChar,rule.newChar, '0');
                    applyRule(appliedRules,proto1,previouslyAppliedRules.get(rule));

                    Rule proto2 = new Rule(rule.newChar,rule.rightChar, '0');

                    applyRule(appliedRules,proto2, previouslyAppliedRules.get(rule));

            }
        }

        if (i>1)
            iterate(i-1, appliedRules);
    }

    private void applyRule(Map<Rule,Long> appliedRules, Rule protoRule, long multiplier) {

        Rule actualRule = this.rules.ceiling(protoRule);

        if(actualRule.equals(protoRule)) { //is this a valid rule?
            increaseFrequency(actualRule.newChar,multiplier);
            appliedRules.put(actualRule,multiplier+appliedRules.getOrDefault(actualRule,0L));
        }
    }

    public static void main(String[] args) throws IOException {

        ExtendedPolymerization ep = ExtendedPolymerization.parseInput("input-2021-14.txt");

        ep.iterate();

        System.out.println(ep.calculateValue());
    }


}
