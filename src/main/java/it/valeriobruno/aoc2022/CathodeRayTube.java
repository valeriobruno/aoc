package it.valeriobruno.aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CathodeRayTube {

    int cycle;
    int registerX;
    long signalSum;
static final int SIZE = 40;
    char[] line;
    CathodeRayTube()
    {
        signalSum=0;

        registerX =1;
        cycle=0;
        initLine();

    }

    private void initLine() {
        line = new char[SIZE];
        for(int x=0;x<SIZE;x++)
            line[x]=' ';
    }

    long signal()
    {
        return cycle*registerX;
    }

    void onNewCycle()
    {
        cycle++;
        var pixel2draw  =(cycle-1)%40;
        if(pixel2draw>=registerX-1 &&pixel2draw<=registerX+1) {
            line[pixel2draw] ='#';
        }else line[pixel2draw] =' ';

        if(cycle ==20 || (cycle-20)%40 ==0) {
            signalSum += signal();
            //System.out.printf("cycle %d %d, signal %d\n",cycle,registerX,signal());

        }
        if(cycle %40 ==0)
        {
            System.out.println(line);
            initLine();
        }
    }
    void execute(String command)
    {
        //System.out.println(command);
        onNewCycle();
        if(command.startsWith("addx "))
        {
            onNewCycle();
            int increment = Integer.parseInt(command.substring(5));
            registerX+= increment;
        }else if (command.equals("noop"));
        else throw new RuntimeException("invalid command");
    }
    public static void main(String[] args) throws IOException {
       var tube = new CathodeRayTube();
        var input =  Files.readAllLines(Paths.get("input-2022-10.txt"));
       for(var line : input)
       {
           tube.execute(line);
       }

       //System.out.println(tube.signalSum);
    }
}
