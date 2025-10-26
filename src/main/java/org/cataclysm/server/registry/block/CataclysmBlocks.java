package org.cataclysm.server.registry.block;

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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public class CataclysmBlocks {
    public final static Block PARAGON_HEART = registerBlock("paragon_heart", new Block(AbstractBlock.Settings.create()
            .strength(1.5F, 6.0F)
            .luminance(value -> 10)
            .sounds(BlockSoundGroup.LODESTONE)
            .mapColor(DyeColor.YELLOW)
            .requiresTool()
    ), new Item.Settings().rarity(Rarity.UNCOMMON));

    public static void initBlocks() {}

    private static Block registerBlock(String path, Block block) {
        return registerBlock(path, block, new Item.Settings());
    }
    private static Block registerBlock(String path, Block block, Item.Settings settings) {
        Identifier id = Cataclysm.identifier(path);

        BlockItem item = new BlockItem(block, settings);
        Registry.register(Registries.ITEM, id, item);

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static @NotNull @Unmodifiable List<Item> getBlockItemList() {
        return Registries.ITEM.stream()
                .filter(item -> Registries.ITEM.getId(item).getNamespace().equals(Cataclysm.MOD_ID))
                .filter(item -> item instanceof BlockItem)
                .toList();
    }
}
