package ca.gg_g.fastcrystalspin;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Very simple config loader for FastCrystalSpin.
 *
 * Config file: .minecraft/config/fastcrystalspin.cfg
 *
 * Format:
 *   # comments start with #
 *   multiplier=2.0
 */
public class FastCrystalSpinConfig {

    // Default value; will be overwritten if config file has a valid value.
    public static float spinSpeedMultiplier = 2.0F;

    public static void load() {
        // Resolve the config dir from Fabric
        Path configDir = FabricLoader.getInstance().getConfigDir();
        Path configPath = configDir.resolve("fastcrystalspin.cfg");

        if (Files.exists(configPath)) {
            try {
                List<String> lines = Files.readAllLines(configPath);
                for (String raw : lines) {
                    String line = raw.trim();
                    if (line.isEmpty() || line.startsWith("#")) continue;

                    String[] parts = line.split("=", 2);
                    if (parts.length == 2 && parts[0].trim().equalsIgnoreCase("multiplier")) {
                        try {
                            spinSpeedMultiplier = Float.parseFloat(parts[1].trim());
                        } catch (NumberFormatException ignored) {
                            // keep default if parse fails
                        }
                    }
                }
            } catch (IOException e) {
                // If reading fails, just keep the default
            }
        } else {
            // Create a default config file
            try {
                Files.createDirectories(configDir);
                String content = ""
                        + "# FastCrystalSpin config\n"
                        + "# multiplier = spin speed factor (float)\n"
                        + "# Example: multiplier=1.0 (vanilla), 2.0 (2x faster), 3.5, etc.\n"
                        + "multiplier=2.0\n";
                Files.writeString(configPath, content,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException ignored) {
            }
        }
    }
}