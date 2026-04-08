package ca.gg_g.fastcrystalspin.mixin;

import ca.gg_g.fastcrystalspin.FastCrystalSpinConfig;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndCrystal.class)
public abstract class EndCrystalEntityMixin {

    @Shadow public int time;

    @Inject(method = "tick", at = @At("TAIL"))
    private void fastcrystalspin$boostAge(CallbackInfo ci) {
        float multiplier = FastCrystalSpinConfig.getSpinSpeedMultiplier();

        if (multiplier <= 1.0f) {
            return;
        }

        int extra = Math.max(0, Math.round(multiplier) - 1);

        if (extra > 0) {
            this.time += extra;
        }
    }
}