package org.cataclysm.global.registry.item.custom.misc;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import org.cataclysm.global.registry.item.custom.CataclysmItem;

public class RelicItem extends Item implements CataclysmItem {
    public RelicItem() {
        super(new Settings()
                .rarity(Rarity.EPIC)
                .fireproof()
                .maxCount(1)
        );
    }
}
