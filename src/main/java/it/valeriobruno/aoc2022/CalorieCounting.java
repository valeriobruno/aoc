package it.valeriobruno.aoc2022;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CalorieCounting {
   TreeSet<Integer> sortedFood = new TreeSet<>(Comparator.naturalOrder());

   public void loadFile(Path path) throws IOException {
       Scanner scanner = new Scanner(path, Charset.forName("UTF-8"));
       int food =0;

       while(scanner.hasNextLine())
       {
           String line = scanner.nextLine();
           if(line.equals(""))
           {
               sortedFood.add(food);
               food=0;
               continue;
           }
           else{
               food+= Integer.parseInt(line);
           }
           System.out.println(line);
       }
       scanner.close();
   }

   public int max()
   {
       return sortedFood.last();
   }

   public int topThree()
   {
       Iterator<Integer> it = sortedFood.descendingIterator();
       return it.next()+it.next()+it.next();
   }

    public static void main(String[] args) throws Exception {

        CalorieCounting d1 = new CalorieCounting();

        d1.loadFile(Paths.get("elfood.txt"));
        System.out.printf("\n%d\n",d1.max());
        System.out.println(d1.topThree());
    }
}
