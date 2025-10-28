package org.cataclysm;

import org.cataclysm.listener.ServerPlayerListener;
import org.cataclysm.registry.effect.CataclysmEffects;
import org.cataclysm.registry.entity.CataclysmEntityTypes;
import org.cataclysm.registry.item.CataclysmItemGroups;
import org.cataclysm.registry.item.CataclysmItems;
import org.cataclysm.registry.block.CataclysmBlocks;
import org.cataclysm.registry.model.CataclysmModelPredicates;

public final class CataclysmRegistry {
    public static void registerAll() {
        initEntityRegistries();
        initItemRegistries();
        initListeners();
        CataclysmEffects.registerAllEffects();
        CataclysmModelPredicates.registerAllModelPredicates();
    }

    private static void initListeners() {
        ServerPlayerListener.registerEvents();
    }

    private static void initEntityRegistries() {
        CataclysmEntityTypes.registerAllEntityTypes();
    }

    private static void initItemRegistries() {
        CataclysmItems.registerAllItems();
        CataclysmBlocks.registerAllBlocks();
        CataclysmItemGroups.registerAllItemGroups();
    }
}