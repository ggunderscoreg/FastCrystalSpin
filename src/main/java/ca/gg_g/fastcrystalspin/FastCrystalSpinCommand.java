package ca.gg_g.fastcrystalspin;

import com.mojang.brigadier.arguments.FloatArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.minecraft.network.chat.Component;

public class FastCrystalSpinCommand {

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    ClientCommands.literal("crystalspin")
                            .then(ClientCommands.literal("reload")
                                    .executes(context -> {
                                        FastCrystalSpinConfig.load();
                                        context.getSource().sendFeedback(
                                                Component.literal("[FastCrystalSpin] Reloaded config. Multiplier = "
                                                        + FastCrystalSpinConfig.getSpinSpeedMultiplier())
                                        );
                                        return 1;
                                    })
                            )
                            .then(ClientCommands.literal("set")
                                    .then(ClientCommands.argument(
                                                    "multiplier",
                                                    FloatArgumentType.floatArg(
                                                            FastCrystalSpinConfig.MIN_MULTIPLIER,
                                                            FastCrystalSpinConfig.MAX_MULTIPLIER)
                                            )
                                            .executes(context -> {
                                                float value = FloatArgumentType.getFloat(context, "multiplier");
                                                FastCrystalSpinConfig.setSpinSpeedMultiplier(value);
                                                FastCrystalSpinConfig.save();

                                                context.getSource().sendFeedback(
                                                        Component.literal("[FastCrystalSpin] Set crystal spin multiplier to " + value)
                                                );
                                                return 1;
                                            })
                                    )
                            )
            );
        });
    }
}
