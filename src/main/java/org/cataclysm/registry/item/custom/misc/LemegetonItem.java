package org.cataclysm.registry.item.custom.misc;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class LemegetonItem extends Item {
    public LemegetonItem() {
        super(new Settings().rarity(Rarity.EPIC).maxCount(1));
    }
}
