package org.cataclysm.common.registry.item.tool.twisted;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.tag.BlockTags;

public final class TwistedHoeItem extends TwistedTool {
    public TwistedHoeItem(Settings settings) {
        super(settings.attributeModifiers(MiningToolItem.createAttributeModifiers(TOOL_MATERIAL, -4.0F, 0.0F)).component(DataComponentTypes.TOOL, TOOL_MATERIAL.createComponent(BlockTags.HOE_MINEABLE)));
    }
}