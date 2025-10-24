package org.cataclysm.server.item.group;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;
import org.cataclysm.server.item.CataclysmItems;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CataclysmItemGroups {
    public static final ItemGroup ITEM_GROUP = registerGroup("cataclysm_item_group", "Cataclysm", CataclysmItems.PARAGON_TOTEM, CataclysmItems.getItemList());

    public static void initGroups() {}

    private static ItemGroup registerGroup(String path, String display, @NotNull Item item, List<Item> items) {
        Identifier id = Cataclysm.identifier(path);
        RegistryKey<ItemGroup> key = RegistryKey.of(Registries.ITEM_GROUP.getKey(), id);

        ItemGroup.Builder group = FabricItemGroup.builder()
                .icon(item::getDefaultStack)
                .displayName(Text.literal(display))
                .entries((displayContext, entries) -> items.forEach(entries::add));

        return Registry.register(Registries.ITEM_GROUP, key, group.build());
    }
}
