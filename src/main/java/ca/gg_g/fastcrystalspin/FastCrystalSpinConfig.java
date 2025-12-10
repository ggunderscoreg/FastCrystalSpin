package ca.gg_g.fastcrystalspin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class FastCrystalSpinConfig {

    private static final Path CONFIG_PATH = Paths.get("config", "fastcrystalspin.cfg");

    // default value
    public static float spinSpeedMultiplier = 2.0F;

    public static void load() {
        try {
            if (!Files.exists(CONFIG_PATH)) {
                saveDefault();
                return;
            }

            Properties props = new Properties();
            props.load(Files.newInputStream(CONFIG_PATH));

            String value = props.getProperty("spinSpeedMultiplier", "2.0");
            spinSpeedMultiplier = Float.parseFloat(value);
        } catch (IOException | NumberFormatException e) {
            System.err.println("[FastCrystalSpin] Failed to load config, using default. " + e.getMessage());
            spinSpeedMultiplier = 2.0F;
        }
    }

    private static void saveDefault() throws IOException {
        Files.createDirectories(CONFIG_PATH.getParent());
        Properties props = new Properties();
        props.setProperty("spinSpeedMultiplier", "2.0");
        props.store(Files.newOutputStream(CONFIG_PATH), "FastCrystalSpin config");
    }

    // 👉 ADD THIS
    public static float getSpinSpeedMultiplier() {
        return spinSpeedMultiplier;
    }
}