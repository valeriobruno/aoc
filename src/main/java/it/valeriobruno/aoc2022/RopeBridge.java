package it.valeriobruno.aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class RopeBridge {

    static class Positioning {
        int hx, hy;
        int tx, ty;

        Positioning clonePositioning()
        {
            var other = new Positioning();
            other.hx = this.hx;
            other.hy = this.hy;
            other.tx = this.tx;
            other.ty = this.ty;

            return other;
        }
        public Positioning moveHeadRight() {
                var result = this.clonePositioning();
                result.hx += 1;
                result.tailFollow();
                return result;
        }

        public Positioning moveHeadLeft() {
                var result = this.clonePositioning();
                result.hx -= 1;
                result.tailFollow();
                return result;
        }

        public Positioning moveHeadUp() {

                var result = this.clonePositioning();
                result.hy -= 1;
                result.tailFollow();
                return result;
        }

        public Positioning moveHeadDown() {

                var result = this.clonePositioning();
                result.hy += 1;
                result.tailFollow();
                return result;
        }

        void tailFollow() {
            if (Math.abs(hx - tx) == 2){
                tx += Math.signum(hx - tx) * 1;
                if ((hy - ty)!=0)
                    ty += Math.signum(hy - ty) * 1;
                }
            else if (Math.abs(hy - ty) == 2) {
                ty += Math.signum(hy - ty) * 1;
                if ((hx - tx) != 0)
                    tx += Math.signum(hx - tx) * 1;
            }
        }

        @Override
        public String toString() {
            return printTail();
        }
        public String printTail()
        {
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(tx).append(',').append(ty).append(')');
            return sb.toString();
        }
    }

    List<Positioning> history = new LinkedList<>();
    Set<Positioning> uniquePositions = new TreeSet<>(new OrderByTailPosition());
    Positioning currentPosition = new Positioning();

    static class OrderByTailPosition implements Comparator<Positioning> {
        @Override
        public int compare(Positioning o1, Positioning o2) {
            int result = o1.tx - o2.tx;
            if (result == 0)
                result = o1.ty - o2.ty;

            return result;
        }
    }

    void executeMove(String move) {
        int times = Integer.parseInt(move.substring(2));
        for (int i = 1; i <= times; i++) {
            currentPosition = move(move.charAt(0));
            history.add(currentPosition);
            uniquePositions.add(currentPosition);
        }
    }

    Positioning move(char c) {
        switch (c) {
            case 'R':
                return currentPosition.moveHeadRight();
            case 'D':
                return currentPosition.moveHeadDown();
            case 'L':
                return currentPosition.moveHeadLeft();
            case 'U':
                return currentPosition.moveHeadUp();
        }
        throw new RuntimeException("Invalid move");
    }

    public static void main(String[] args) throws IOException {
        var position = new Positioning();
        var rope = new RopeBridge();

        var lines = Files.readAllLines(Paths.get("input-2022-9.txt"));
        for (var line : lines) {
            rope.executeMove(line);
        }

        System.out.printf("%d %d\n",rope.history.size(),rope.uniquePositions.size());
        System.out.println(rope.history);

    }
}
