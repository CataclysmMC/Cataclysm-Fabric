package org.cataclysm.server.registry.item.custom.tool;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class CataclysmShovel extends ShovelItem {
    public CataclysmShovel(ToolMaterial toolMaterial) {
        super(toolMaterial, new Settings()
                .attributeModifiers(createAttributeModifiers(toolMaterial, 1.5F, -3.0F))
                .fireproof()
        );
    }
}
