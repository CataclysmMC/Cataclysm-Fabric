package org.cataclysm.common.registry.item.tool.twisted;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.tag.BlockTags;

public final class TwistedShovelItem extends TwistedTool {
    public TwistedShovelItem(Settings settings) {
        super(settings.attributeModifiers(MiningToolItem.createAttributeModifiers(TOOL_MATERIAL, 1.5F, -3.0F)).component(DataComponentTypes.TOOL, TOOL_MATERIAL.createComponent(BlockTags.SHOVEL_MINEABLE)));
    }
}