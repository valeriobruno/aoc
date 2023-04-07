package it.valeriobruno.aoc2022;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Monkeys {

    class Item
    {
        int initialWorryLevel;
    }
    class Monkey{

        private final Function<BigInteger, BigInteger> operation;
        private final Function<BigInteger, Monkey> test;
        final List<BigInteger> items;
        BigInteger inspectTimes;
        Monkey(List<BigInteger> startingItems, Function<BigInteger,BigInteger> operation, Function<BigInteger,Monkey> test)
        {
            this.operation = operation;
            this.test = test;

            items = new LinkedList<>();
            items.addAll(startingItems);

            inspectTimes = BigInteger.ZERO;
        }

        void playTurn()
        {
            while(!items.isEmpty()) {
              BigInteger worryLevel =  items.remove(0);
              worryLevel = operation.apply(worryLevel);
              //worryLevel /= 3L;
              test.apply(worryLevel).catchItem(worryLevel);
                inspectTimes = inspectTimes.add(BigInteger.ONE);
            }
        }

        void catchItem(BigInteger item)
        {
            items.add(item);
        }
    }

    final List<Monkey> allMonkeys;

    class Operation implements Function<BigInteger,BigInteger>
    {
        private final String opString;
        private static final Pattern operationPattern = Pattern.compile("(.+) ([\\+\\*]) (.+)");

        Operation(String opString)
        {
            this.opString = opString;
        }

        public BigInteger apply(BigInteger worryLevel)
        {
            var matcher = operationPattern.matcher(opString);
            matcher.matches();

            String leftTerm = matcher.group(1);
            BigInteger left = leftTerm.equals("old") ? worryLevel : new BigInteger(leftTerm,10) ;

            String rightTerm = matcher.group(3);
            BigInteger right = rightTerm.equals("old") ? worryLevel : new BigInteger(rightTerm,10);

            switch(matcher.group(2))
            {
                case "+":
                    return left.add(right);
                case "*":
                    return left.multiply(right);
                default:
                    throw new RuntimeException("Invalid operation: "+opString);
            }
        }
    }

    class TestFunction implements Function<BigInteger,Monkey>
    {
        final BigInteger divider;
        final int trueMonkey,falseMonkey;

        TestFunction(String testDescription)
        {

            String[] lines = testDescription.split("\n");
            divider = new BigInteger((lines[0].substring(21)),10);
            trueMonkey = Integer.parseInt(lines[1].substring(29));
            falseMonkey = Integer.parseInt(lines[2].substring(30));
        }

        public Monkey apply(BigInteger worryLevel)
        {
            if(worryLevel.mod(divider).equals(BigInteger.ZERO))
                return allMonkeys.get(trueMonkey);
            else return allMonkeys.get(falseMonkey);
        }


    }
    public  Monkeys()
    {
        allMonkeys = new ArrayList<>(100);
    }
    void parseMonkey(String monkeyDescription)
    {
        String[] lines = monkeyDescription.split("\n");
        var startingItems = Arrays.stream(lines[1].substring(18).split(", ")).map(x -> new BigInteger(x,10)).toList();
        var operation = new Operation(lines[2].substring(19));
        var test = new TestFunction(lines[3]+"\n"+lines[4]+"\n"+lines[5]);

        allMonkeys.add(new Monkey(startingItems,operation,test));
    }

    void playRound()
    {
        for(var monkey : allMonkeys)
        {
            monkey.playTurn();
        }
    }

    public static void main(String[] args) throws IOException {
        var monkeys = new Monkeys();
        Arrays.stream(Files.readString(Paths.get("input-2022-11.txt")).split("\n\n")).forEach((s -> monkeys.parseMonkey(s)));

        for (int i = 0; i < 10000; i++) {
            monkeys.playRound();
            for (int j = 0; j < monkeys.allMonkeys.size(); j++)
            {
                //System.out.printf("Monkey %d: %s\n",j,monkeys.allMonkeys.get(j).items);
            }

            if(i==0 || i==19 || (i+1) % 1000==0) {
                System.out.printf("round %d\n", i + 1);
                for (int j = 0; j < monkeys.allMonkeys.size(); j++)
                    System.out.printf("Monkey %d inspected items %d times\n", j, monkeys.allMonkeys.get(j).inspectTimes);


                var sorted = monkeys.allMonkeys.stream().map(( m -> m.inspectTimes)).sorted().toList();
                var monkeyBusiness = sorted.subList(sorted.size()-2,sorted.size()).stream().reduce( (a,b)-> a.multiply(b)).get();

                System.out.printf("===%d===\n\n",monkeyBusiness);
            }
        }





    }

}
