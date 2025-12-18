package org.cataclysm.server.registry.misc;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import org.cataclysm.Cataclysm;
import org.cataclysm.server.registry.block.CataclysmBlocks;
import org.cataclysm.server.registry.item.CataclysmItems;

import java.util.List;

public class ItemGroupRegistry {
    public static void register() {
        register("cataclysm_item_group", "Cataclysm Items", CataclysmItems.PARAGON_BLESSING, CataclysmItems.getItemList());
        register("cataclysm_block_group", "Cataclysm Blocks", CataclysmBlocks.PARAGON_HEART.asItem(), CataclysmBlocks.getBlockItemList());
    }

    private static void register(String path, String name, Item icon, List<Item> items) {
        RegistryKey<ItemGroup> key = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Cataclysm.identifier(path));
        Registry.register(Registries.ITEM_GROUP, key, FabricItemGroup.builder()
                .icon(icon::getDefaultStack)
                .displayName(Text.translatable(name))
                .entries((ctx, entries) -> items.forEach(item -> entries.add(item.getDefaultStack())))
                .build());
    }
}