package org.cataclysm;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.cataclysm.server.CataclysmServer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cataclysm implements ModInitializer {
    public static final String MOD_ID = "cataclysm";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static Cataclysm instance;
    private CataclysmServer server;

    @Override
    public void onInitialize() {
        final long start = System.nanoTime();
        instance = this;

        LOGGER.info("Initializing Cataclysm...");

        this.server = new CataclysmServer(this);
        this.server.init();

        final long elapsed = (System.nanoTime() - start) / 1_000_000L;
        LOGGER.info("Cataclysm initialized in {} ms.", elapsed);
    }

    public CataclysmServer getServer() {
        return this.server;
    }

    public static Cataclysm getInstance() {
        return instance;
    }

    public static @NotNull Identifier identifier(String path) {
        return Identifier.of(MOD_ID, path);
    }
}