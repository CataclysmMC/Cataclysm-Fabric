package org.cataclysm.server.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;
import org.cataclysm.server.item.custom.paragon.ParagonBlessingItem;
import org.cataclysm.server.item.custom.paragon.ParagonPearlItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public final class CataclysmItems {
    public static final Item PARAGON_QUARTZ = registerItem("paragon_quartz");
    public static final Item PARAGON_BLESSING = registerItem("paragon_blessing", new ParagonBlessingItem());
    public static final Item PARAGON_PEARL = registerItem("paragon_pearl", new ParagonPearlItem());

    public static void initItems() {
        Cataclysm.LOGGER.info("Items initialized: {} items registered", getItemList().size());
    }

    private static Item registerItem(String path) {
        return registerItem(path, new Item(new Item.Settings()));
    }
    private static Item registerItem(String path, Item item) {
        Identifier id = Cataclysm.identifier(path);
        if (item instanceof BlockItem bi) {
            bi.appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registries.ITEM, id, item);
    }

    public static @NotNull @Unmodifiable List<Item> getItemList() {
        return Registries.ITEM.stream()
                .filter(item -> Registries.ITEM.getId(item).getNamespace().equals(Cataclysm.MOD_ID))
                .toList();
    }
}