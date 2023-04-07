package it.valeriobruno.aoc2022;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TreeShed {

    final int SIZE = 99;
    int[][] input = new int[SIZE][SIZE];
    Boolean[][] visible = new Boolean[SIZE][SIZE];

    void parse(File f) throws IOException {
        FileReader reader = new FileReader(f);

        reader = new FileReader(f);
        int x = 0;
        int y = 0;
        int c;
        while ((c = reader.read()) > -1) {
            switch (c) {
                case '\n':
                    y++;
                    x = 0;
                    break;
                default:
                    input[x][y] = Integer.parseInt("" + (char) c);
                    x++;
            }
        }
        reader.close();

    }

    int countVisible() {

        //all visible from left;
        for (int y = 0; y < SIZE; y++) {
            int tallestVisible = Integer.MIN_VALUE;
            for (int x = 0; x < SIZE; x++)
                if (input[x][y] > tallestVisible) {
                    visible[x][y] = true;
                    tallestVisible = input[x][y];
                }
        }
        //all visible from top;
        for (int x = 0; x < SIZE; x++) {
            int tallestVisible = Integer.MIN_VALUE;
            for (int y = 0; y < SIZE; y++)
                if (input[x][y] > tallestVisible) {
                    visible[x][y] = true;
                    tallestVisible = input[x][y];
                }
        }
        //all visible from right;
        for (int y = 0; y < SIZE; y++) {
            int tallestVisible = Integer.MIN_VALUE;
            for (int x = SIZE - 1; x >= 0; x--)
                if (input[x][y] > tallestVisible) {
                    visible[x][y] = true;
                    tallestVisible = input[x][y];
                }
        }
        //all visible from bottom;
        for (int x = 0; x < SIZE; x++) {
            int tallestVisible = Integer.MIN_VALUE;
            for (int y = SIZE - 1; y >= 0; y--)
                if (input[x][y] > tallestVisible) {
                    visible[x][y] = true;
                    tallestVisible = input[x][y];
                }
        }

        int total = 0;
        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++)
                if (visible[x][y] != null) total++;

        return total;
    }

    long maxScenicScore() {

        int[][] scenicLeft = new int[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++)
                scenicLeft[x][y]= treesOnTheLeft(x, y, scenicLeft);

        int[][] scenicRight = new int[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++)
            for (int x = SIZE-1; x > -1; x--)
                scenicRight[x][y]= treesOnTheRight(x, y, scenicRight);

        int[][] scenicTop = new int[SIZE][SIZE];
        for (int x = SIZE-1; x > -1; x--)
            for (int y = 0; y < SIZE; y++)
                scenicTop[x][y]= treesFromTop(x, y, scenicTop);

        int[][] scenicBot = new int[SIZE][SIZE];
        for (int x = SIZE-1; x > -1; x--)
            for (int y = SIZE-1; y > -1; y--)
                scenicBot[x][y]= treesFromBottom(x, y, scenicBot);

        long max = Long.MIN_VALUE;
        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++) {
                long total = ((long)scenicLeft[x][y]) * ((long)scenicRight[x][y]) * ((long)scenicTop[x][y]) * ((long)scenicBot[x][y]);
                if (max < total)
                    max = total;
            }
        return max;
    }

    int treesOnTheLeft(int x, int y, int[][] trees) {
        if (x == 0) return 0;

        if (input[x - 1][y] >= input[x][y])
            return 1;
        else return trees[x - 1][y] + 1;
    }

    int treesOnTheRight(int x, int y, int[][] trees) {
        if (x == SIZE - 1) return 0;

        if (input[x + 1][y] >= input[x][y])
            return 1;
        else return trees[x + 1][y] + 1;
    }

    int treesFromTop(int x, int y, int[][] trees) {
        if (y == 0) return 0;

        if (input[x][y - 1] >= input[x][y])
            return 1;
        else return trees[x][y - 1] + 1;
    }

    int treesFromBottom(int x, int y, int[][] trees) {
        if (y == SIZE - 1) return 0;

        if (input[x][y + 1] >= input[x][y])
            return 1;
        else return trees[x][y + 1] + 1;
    }


    public static void main(String[] args) throws Exception {
        TreeShed ts = new TreeShed();
        ts.parse(new File("input-2022-8.txt"));
        System.out.println(ts.countVisible());
        System.out.println(ts.maxScenicScore());
    }
}
