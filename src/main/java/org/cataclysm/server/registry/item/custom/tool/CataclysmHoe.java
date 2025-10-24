package org.cataclysm.server.registry.item.custom.tool;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class CataclysmHoe extends HoeItem {
    public CataclysmHoe(ToolMaterial toolMaterial) {
        super(toolMaterial, new Settings()
                .attributeModifiers(createAttributeModifiers(toolMaterial, -4.0F, 0.0F))
                .fireproof()
        );
    }
}
