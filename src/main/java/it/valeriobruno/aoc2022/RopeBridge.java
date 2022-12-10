package it.valeriobruno.aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

public class RopeBridge {
    private final int LENGTH;
    final List<Positioning> history;
    final Set<Positioning> uniquePositions;
    Positioning currentPosition;

    public RopeBridge(int ropeLen) {
        LENGTH = ropeLen;
        history = new LinkedList<>();
        uniquePositions = new HashSet<>();
        currentPosition = new Positioning();
    }

    class Positioning {

        final Point[] points;

        Positioning() {
            this.points = new Point[LENGTH];
            for(int x=0;x<LENGTH;x++)
                points[x] = new Point(0,0);
        }

        Positioning(Point[] points) {
            this.points = points;
        }

        static class Point {
            final int x, y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public Point shiftX(int sign) {
                return new Point(x + sign, y);
            }

            public Point shiftY(int sign) {
                return new Point(x, y + sign);
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


        Positioning clonePositioning() {
            var other = new Positioning(Arrays.copyOf(this.points, points.length));

            return other;
        }

        public Positioning moveHead(Function<Point, Point> calculator) {
            var result = this.clonePositioning();
            result.points[0] = calculator.apply(this.points[0]);
            for (int x = 1; x < LENGTH; x++)
                result.points[x] = result.tailFollow(result.points[x - 1], result.points[x]);
            return result;
        }

        Point tailFollow(Point head, Point tail) {
            int hx = head.x;
            int hy = head.y;
            int tx = tail.x;
            int ty = tail.y;

            /*this method is called after head has moved, to adjust tail to follow head.*/

            if (Math.abs(hx - tx) > 1 && hy==ty)
                tx += Math.signum(hx - tx) * 1;
            else if (Math.abs(hy - ty) > 1 && hx==tx)
                ty += Math.signum(hy - ty) * 1;
            else if (Math.abs(hy - ty) > 1)
            {
                ty += Math.signum(hy - ty) * 1;
                tx += Math.signum(hx - tx) * 1;
            }
            else if (Math.abs(hx - tx) == 2)
            {
                tx += Math.signum(hx - tx) * 1;
                ty += Math.signum(hy - ty) * 1;
            }

            return new Point(tx, ty);
        }

        @Override
        public String toString() {
            return printTail(LENGTH-1);
        }

        public String printTail(int index) {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(points[index].x).append(',').append(points[index].y).append(')');
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Positioning that = (Positioning) o;

            return this.points[LENGTH-1].equals( that.points[LENGTH-1]);
        }

        @Override
        public int hashCode() {
            return points[LENGTH-1].hashCode();
        }
    }


    void executeMove(String move) {
        int times = Integer.parseInt(move.substring(2));
        for (int i = 1; i <= times; i++) {
            currentPosition = move(move.charAt(0));
            history.add(currentPosition);
            uniquePositions.add(currentPosition);
        }
        printMap(21,26,new Positioning.Point(11,15));
    }

    Positioning move(char c) {
        switch (c) {
            case 'R':
                return currentPosition.moveHead(p -> new Positioning.Point(p.x + 1, p.y));
            case 'D':
                return currentPosition.moveHead(p -> new Positioning.Point(p.x, p.y + 1));
            case 'L':
                return currentPosition.moveHead(p -> new Positioning.Point(p.x - 1, p.y));
            case 'U':
                return currentPosition.moveHead(p -> new Positioning.Point(p.x, p.y - 1));
        }
        throw new RuntimeException("Invalid move");
    }

    void printMap(int mapHeight, int mapWidth, Positioning.Point origin)
    {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');

        for(int y=0;y<mapHeight;y++) {
            loop: for (int x = 0; x < mapWidth; x++) {
                if(x == origin.x && y == origin.y) {
                    sb.append('S');
                    continue;
                }
                for(int i=0;i<LENGTH;i++)
                {
                    var pointI = currentPosition.points[i];
                    if(x == pointI.x + origin.x && y == pointI.y+origin.y) {
                        sb.append(i);
                        continue loop;
                    }
                }
                sb.append('.');
                continue;
            }
            sb.append('\n');
        }
        sb.append('\n');
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        var rope = new RopeBridge(10);

        //rope.printMap(5,6,new Positioning.Point(0,4));
        var lines = Files.readAllLines(Paths.get("input-2022-9.txt"));
        for (var line : lines) {
            rope.executeMove(line);
        }

        System.out.printf("%d %d\n", rope.history.size(), rope.uniquePositions.size());
        //System.out.println(rope.history);
    }
}
