package it.valeriobruno.aoc2021;

import java.io.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day13TransparentOrigami {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

    Set<Point> pointOnSheet;

    public boolean add(Point point) {
        return pointOnSheet.add(point);
    }

    Day13TransparentOrigami()
    {
        pointOnSheet = new HashSet<>();
    }

    public void flipOnY(int yCoord)
    {
        Function<Point, Point> flipping =
                point ->
                        point.y < yCoord ? point : new Point(point.x, yCoord- (point.y - yCoord));
        pointOnSheet = pointOnSheet.stream().map(flipping).distinct().collect(Collectors.toSet());
    }

    public void flipOnX(int xCoord)
    {
        Function<Point, Point> flipping =
                point ->
                        point.x < xCoord ? point : new Point( xCoord - (point.x - xCoord), point.y);
        pointOnSheet = pointOnSheet.stream().map(flipping).distinct().collect(Collectors.toSet());
    }

    public void draw()
    {
        Comparator<Point> yComparator = Comparator.comparingInt(p -> p.y);
        int yMax = pointOnSheet.stream().max(yComparator).get().y;

        Comparator<Point> xComparator = Comparator.comparingInt(p -> p.x);
        int xMax = pointOnSheet.stream().max(xComparator).get().x;

        for(int y=0;y<=yMax;y++) {
            for (int x = 0; x <= xMax; x++) {
                Point p = new Point(x, y);
                if (pointOnSheet.contains(p))
                    System.out.print("#");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws IOException {
        Day13TransparentOrigami sheet =  new Day13TransparentOrigami();

        BufferedReader reader = new BufferedReader(new FileReader("input-2021-13.txt"));
        String line = "line";
        Pattern pointPattern = Pattern.compile("(\\d+),(\\d+)");
        Pattern foldPattern = Pattern.compile("fold along ([x|y])=(\\d+)");
        while(!line.equals(""))
        {
           line =  reader.readLine();

            Matcher matcher = pointPattern.matcher(line);
            if(matcher.matches()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));

                sheet.add(new Point(x, y));
            }
        }
        line =  reader.readLine();
        while(line!=null)
        {


            Matcher matcher = foldPattern.matcher(line);
            if(matcher.matches()) {
                int foldPoint = Integer.parseInt(matcher.group(2));
                if (matcher.group(1).equals("x"))
                    sheet.flipOnX(foldPoint);
                else sheet.flipOnY(foldPoint);
            }
            line =  reader.readLine();
        }


        sheet.draw();

        reader.close();
    }

}
