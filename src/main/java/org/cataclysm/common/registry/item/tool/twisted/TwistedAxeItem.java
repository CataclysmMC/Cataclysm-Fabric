package org.cataclysm.common.registry.item.tool.twisted;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.AxeItem;
import net.minecraft.registry.tag.BlockTags;

public final class TwistedAxeItem extends TwistedTool {
    public TwistedAxeItem(Settings settings) {
        super(settings.attributeModifiers(AxeItem.createAttributeModifiers(TOOL_MATERIAL, 5.0F, -3.0F)).component(DataComponentTypes.TOOL, TOOL_MATERIAL.createComponent(BlockTags.AXE_MINEABLE)));
    }
}