package ca.gg_g.fastcrystalspin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class FastCrystalSpinConfig {
    private static final Path CONFIG_PATH = Path.of("config", "fastcrystalspin.cfg");
    private static final String KEY_MULTIPLIER = "multiplier";
    private static final float DEFAULT_MULTIPLIER = 2.0F;
    private static final float MIN_MULTIPLIER = 0.1F;
    private static final float MAX_MULTIPLIER = 50.0F;

    private static float spinSpeedMultiplier = DEFAULT_MULTIPLIER;

    private FastCrystalSpinConfig() {
    }

    public static void load() {
        try {
            if (!Files.exists(CONFIG_PATH)) {
                saveDefault();
                FastCrystalSpinClient.LOGGER.info("No config found, created default config.");
                return;
            }

            Properties props = new Properties();
            try (InputStream in = Files.newInputStream(CONFIG_PATH)) {
                props.load(in);
            }

            String value = props.getProperty(KEY_MULTIPLIER, Float.toString(DEFAULT_MULTIPLIER));
            setSpinSpeedMultiplier(Float.parseFloat(value));
            FastCrystalSpinClient.LOGGER.info("Loaded config. Multiplier = {}", spinSpeedMultiplier);
        } catch (IOException | NumberFormatException e) {
            FastCrystalSpinClient.LOGGER.warn("Failed to load config, using default.", e);
            spinSpeedMultiplier = DEFAULT_MULTIPLIER;
        }
    }

    private static void saveDefault() throws IOException {
        Files.createDirectories(CONFIG_PATH.getParent());
        Properties props = new Properties();
        props.setProperty(KEY_MULTIPLIER, Float.toString(DEFAULT_MULTIPLIER));
        try (OutputStream out = Files.newOutputStream(CONFIG_PATH)) {
            props.store(out, "FastCrystalSpin config");
        }
        spinSpeedMultiplier = DEFAULT_MULTIPLIER;
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Properties props = new Properties();
            props.setProperty(KEY_MULTIPLIER, Float.toString(spinSpeedMultiplier));
            try (OutputStream out = Files.newOutputStream(CONFIG_PATH)) {
                props.store(out, "FastCrystalSpin config");
            }
            FastCrystalSpinClient.LOGGER.info("Saved config. Multiplier = {}", spinSpeedMultiplier);
        } catch (IOException e) {
            FastCrystalSpinClient.LOGGER.error("Failed to save config.", e);
        }
    }

    public static float getSpinSpeedMultiplier() {
        return spinSpeedMultiplier;
    }

    public static void setSpinSpeedMultiplier(float multiplier) {
        spinSpeedMultiplier = clamp(multiplier, MIN_MULTIPLIER, MAX_MULTIPLIER);
    }

    private static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }
}
