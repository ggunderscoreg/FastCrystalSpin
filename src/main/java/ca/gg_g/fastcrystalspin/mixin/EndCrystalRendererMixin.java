package ca.gg_g.fastcrystalspin.mixin;

import ca.gg_g.fastcrystalspin.FastCrystalSpinConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Makes End Crystals visually spin faster by scaling their
 * animation time used in the renderer.
 *
 * CLIENT-SIDE ONLY: no gameplay changes.
 */
@Mixin(targets = "net.minecraft.client.render.entity.EndCrystalEntityRenderer")
public class EndCrystalRendererMixin {

    /**
     * Hooks the local float used for crystal animation time (age + tickDelta)
     * and multiplies it, so the spin animation is faster.
     */
    @ModifyVariable(
            method = "render(Lnet/minecraft/entity/decoration/EndCrystalEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "STORE"),
            ordinal = 0
    )
    private float fastcrystalspin$scaleSpin(float originalAge) {
        return originalAge * FastCrystalSpinConfig.spinSpeedMultiplier;
    }
}