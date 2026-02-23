package ca.gg_g.fastcrystalspin.mixin;

import ca.gg_g.fastcrystalspin.FastCrystalSpinConfig;
import net.minecraft.entity.decoration.EndCrystalEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Speeds up end crystal rotation by increasing their internal "age" value
 * every tick based on the configured multiplier.
 */
@Mixin(EndCrystalEntity.class)
public abstract class EndCrystalEntityMixin {

    // NOTE: if this doesn't compile, open EndCrystalEntity and copy the
    // actual int field name used for the age, e.g. "endCrystalAge" or similar.
    @Shadow public int endCrystalAge;

    @Inject(method = "tick", at = @At("TAIL"))
    private void fastcrystalspin$boostAge(CallbackInfo ci) {
        float multiplier = FastCrystalSpinConfig.getSpinSpeedMultiplier();

        // 1.0 = vanilla; don't slow things down with < 1.0
        if (multiplier <= 1.0f) {
            return;
        }

        // Example: multiplier 10 -> +9 extra “ticks” per game tick
        int extra = Math.max(0, Math.round(multiplier) - 1);

        if (extra > 0) {
            this.endCrystalAge += extra;
        }
    }
}