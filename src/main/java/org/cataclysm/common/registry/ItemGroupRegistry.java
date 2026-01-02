package org.cataclysm.common.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import org.cataclysm.Cataclysm;
import org.cataclysm.common.registry.item.CataclysmItems;
import org.cataclysm.common.util.RegistryUtils;

import java.util.List;

public final class ItemGroupRegistry {
    public static void initialize() {
        register("base", "Cataclysm", CataclysmItems.PARAGON_BLESSING, RegistryUtils.getEntries(Registries.ITEM));
    }

    private static void register(String path, String name, Item icon, List<Item> items) {
        RegistryKey<ItemGroup> key = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Cataclysm.identifier(path));
        Registry.register(Registries.ITEM_GROUP, key, FabricItemGroup.builder().icon(icon::getDefaultStack).displayName(Text.translatable(name)).entries((ctx, entries) -> items.forEach(item -> entries.add(item.getDefaultStack()))).build());
    }
}