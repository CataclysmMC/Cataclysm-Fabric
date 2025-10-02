package org.cataclysm.server.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;

import java.util.List;

public final class CataclysmItemGroups {
    public static ItemGroup CATACLYSM_GROUP;

    public static void init() {
        CATACLYSM_GROUP = registerGroup("cataclysm_group", "Cataclysm", CataclysmItems.PARAGON_BLESSING, CataclysmItems.getItems());
    }

    private static ItemGroup registerGroup(String name, String display, Item item, List<Item> items) {
        RegistryKey<ItemGroup> key = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Cataclysm.MOD_ID, name));
        ItemGroup.Builder group = FabricItemGroup.builder().icon(item::getDefaultStack).displayName(Text.literal(display)).entries((displayContext, entries) ->
                items.forEach(entries::add));
        return Registry.register(Registries.ITEM_GROUP, key, group.build());
    }
}