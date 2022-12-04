package it.valeriobruno.aoc2016;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SecurityThroughObscurity {

    static Pattern namePattern = Pattern.compile("(([a-z]+-)+)(\\d+)\\[([a-z]+)\\]");

    static class Room {

        Comparator<Character> order = new Comparator<>() {
            @Override
            public int compare(Character o1, Character o2) {
                int res = -1 * letterFrequency.getOrDefault(o1, 0).compareTo(letterFrequency.getOrDefault(o2, 0));
                if (res == 0)
                    res = o1.compareTo(o2);

                return res;
            }
        };


        Map<Character, Integer> letterFrequency;
        TreeSet<Character> expectedChecksum;
        final String encryptedName;

        Matcher matcher;

        public Room(String encryptedName) {
            this.encryptedName = encryptedName;
            matcher = namePattern.matcher(encryptedName);
            letterFrequency = new HashMap<>();
        }

        boolean isValid() {
            boolean res = matcher.matches();
            if (res) {
                String encryptedName = matcher.group(1);

                encryptedName.chars().forEach(l ->
                {
                    Integer old;
                    if (l != '-') {
                        if ((old = letterFrequency.putIfAbsent((char) l, 1)) != null)
                            letterFrequency.put((char) l, old + 1);
                    }
                });


                expectedChecksum = new TreeSet<>(order);
                encryptedName.chars().forEach(l -> {
                    if (l != '-') expectedChecksum.add((char) l);
                });
                String actualChecksum = matcher.group(4);
                Iterator<Character> checksumIt = expectedChecksum.iterator();
                for (int x = 0; x < 5; x++) {
                    if (actualChecksum.charAt(x) != checksumIt.next()) {
                        res = false;
                        break;
                    }
                }
            }
            return res;
        }

        int sector() {
            return Integer.parseInt(matcher.group(3));
        }

        String unencryptedName()
        {
            StringBuilder sb =

            encryptedName.chars().map( c-> shiftBy((char)c,sector())).collect(
                    StringBuilder::new,
                    (sb1,a)->sb1.append((char)a),
                    (sb1,sb2)-> sb1.append(sb2.toString()) );

            return sb.toString();
        }

         char shiftBy(char c, int sector) {

            if(c=='-')
                return ' ';

            for(int i=0;i<sector;i++) {
                if (c == 'z'){
                    c = 'a';
                }
                else c++;
            }
            return c;
        }
    }

    int part1(String input) {
        List<Room> validRooms = Arrays.stream(input.split("\n")).map(Room::new).filter(Room::isValid).collect(Collectors.toList());

        validRooms.forEach(room -> System.out.printf("%s - %d\n",room.unencryptedName(),room.sector()));

        return validRooms.stream().map(Room::sector).reduce(Integer::sum).get();
    }

    public static void main(String[] args) throws IOException {

        SecurityThroughObscurity sto = new SecurityThroughObscurity();
        String input = Files.readString(Paths.get("input-2016-4.txt"));

        System.out.println(sto.part1(input));
    }
}
