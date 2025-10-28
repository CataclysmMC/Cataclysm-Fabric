package org.cataclysm.registry;

import org.cataclysm.registry.damage.type.CataclysmDamageTypes;
import org.cataclysm.registry.effect.CataclysmEffects;
import org.cataclysm.registry.entity.CataclysmEntityTypes;
import org.cataclysm.registry.item.CataclysmItemGroups;
import org.cataclysm.registry.item.CataclysmItems;
import org.cataclysm.registry.block.CataclysmBlocks;
import org.cataclysm.registry.model.CataclysmModelPredicates;

public final class CataclysmRegistry {
    public static void initRegistries() {
        initEntityRegistries();
        initItemRegistries();
        CataclysmEffects.registerAllEffects();
        CataclysmModelPredicates.registerAllModelPredicates();
        CataclysmDamageTypes.registerAllDamageTypes();
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