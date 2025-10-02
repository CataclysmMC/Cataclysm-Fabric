package org.cataclysm.server.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;
import org.cataclysm.server.blocks.paragon.ParagonCore;

import java.util.function.Function;

public class CataclysmBlocks {

    public static final Block PARAGON_CORE = CataclysmBlocks.register("paragon_core", ParagonCore::new, AbstractBlock.Settings.create().luminance(state -> 10).sounds(BlockSoundGroup.NETHERITE));

    public static void init() {
        Cataclysm.LOGGER.info("✅ CataclysmBlocks inicializado: {} bloques registrados para el mod '{}'" ,getRegisteredBlockCount(), Cataclysm.MOD_ID);
    }

    private static Block register(String name, Function<AbstractBlock.Settings,Block> blockFactory){
        return register(name, blockFactory, AbstractBlock.Settings.create());
    }

    private static Block register(String name, AbstractBlock.Settings settings){
        return register(name, Block::new, settings);
    }

    private static Block register(String name, Function<AbstractBlock.Settings,Block> blockFactory, AbstractBlock.Settings settings){
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));
        RegistryKey<Item> itemKey = keyOfItem(name);
        BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey());
        Registry.register(Registries.ITEM,itemKey,blockItem);
        return Registry.register(Registries.BLOCK,blockKey,block);
    }

    private static RegistryKey<Block> keyOfBlock(String name){
        return RegistryKey.of(RegistryKeys.BLOCK,Identifier.of(Cataclysm.MOD_ID,name));
    }

    private static RegistryKey<Item> keyOfItem(String name){
        return RegistryKey.of(RegistryKeys.ITEM,Identifier.of(Cataclysm.MOD_ID,name));
    }

    private static long getRegisteredBlockCount(){
        return Registries.BLOCK.stream().filter(block->Registries.BLOCK.getId(block).getNamespace().equals(Cataclysm.MOD_ID)).count();
    }
}