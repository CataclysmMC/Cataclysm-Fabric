package org.cataclysm.global.registry.item.custom.tool;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;
import org.cataclysm.global.registry.item.custom.CataclysmItem;

public class CataclysmHoe extends HoeItem implements CataclysmItem {
    public CataclysmHoe(ToolMaterial toolMaterial) {
        super(toolMaterial, new Settings()
                .attributeModifiers(createAttributeModifiers(toolMaterial, -4.0F, 0.0F))
                .fireproof()
        );
    }
}
