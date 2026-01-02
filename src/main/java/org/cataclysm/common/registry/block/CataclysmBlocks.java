package org.cataclysm.common.registry.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.cataclysm.Cataclysm;

public final class CataclysmBlocks {
    private static final Registry<Block> REGISTRY = Registries.BLOCK;


    private CataclysmBlocks() {}

    public static Registry<Block> initialize() {
        return REGISTRY;
    }

    private static Block register(String path, Block block, Item.Settings settings) {
        Identifier identifier = Cataclysm.identifier(path);

        BlockItem item = new BlockItem(block, settings);
        Registry.register(Registries.ITEM, identifier, item);

        return Registry.register(Registries.BLOCK, identifier, block);
    }
}