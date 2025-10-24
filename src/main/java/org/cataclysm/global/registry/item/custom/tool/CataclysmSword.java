package org.cataclysm.global.registry.item.custom.tool;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import org.cataclysm.global.registry.item.custom.CataclysmItem;

public class CataclysmSword extends SwordItem implements CataclysmItem {
    public CataclysmSword(ToolMaterial toolMaterial) {
        super(toolMaterial, new Item.Settings()
                .attributeModifiers(createAttributeModifiers(toolMaterial, 3, -2.4F))
                .fireproof()
        );
    }
}
