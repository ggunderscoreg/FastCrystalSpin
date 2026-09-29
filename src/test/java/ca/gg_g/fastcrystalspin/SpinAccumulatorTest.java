package ca.gg_g.fastcrystalspin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpinAccumulatorTest {
    @Test
    void fractionalSpeedAccumulatesPerTick() {
        SpinAccumulator spin = new SpinAccumulator();
        assertEquals(0, spin.extraTicks(1.5F));
        assertEquals(1, spin.extraTicks(1.5F));
        assertEquals(0, spin.extraTicks(1.5F));
        assertEquals(1, spin.extraTicks(1.5F));
    }

    @Test
    void integerSpeedAndSeparateEntities() {
        SpinAccumulator first = new SpinAccumulator();
        SpinAccumulator second = new SpinAccumulator();
        assertEquals(2, first.extraTicks(3.0F));
        assertEquals(0, first.extraTicks(1.5F));
        assertEquals(0, second.extraTicks(1.5F));
        assertEquals(1, first.extraTicks(1.5F));
        assertEquals(1, second.extraTicks(1.5F));
    }

    @Test
    void oneTimesSpeedDoesNotAddTicks() {
        SpinAccumulator spin = new SpinAccumulator();
        assertEquals(0, spin.extraTicks(1.0F));
        assertEquals(0, spin.extraTicks(1.0F));
    }
}
