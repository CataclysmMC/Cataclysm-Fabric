package org.cataclysm.registry.item.custom.tool.arcane;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import org.cataclysm.registry.item.custom.CataclysmItem;

public interface ArcaneItem extends CataclysmItem {
    static Item.Settings getSettings() {
        return CataclysmItem.getSettings()
                .rarity(Rarity.EPIC)
                .maxCount(1);
    }
}
