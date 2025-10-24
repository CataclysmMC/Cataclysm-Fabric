package org.cataclysm.server.registry.item;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;
import org.cataclysm.server.registry.item.custom.tool.*;
import org.cataclysm.server.registry.item.custom.util.paragon.ParagonBlessingItem;
import org.cataclysm.server.registry.item.custom.util.paragon.ParagonPearlItem;
import org.cataclysm.server.registry.item.custom.util.totem.ParagonTotemItem;
import org.cataclysm.server.registry.item.material.CataclysmToolMaterials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public final class CataclysmItems {
    public static final Item TWISTED_INGOT = registerItem("twisted_ingot");
    public static final Item TWISTED_ROTTEN_FLESH = registerItem("twisted_rotten_flesh");
    public static final Item TWISTED_BONE = registerItem("twisted_bone");
    public static final Item TWISTED_STRING = registerItem("twisted_string");
    public static final Item TWISTED_GUNPOWDER = registerItem("twisted_gunpowder");
    public static final Item TWISTED_PEARL = registerItem("twisted_pearl", new Item(new Item.Settings().maxCount(16)));
    public static final Item TWISTED_BLAZE_ROD = registerItem("twisted_blaze_rod");
    public static final Item TWISTED_SWORD = registerItem("twisted_sword", new CataclysmSword(CataclysmToolMaterials.TWISTED));
    public static final Item TWISTED_AXE = registerItem("twisted_axe", new CataclysmAxe(CataclysmToolMaterials.TWISTED));
    public static final Item TWISTED_PICKAXE = registerItem("twisted_pickaxe", new CataclysmPickaxe(CataclysmToolMaterials.TWISTED));
    public static final Item TWISTED_SHOVEL = registerItem("twisted_shovel", new CataclysmShovel(CataclysmToolMaterials.TWISTED));
    public static final Item TWISTED_HOE = registerItem("twisted_hoe", new CataclysmHoe(CataclysmToolMaterials.TWISTED));
    public static final Item PARAGON_BLESSING = registerItem("paragon_blessing", new ParagonBlessingItem());
    public static final Item PARAGON_PEARL = registerItem("paragon_pearl", new ParagonPearlItem());
    public static final Item PARAGON_TOTEM = registerItem("paragon_totem", new ParagonTotemItem());
    public static final Item PARAGON_QUARTZ = registerItem("paragon_quartz");
    public static final Item PARAGON_KEY = registerItem("paragon_key");

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