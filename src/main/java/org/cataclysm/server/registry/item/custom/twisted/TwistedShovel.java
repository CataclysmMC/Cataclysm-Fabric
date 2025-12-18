package org.cataclysm.server.registry.item.custom.twisted;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.tag.BlockTags;

public final class TwistedShovel extends AbstractTwistedItem {
    public TwistedShovel() {
        super(new Settings()
                .attributeModifiers(MiningToolItem.createAttributeModifiers(TOOL_MATERIAL, 1.5F, -3.0F))
                .component(DataComponentTypes.TOOL, TOOL_MATERIAL.createComponent(BlockTags.SHOVEL_MINEABLE))
        );
    }
}