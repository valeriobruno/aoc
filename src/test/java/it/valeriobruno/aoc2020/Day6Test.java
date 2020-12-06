package it.valeriobruno.aoc2020;

import it.valeriobruno.aoc2020.Day6;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class Day6Test {

    @Test
    public void testPart1() {
        String text = "abc\n" +
                "\n" +
                "a\n" +
                "b\n" +
                "c\n" +
                "\n" +
                "ab\n" +
                "ac\n" +
                "\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "\n" +
                "b";
        Scanner inputScanner = new Scanner(text);
        assertEquals(11, Day6.countQuestionsRepliedByAnybody(inputScanner));

    }

    @Test
    public void testPart2() {
        String text = "abc\n" +
                "\n" +
                "a\n" +
                "b\n" +
                "c\n" +
                "\n" +
                "ab\n" +
                "ac\n" +
                "\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "a\n" +
                "\n" +
                "b";

        Scanner inputScanner = new Scanner(text);
        assertEquals(6, Day6.countQuestionsRepliedByEverybody(inputScanner));
    }
}
