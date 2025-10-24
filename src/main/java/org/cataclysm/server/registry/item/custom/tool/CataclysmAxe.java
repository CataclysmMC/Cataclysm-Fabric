package org.cataclysm.server.registry.item.custom.tool;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class CataclysmAxe extends AxeItem {
    public CataclysmAxe(ToolMaterial toolMaterial) {
        super(toolMaterial, new Item.Settings()
                .attributeModifiers(createAttributeModifiers(toolMaterial, 5.0F, -3.0F))
                .fireproof()
        );
    }
}
