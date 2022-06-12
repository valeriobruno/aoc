import java.util.HashMap;
import java.util.Map;

public class StepHopJumpMedium
{

    public static final char PASS = '-';
    Map<String,Integer> memory = new HashMap<>();

    public int countWays(String level)
    {

        if(level.length() <= 2 )
            return 1;

        if(memory.containsKey(level))
            return memory.get(level);

        int count=0;
        if( level.charAt(1) == PASS) {

            count = countWays(level.substring(1));
        }
        if( level.charAt(2) == PASS) {
            count += countWays(level.substring(2));
        }
        if( level.length() > 3 && level.charAt(3) == PASS) {
            count += countWays(level.substring(3));
        }

        count = count % ((int) Math.pow(10, 9)+7) ;

        memory.put(level, count);
        return count;
    }
}