package ca.gg_g.fastcrystalspin;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FastCrystalSpinClient implements ClientModInitializer {
    public static final String MOD_ID = "fastcrystalspin";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        FastCrystalSpinConfig.load();
        FastCrystalSpinCommand.register();

        LOGGER.info("Loaded FastCrystalSpin. Multiplier = {}", FastCrystalSpinConfig.getSpinSpeedMultiplier());
    }
}
