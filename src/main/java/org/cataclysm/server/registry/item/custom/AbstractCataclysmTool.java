package org.cataclysm.server.registry.item.custom;

import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.item.*;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCataclysmTool extends ToolItem {
    public enum Type { SWORD, AXE, PICKAXE, SHOVEL, HOE }

    public AbstractCataclysmTool(ToolMaterial material, @NotNull Type type, TagKey<Block> effectiveBlocks, @NotNull Settings settings) {
        super(material, settings
                .component(DataComponentTypes.TOOL, material.createComponent(effectiveBlocks))
                .attributeModifiers(createAttributeModifiers(material, type))
        );
    }

    public AbstractCataclysmTool(ToolMaterial material, @NotNull Type type, @NotNull Settings settings) {
        super(material, settings.attributeModifiers(createAttributeModifiers(material, type)));
    }

    private static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, @NotNull Type type) {
        return switch (type) {
            case SWORD -> SwordItem.createAttributeModifiers(material, 3, -2.4F);
            case AXE -> AxeItem.createAttributeModifiers(material, 5.0F, -3.0F);
            case PICKAXE -> PickaxeItem.createAttributeModifiers(material, 1.0F, -2.8F);
            case SHOVEL -> ShovelItem.createAttributeModifiers(material, 1.5F, -3.0F);
            case HOE -> HoeItem.createAttributeModifiers(material, -4.0F, 0.0F);
        };
    }
}
