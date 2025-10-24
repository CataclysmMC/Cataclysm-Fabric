package org.cataclysm.server.registry.item.custom.tool;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class CataclysmPickaxe extends PickaxeItem {
    public CataclysmPickaxe(ToolMaterial toolMaterial) {
        super(toolMaterial, new Settings()
                .attributeModifiers(createAttributeModifiers(toolMaterial, 1.0F, -2.8F))
                .fireproof()
        );
    }
}
