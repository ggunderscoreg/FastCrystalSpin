package ca.gg_g.fastcrystalspin;

import com.mojang.brigadier.arguments.FloatArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

/**
 * Client-side commands:
 *   /crystalspin reload        - reload config file
 *   /crystalspin set <number>  - set multiplier and save config
 */
public class FastCrystalSpinCommand {

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    ClientCommandManager.literal("crystalspin")
                            // /crystalspin reload
                            .then(ClientCommandManager.literal("reload")
                                    .executes(context -> {
                                        FastCrystalSpinConfig.load();
                                        FabricClientCommandSource source = context.getSource();
                                        source.sendFeedback(Text.literal(
                                                "[FastCrystalSpin] Reloaded config. Multiplier = "
                                                        + FastCrystalSpinConfig.getSpinSpeedMultiplier()));
                                        return 1;
                                    })
                            )
                            // /crystalspin set <multiplier>
                            .then(ClientCommandManager.literal("set")
                                    .then(ClientCommandManager.argument(
                                                            "multiplier",
                                                            // allowed range: 0.1x to 50x, adjust if you want
                                                            FloatArgumentType.floatArg(0.1F, 50.0F)
                                                    )
                                                    .executes(context -> {
                                                        float value = FloatArgumentType.getFloat(context, "multiplier");
                                                        FastCrystalSpinConfig.spinSpeedMultiplier = value;
                                                        FastCrystalSpinConfig.save();

                                                        FabricClientCommandSource source = context.getSource();
                                                        source.sendFeedback(Text.literal(
                                                                "[FastCrystalSpin] Set crystal spin multiplier to " + value));
                                                        return 1;
                                                    })
                                    )
                            )
            );
        });
    }
}
