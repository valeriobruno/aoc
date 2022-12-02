package it.valeriobruno.aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


public class RockPaperScissors {

    static long points(String row)
    {
        char opponent = row.charAt(0);
        char me = row.charAt(2);

        return shapeValue(translate(me))+outcome(opponent,me);
    }

    static long shapeValue(char me)
    {
        switch (me)
        {
            case 'R':
                return 1;
            case 'P':
                return 2;
            case 'S':
                return 3;
        }
        throw new RuntimeException("Invalid shape");
    }

    static long outcome(char opponent, char me)
    {
        switch(translate(opponent))
        {
            case 'R':
                switch (translate(me))
                {
                    case 'R':
                        return 3;
                    case 'P':
                        return 6;
                    case 'S':
                        return 0;
                }
            case 'P':
                switch (translate(me))
                {
                    case 'R':
                        return 0;
                    case 'P':
                        return 3;
                    case 'S':
                        return 6;
                }
            case 'S':
                switch (translate(me))
                {
                    case 'R':
                        return 6;
                    case 'P':
                        return 0;
                    case 'S':
                        return 3;
                }
        }
        throw new RuntimeException("Invalid move");
    }

    static char translate(char c)
    {
        switch(c)
        {
            case 'A':
            case 'X':
                return 'R';

            case 'B':
            case 'Y':
                return 'P';

            case 'C':
            case 'Z':
                return 'S';
        }
        throw new RuntimeException("Invalid move "+c);
    }

    static long points2(String row)
    {
        char opponent = row.charAt(0);
        char ending = row.charAt(2);

        switch(translate(opponent))
        {
            case 'R':
                switch (ending)
                {
                    case 'X': //lose
                        return 0+shapeValue('S');
                    case 'Y': //draw
                        return 3+shapeValue('R');
                    case 'Z'://win
                        return 6+shapeValue('P');
                }
            case 'P':
                switch (ending)
                {
                    case 'X': //lose
                        return 0+shapeValue('R');
                    case 'Y': //draw
                        return 3+shapeValue('P');
                    case 'Z'://win
                        return 6+shapeValue('S');
                }
            case 'S':
                switch (ending)
                {
                    case 'X': //lose
                        return 0+shapeValue('P');
                    case 'Y': //draw
                        return 3+shapeValue('S');
                    case 'Z'://win
                        return 6+shapeValue('R');
                }
        }
        throw new RuntimeException("Invalid");
    }

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("input-2022-2.txt"));

        long result = Arrays.stream(input.split("\n")).map(RockPaperScissors::points).reduce((a,b)-> a+b).get();
        System.out.printf("result: %d\n",result);

        result = Arrays.stream(input.split("\n")).map(RockPaperScissors::points2).reduce((a,b)-> a+b).get();
        System.out.printf("result2: %d",result);

    }
}
