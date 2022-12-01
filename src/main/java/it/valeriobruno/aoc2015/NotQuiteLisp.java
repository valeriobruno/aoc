package it.valeriobruno.aoc2015;

import java.io.*;

public class NotQuiteLisp {

    int floor =0;
    Integer basementPosition;
    void countFloor(File path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));

        int parenthesis;
        int steps=1;
        while((parenthesis = reader.read()) != -1)
        {
            parseParenthesis((char) parenthesis);
            if(floor == -1 && basementPosition == null)
                basementPosition = steps;

            steps++;
        }
        reader.close();
    }

    void parseParenthesis(char p)
    {
        switch (p)
        {
            case '(': floor++;
            break;
            case ')': floor--;
            break;
            default:
                throw new RuntimeException("Invalid");
        }
    }



    public static void main(String[] args) throws IOException {

        NotQuiteLisp nql= new NotQuiteLisp();

        nql.countFloor(new File("input-2015-1.txt"));

        System.out.printf("reached floor %d\n",nql.floor);
        System.out.printf("reached basement at %d",nql.basementPosition);

    }
}
