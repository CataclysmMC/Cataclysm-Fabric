package org.cataclysm.global.registry.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import org.cataclysm.Cataclysm;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CataclysmItemGroups {
    public static void initGroups() {
        registerGroup(
                "cataclysm_group",
                "Cataclysm",
                CataclysmItems.PARAGON_BLESSING,
                CataclysmItems.getItemList()
        );
    }

    private static void registerGroup(String path, String display, @NotNull Item icon, @NotNull List<Item> items) {
        var groupId  = Cataclysm.identifier(path);
        var groupKey = RegistryKey.of(Registries.ITEM_GROUP.getKey(), groupId);

        Registry.register(Registries.ITEM_GROUP, groupKey, FabricItemGroup.builder()
                .icon(icon::getDefaultStack)
                .displayName(Text.literal(display))
                .entries((ctx, entries) ->
                        items.forEach(entries::add))
                .build());
    }
}