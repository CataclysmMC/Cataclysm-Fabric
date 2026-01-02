package org.cataclysm.common.registry.item.tool.twisted;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.tag.BlockTags;

public final class TwistedPickaxeItem extends TwistedTool {
    public TwistedPickaxeItem(Settings settings) {
        super(settings.attributeModifiers(MiningToolItem.createAttributeModifiers(TOOL_MATERIAL, 1.0F, -2.8F)).component(DataComponentTypes.TOOL, TOOL_MATERIAL.createComponent(BlockTags.PICKAXE_MINEABLE)));
    }
}