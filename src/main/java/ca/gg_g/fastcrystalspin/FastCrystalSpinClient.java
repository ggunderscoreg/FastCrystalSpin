package ca.gg_g.fastcrystalspin;

import net.fabricmc.api.ClientModInitializer;

public class FastCrystalSpinClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Load config once at startup
        FastCrystalSpinConfig.load();

        // Register the /crystalspin reload client command
        FastCrystalSpinCommand.register();

        System.out.println("[FastCrystalSpin] Client mod loaded. Multiplier = "
                + FastCrystalSpinConfig.spinSpeedMultiplier);
    }
}