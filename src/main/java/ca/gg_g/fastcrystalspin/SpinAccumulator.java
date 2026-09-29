package ca.gg_g.fastcrystalspin;

/** Tracks the fractional extra animation ticks for one End Crystal. */
public final class SpinAccumulator {
    private double remainder;

    public int extraTicks(float multiplier) {
        double total = remainder + (double) multiplier - 1.0;
        int extra = (int) Math.floor(total);
        remainder = total - extra;
        return extra;
    }
}
