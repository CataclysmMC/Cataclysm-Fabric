package org.cataclysm;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.cataclysm.server.registry.CataclysmRegistry;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cataclysm implements ModInitializer {
    public static final String MOD_ID = "cataclysm";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        final long start = System.nanoTime();

        LOGGER.info("Initializing Cataclysm...");

        CataclysmRegistry.initRegistries();

        final long elapsed = (System.nanoTime() - start) / 1_000_000L;
        LOGGER.info("Cataclysm initialized in {} ms.", elapsed);
    }

    public static @NotNull Identifier identifier(String path) {
        return Identifier.of(MOD_ID, path);
    }
}