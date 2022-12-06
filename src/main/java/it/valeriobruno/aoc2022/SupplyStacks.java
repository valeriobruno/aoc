package it.valeriobruno.aoc2022;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Collections.reverse;

public class SupplyStacks {
    static final Pattern movePattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

    List<Stack<Character>> stacks = new ArrayList<>(10);

    void parseInitialStatus(List<String> rows)
    {
        for(String row: rows)
        for(int x=1;x<row.length();x+=4)
        {
            char letter = row.charAt(x);

            if(letter>= 'A' && letter <='Z')
            {
                for(int sId=0; sId<=x/4;sId++)
                    if(stacks.size()<=sId)
                        stacks.add(new Stack<>());


                Stack stack = stacks.get(x/4);
//                if(stack == null) {
//                    stack = new Stack();
//                    stacks.set(x/4,stack);
//                }
                stack.push(letter);
            }
        }

        for (Stack stack :stacks)
            reverse(stack);
    }

    void executeMoves(List<String> commands)
    {
        commands.forEach( command -> executeMove2(command));
    }

    private void executeMove(String command) {
        Matcher matcher = movePattern.matcher(command);
        if(matcher.matches()) {
            int howMany = Integer.parseInt(matcher.group(1));

            int from = Integer.parseInt(matcher.group(2));
            int to = Integer.parseInt(matcher.group(3));

            for(int i=0;i<howMany;i++) {
                Character c = stacks.get(from-1).pop();
                stacks.get(to-1).push(c);
            }
        }else
            System.err.printf("WARN: skip command: %s\n",command);
    }

    private void executeMove2(String command) {
        Matcher matcher = movePattern.matcher(command);
        if(matcher.matches()) {
            int howMany = Integer.parseInt(matcher.group(1));

            int from = Integer.parseInt(matcher.group(2));
            int to = Integer.parseInt(matcher.group(3));

            LinkedList<Character> popped = new LinkedList<>();
            for(int i=0;i<howMany;i++) {
                Character c = stacks.get(from-1).pop();
                popped.addFirst(c);
            }

            popped.forEach( c -> stacks.get(to-1).push(c) );
        }else
            System.err.printf("WARN: skip command: %s\n",command);
    }

    void printTop()
    {
        System.out.println();
        for(Stack stack : stacks)
            System.out.printf("%c",stack.peek());

        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        final int stackHeight = 9;
        List<String> allLines = Files.readLines(new File("input-2022-5.txt"), Charset.forName("UTF-8"));

        List<String> stacks = allLines.subList(0,stackHeight);
        List<String> moves = allLines.subList(stackHeight,allLines.size());
        allLines = null;

        SupplyStacks ss = new SupplyStacks();
        ss.parseInitialStatus(stacks);
        ss.executeMoves(moves);
        ss.printTop();

    }

}
