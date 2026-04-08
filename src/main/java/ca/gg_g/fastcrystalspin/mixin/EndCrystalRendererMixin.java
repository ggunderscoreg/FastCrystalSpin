package ca.gg_g.fastcrystalspin.mixin;

/**
 * Legacy renderer-side mixin kept as a reference during the 26.1.x port.
 *
 * The mod currently uses only EndCrystalEntityMixin because the 26.1.x render
 * pipeline and local-variable layout need to be re-mapped before a safe
 * renderer-side injection can be restored.
 */
public final class EndCrystalRendererMixin {
    private EndCrystalRendererMixin() {
    }
}
