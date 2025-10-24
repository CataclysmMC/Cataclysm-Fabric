package org.cataclysm.server.registry.item.custom.tool;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class CataclysmSword extends SwordItem {
    public CataclysmSword(ToolMaterial toolMaterial) {
        super(toolMaterial, new Item.Settings()
                .attributeModifiers(createAttributeModifiers(toolMaterial, 3, -2.4F))
                .fireproof()
        );
    }
}
