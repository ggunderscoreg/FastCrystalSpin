package ca.gg_g.fastcrystalspin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class FastCrystalSpinConfig {

    private static final Path CONFIG_PATH = Paths.get("config", "fastcrystalspin.cfg");
    private static final String KEY_MULTIPLIER = "multiplier";

    // default value if config is missing or broken
    public static float spinSpeedMultiplier = 2.0F;

    public static void load() {
        try {
            if (!Files.exists(CONFIG_PATH)) {
                saveDefault();
                System.out.println("[FastCrystalSpin] No config found, created default.");
                return;
            }

            Properties props = new Properties();
            try (InputStream in = Files.newInputStream(CONFIG_PATH)) {
                props.load(in);
            }

            // Read multiplier, default 2.0
            String value = props.getProperty(KEY_MULTIPLIER, "2.0");
            spinSpeedMultiplier = Float.parseFloat(value);

            System.out.println("[FastCrystalSpin] Loaded config. Multiplier = " + spinSpeedMultiplier);
        } catch (IOException | NumberFormatException e) {
            System.err.println("[FastCrystalSpin] Failed to load config, using default. " + e.getMessage());
            spinSpeedMultiplier = 2.0F;
        }
    }

    private static void saveDefault() throws IOException {
        Files.createDirectories(CONFIG_PATH.getParent());
        Properties props = new Properties();
        props.setProperty(KEY_MULTIPLIER, "2.0");
        try (OutputStream out = Files.newOutputStream(CONFIG_PATH)) {
            props.store(out, "FastCrystalSpin config");
        }
    }

    // Save the current multiplier back to file
    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Properties props = new Properties();
            props.setProperty(KEY_MULTIPLIER, Float.toString(spinSpeedMultiplier));
            try (OutputStream out = Files.newOutputStream(CONFIG_PATH)) {
                props.store(out, "FastCrystalSpin config");
            }
            System.out.println("[FastCrystalSpin] Saved config. Multiplier = " + spinSpeedMultiplier);
        } catch (IOException e) {
            System.err.println("[FastCrystalSpin] Failed to save config: " + e.getMessage());
        }
    }

    public static float getSpinSpeedMultiplier() {
        return spinSpeedMultiplier;
    }
}
