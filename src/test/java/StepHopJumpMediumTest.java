import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepHopJumpMediumTest {

    private StepHopJumpMedium instance;

    @BeforeEach
    public void setup()
    {
        instance = new StepHopJumpMedium();
    }

    @Test
    public void test0()
    {
        assertEquals(2, instance.countWays("---"));
        assertEquals(4, instance.countWays("----"));
        assertEquals(1, instance.countWays("-**-"));
        assertEquals(132436845,instance.countWays("------------------------------------"));
    }
}
