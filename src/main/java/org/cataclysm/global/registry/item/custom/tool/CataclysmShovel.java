package org.cataclysm.global.registry.item.custom.tool;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import org.cataclysm.global.registry.item.custom.CataclysmItem;

public class CataclysmShovel extends ShovelItem implements CataclysmItem {
    public CataclysmShovel(ToolMaterial toolMaterial) {
        super(toolMaterial, new Settings()
                .attributeModifiers(createAttributeModifiers(toolMaterial, 1.5F, -3.0F))
                .fireproof()
        );
    }
}
