package org.cataclysm.registry.item.custom.tool.twisted;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.tag.BlockTags;

public final class TwistedPickaxe extends AbstractTwistedTool {
    public TwistedPickaxe() {
        super(new Settings()
                .attributeModifiers(MiningToolItem.createAttributeModifiers(TOOL_MATERIAL, 1.0F, -2.8F))
                .component(DataComponentTypes.TOOL, TOOL_MATERIAL.createComponent(BlockTags.PICKAXE_MINEABLE))
        );
    }
}