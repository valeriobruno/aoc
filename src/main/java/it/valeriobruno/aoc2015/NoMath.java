package it.valeriobruno.aoc2015;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NoMath {

    static long calcWrappingPaper(long l, long w, long h)
    {
        long a = l*w;
        long b = w*h;
        long c = l*h;

        return 2*(a+b+c)+Math.min(Math.min(a,b),c);
    }

    static long calcRibbon(long l, long w, long h)
    {
        long x,y;

        if(l>w)
        {
            x = w;
            y = Math.min(l,h);
        }
        else{
            x = l;
            y = Math.min(w,h);
        }

        return 2*(x+y) + l*w*h;

        //return Math.min(Math.min(2*l+2+w,2*l+2*h),2*w+2*h)+l*w*h; not th same!
    }

    static long calculate(List<String> lines, Function<? super List<Long>, Long> calculationFunction)
    {
        return lines.stream().
                map(s -> s.split("x")).
                map(dimensions -> Arrays.stream(dimensions).map(Long::parseLong).collect(Collectors.toList())).
                map(calculationFunction).reduce((a, b) -> a + b).get();
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input-2015-2.txt"));
        long total = calculate(lines, lwh -> calcWrappingPaper(lwh.get(0),lwh.get(1),lwh.get(2)) );
        System.out.printf("total paper needed %d\n",total);

        long ribbon = calculate(lines, lwh -> calcRibbon(lwh.get(0),lwh.get(1),lwh.get(2)) );
        System.out.printf("total ribbon needed %d\n",ribbon);
    }
}
