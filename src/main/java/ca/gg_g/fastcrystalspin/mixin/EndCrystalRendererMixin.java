package ca.gg_g.fastcrystalspin.mixin;

import ca.gg_g.fastcrystalspin.FastCrystalSpinConfig;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EndCrystalEntityRenderer.class)
public class EndCrystalRendererMixin {

    @ModifyVariable(
            method = "render",          // <-- only the name now
            at = @At("STORE"),
            ordinal = 0
    )
    private float fastcrystalspin$scaleSpin(float originalAge) {

        return originalAge * FastCrystalSpinConfig.getSpinSpeedMultiplier();
    }
}
