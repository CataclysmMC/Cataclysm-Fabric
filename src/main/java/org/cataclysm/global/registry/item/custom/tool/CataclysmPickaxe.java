package org.cataclysm.global.registry.item.custom.tool;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import org.cataclysm.global.registry.item.custom.CataclysmItem;

public class CataclysmPickaxe extends PickaxeItem implements CataclysmItem {
    public CataclysmPickaxe(ToolMaterial toolMaterial) {
        super(toolMaterial, new Settings()
                .attributeModifiers(createAttributeModifiers(toolMaterial, 1.0F, -2.8F))
                .fireproof()
        );
    }
}
