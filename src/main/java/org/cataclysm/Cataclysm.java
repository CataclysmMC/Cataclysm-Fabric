package org.cataclysm;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.cataclysm.common.registry.CataclysmSounds;
import org.cataclysm.common.registry.ItemGroupRegistry;
import org.cataclysm.common.registry.ModelPredicateRegistry;
import org.cataclysm.common.util.RegistryUtils;
import org.cataclysm.common.registry.block.CataclysmBlocks;
import org.cataclysm.common.registry.effect.CataclysmEffects;
import org.cataclysm.common.registry.entity.CataclysmEntityTypes;
import org.cataclysm.common.registry.item.CataclysmItems;
import org.cataclysm.server.listener.ServerTickListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cataclysm implements ModInitializer {
    public static final String MOD_ID = "cataclysm";
    public static final Logger LOGGER = LoggerFactory.getLogger("Cataclysm");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Cataclysm...");

        this.initializeRegistries();
        this.registerListeners();

        LOGGER.info("Cataclysm initialized.");
    }

    private void initializeRegistries() {
        RegistryUtils.logRegistrations(CataclysmSounds.initialize());
        RegistryUtils.logRegistrations(CataclysmEffects.initialize());
        RegistryUtils.logRegistrations(CataclysmItems.initialize());
        RegistryUtils.logRegistrations(CataclysmBlocks.initialize());
        RegistryUtils.logRegistrations(CataclysmEntityTypes.initialize());
        ModelPredicateRegistry.initialize();
        ItemGroupRegistry.initialize();
    }

    private void registerListeners() {
        ServerTickListener.registerEvents();
    }

    public static Identifier identifier(String path) {
        return Identifier.of(MOD_ID, path);
    }
}