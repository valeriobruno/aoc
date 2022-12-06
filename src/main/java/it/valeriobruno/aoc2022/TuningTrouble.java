package it.valeriobruno.aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static java.nio.file.Files.readString;

public class TuningTrouble {

    int marker(String s, int length)
    {
        for(int x=length;x<s.length();x++)
        {
            HashMap<Character,Integer> map = new HashMap<>();
            for(int y=length;y>0;y--)
                map.put(s.charAt(x-y),1+ map.getOrDefault(s.charAt(x-y),0));

            if( map.keySet().stream().allMatch( c -> map.get(c) == 1))
                return x;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        TuningTrouble tt =  new TuningTrouble();
        System.out.println(tt.marker("mjqjpqmgbljsphdztnvjfqwrcgsmlb",4));
        System.out.println(tt.marker("bvwbjplbgvbhsrlpgdmjqwftvncz",4));
        System.out.println(tt.marker("nppdvjthqldpwncqszvftbrmjlhg",4));
        System.out.println(tt.marker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",4));
        System.out.println(tt.marker("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw",4));

        var input =  Files.readString(Paths.get("input-2022-6.txt"));
        System.out.println(tt.marker(input,4));
        System.out.println(tt.marker(input,14));
    }
}
