package org.cataclysm.server.registry.item;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.cataclysm.Cataclysm;
import org.cataclysm.server.registry.item.custom.arcane.ArcaneBowItem;
import org.cataclysm.server.registry.item.custom.misc.paragon.ParagonBlessingItem;
import org.cataclysm.server.registry.item.custom.misc.paragon.ParagonPearlItem;
import org.cataclysm.server.registry.item.custom.misc.paragon.ParagonTotem;
import org.cataclysm.server.registry.item.custom.misc.relic.TwistedChaliceItem;
import org.cataclysm.server.registry.item.custom.AbstractCataclysmTool;
import org.cataclysm.server.registry.item.custom.arcane.*;
import org.cataclysm.server.registry.item.custom.arcane.ArcaneMaceItem;
import org.cataclysm.server.registry.item.custom.arcane.ArcaneTridentItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public final class CataclysmItems {
    public static final Item ARCANE_SWORD = registerItem("arcane_sword", new ArcaneSwordItem());
    public static final Item ARCANE_AXE = registerItem("arcane_axe", new ArcaneMiningToolItem(AbstractCataclysmTool.Type.AXE));
    public static final Item ARCANE_PICKAXE = registerItem("arcane_pickaxe", new ArcaneMiningToolItem(AbstractCataclysmTool.Type.PICKAXE));
    public static final Item ARCANE_SHOVEL = registerItem("arcane_shovel", new ArcaneMiningToolItem(AbstractCataclysmTool.Type.SHOVEL));
    public static final Item ARCANE_HOE = registerItem("arcane_hoe", new ArcaneMiningToolItem(AbstractCataclysmTool.Type.HOE));
    public static final Item ARCANE_TRIDENT = registerItem("arcane_trident", new ArcaneTridentItem());
    public static final Item ARCANE_MACE = registerItem("arcane_mace", new ArcaneMaceItem());
    public static final Item ARCANE_SHIELD = registerItem("arcane_shield", new ArcaneShieldItem(new Item.Settings().maxDamage(336).rarity(Rarity.EPIC).maxCount(1)));
    public static final Item ARCANE_BOW = registerItem("arcane_bow", new ArcaneBowItem(new Item.Settings().maxDamage(500).rarity(Rarity.EPIC).maxCount(1)));
    public static final Item ARCANE_GOLD = registerItem("arcane_gold");

    public static final Item TWISTED_CHALICE = registerItem("twisted_chalice", new TwistedChaliceItem());
    public static final Item TWISTED_INGOT = registerItem("twisted_ingot");
    public static final Item TWISTED_ROTTEN_FLESH = registerItem("twisted_rotten_flesh");
    public static final Item TWISTED_BONE = registerItem("twisted_bone");
    public static final Item TWISTED_STRING = registerItem("twisted_string");
    public static final Item TWISTED_GUNPOWDER = registerItem("twisted_gunpowder");
    public static final Item TWISTED_PEARL = registerItem("twisted_pearl", new Item(new Item.Settings().maxCount(16)));
    public static final Item TWISTED_BLAZE_ROD = registerItem("twisted_blaze_rod");

    public static final Item PARAGON_BLESSING = registerItem("paragon_blessing", new ParagonBlessingItem());
    public static final Item PARAGON_PEARL = registerItem("paragon_pearl", new ParagonPearlItem());
    public static final Item PARAGON_TOTEM = registerItem("paragon_totem", new ParagonTotem());
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
                .filter(item -> !(item instanceof BlockItem))
                .toList();
    }
}