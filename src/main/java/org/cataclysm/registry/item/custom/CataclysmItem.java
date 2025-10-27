package org.cataclysm.registry.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.item.Item;

public interface CataclysmItem {
    static Item.Settings getSettings() {
        return new Item.Settings()
                .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true));
    }
}
