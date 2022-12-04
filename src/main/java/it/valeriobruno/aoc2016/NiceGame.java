package it.valeriobruno.aoc2016;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class NiceGame {

    HashFunction hashing = Hashing.md5();
    final String doorId;
    final int PASS_LENGTH =8;

    public NiceGame(String doorId) {
        this.doorId = doorId;
    }

    String findPassword1()
    {
        String key = "";
        long number = 0;
        while(key.length()< PASS_LENGTH )
        {
            number++;
            if(number%10000000L==0)
                System.out.printf("reached %d\n",number);

            String s = doorId+number;

            HashCode hashed = hashing.hashBytes(s.getBytes(StandardCharsets.UTF_8));

            if(hashed.toString().startsWith("00000"))
            {
                key+=hashed.toString().charAt(5);
            }
        }
        return key;
    }

    String findPassword2()
    {
        Character []  password = new Character[PASS_LENGTH];
        long number = 0;
        while(Arrays.stream(password).filter( x -> x!=null).count() < PASS_LENGTH ) {
            number++;
            if (number % 10000000L == 0)
                System.out.printf("reached %d\n", number);
            String s = doorId + number;
            HashCode hashed = hashing.hashBytes(s.getBytes(StandardCharsets.UTF_8));
            String hexHash = hashed.toString();
            if (hexHash.startsWith("00000")) {
                char position = hexHash.charAt(5);
                if (position >= '0' && position <= '7') {
                    int intPos = Integer.parseInt("" + position);
                    if(password[intPos] == null)
                        password[intPos] =  hexHash.charAt(6);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Arrays.stream(password).forEach( letter -> sb.append(letter));
        return sb.toString();
    }


    public static void main(String[] args) {

        String id = "ffykfhsq";

        NiceGame ng = new NiceGame(id);

        System.out.println(ng.findPassword1());
        System.out.println(ng.findPassword2());

    }
}
