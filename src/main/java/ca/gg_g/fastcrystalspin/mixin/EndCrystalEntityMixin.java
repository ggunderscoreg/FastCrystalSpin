package ca.gg_g.fastcrystalspin.mixin;

import ca.gg_g.fastcrystalspin.FastCrystalSpinConfig;
import ca.gg_g.fastcrystalspin.SpinAccumulator;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndCrystal.class)
public abstract class EndCrystalEntityMixin {

    @Shadow public int time;
    @Unique private final SpinAccumulator fastcrystalspin$accumulator = new SpinAccumulator();

    @Inject(method = "tick", at = @At("TAIL"))
    private void fastcrystalspin$boostAge(CallbackInfo ci) {
        float multiplier = FastCrystalSpinConfig.getSpinSpeedMultiplier();

        this.time += fastcrystalspin$accumulator.extraTicks(multiplier);
    }
}
