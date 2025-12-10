package ca.gg_g.fastcrystalspin;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

/**
 * Registers the /crystalspin reload client command.
 * Usage in game:
 *   /crystalspin reload
 * It will reload fastcrystalspin.cfg and update the multiplier.
 */
public class FastCrystalSpinCommand {

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    ClientCommandManager.literal("crystalspin")
                            .then(ClientCommandManager.literal("reload")
                                    .executes(context -> {
                                        FastCrystalSpinConfig.load();
                                        FabricClientCommandSource source = context.getSource();
                                        source.sendFeedback(Text.literal(
                                                "[FastCrystalSpin] Reloaded config. Multiplier = "
                                                        + FastCrystalSpinConfig.spinSpeedMultiplier));
                                        return 1;
                                    })
                            )
            );
        });
    }
}
