package org.cataclysm.server.registry.item.custom.arcane;

import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Rarity;
import org.cataclysm.server.registry.item.custom.AbstractCataclysmTool;
import org.cataclysm.server.registry.item.CataclysmMaterials;

public final class ArcaneMiningToolItem extends AbstractCataclysmTool {
    public ArcaneMiningToolItem(Type type) {
        super(CataclysmMaterials.ARCANE, type, BlockTags.PICKAXE_MINEABLE, new Settings().rarity(Rarity.EPIC));
    }
}