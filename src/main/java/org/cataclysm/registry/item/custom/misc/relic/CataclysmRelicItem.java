package org.cataclysm.registry.item.custom.misc.relic;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public abstract class CataclysmRelicItem extends Item {
    public CataclysmRelicItem() {
        super(new Settings().rarity(Rarity.EPIC).fireproof().maxCount(1));
    }
}
