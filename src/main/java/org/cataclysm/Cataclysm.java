package org.cataclysm;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.cataclysm.server.listener.TickListener;
import org.cataclysm.server.registry.block.CataclysmBlocks;
import org.cataclysm.server.registry.effect.CataclysmEffects;
import org.cataclysm.server.registry.entity.CataclysmEntityTypes;
import org.cataclysm.server.registry.item.CataclysmItems;
import org.cataclysm.server.registry.misc.ItemGroupRegistry;
import org.cataclysm.server.registry.misc.ModelPredicateRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cataclysm implements ModInitializer {
    public static final String MOD_ID = "cataclysm";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        long start = System.nanoTime();

        LOGGER.info("Initializing Cataclysm...");

        initializeRegistries();
        registerListeners();

        long elapsed = (System.nanoTime() - start) / 1_000_000L;
        LOGGER.info("Cataclysm initialized in {} ms.", elapsed);
    }

    public static Identifier identifier(String path) {
        return Identifier.of(MOD_ID, path);
    }

    private static void initializeRegistries() {
        ModelPredicateRegistry.register();
        ItemGroupRegistry.register();
        CataclysmEntityTypes.initialize();
        CataclysmEffects.initialize();
        CataclysmBlocks.initialize();
        CataclysmItems.initialize();
    }

    private static void registerListeners() {
        TickListener.registerEvents();
    }
}