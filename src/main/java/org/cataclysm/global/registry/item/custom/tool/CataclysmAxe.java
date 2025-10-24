package org.cataclysm.global.registry.item.custom.tool;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import org.cataclysm.global.registry.item.custom.CataclysmItem;

public class CataclysmAxe extends AxeItem implements CataclysmItem {
    public CataclysmAxe(ToolMaterial toolMaterial) {
        super(toolMaterial, new Item.Settings()
                .attributeModifiers(createAttributeModifiers(toolMaterial, 7.0F, -3.0F)) //5
                .fireproof()
        );
    }
}
