package org.cataclysm.registry.item;

import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;
import org.cataclysm.registry.item.custom.calamity.CalamityArmorItem;
import org.cataclysm.registry.item.custom.misc.LemegetonItem;
import org.cataclysm.registry.item.custom.twisted.*;
import org.cataclysm.registry.item.custom.arcane.ArcaneBowItem;
import org.cataclysm.registry.item.custom.arcane.ArcaneShieldItem;
import org.cataclysm.registry.item.custom.paragon.ParagonBlessingItem;
import org.cataclysm.registry.item.custom.paragon.ParagonPearlItem;
import org.cataclysm.registry.item.custom.paragon.ParagonTotem;
import org.cataclysm.registry.item.custom.misc.relic.TwistedChaliceItem;
import org.cataclysm.registry.item.custom.arcane.ArcaneMaceItem;
import org.cataclysm.registry.item.custom.arcane.ArcaneTridentItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public final class CataclysmItems {
    public static final Item CALAMITY_HELMET = registerItem("calamity_helmet", new CalamityArmorItem(ArmorItem.Type.HELMET));
    public static final Item CALAMITY_CHESTPLATE = registerItem("calamity_chestplate", new CalamityArmorItem(ArmorItem.Type.CHESTPLATE));
    public static final Item CALAMITY_LEGGINGS = registerItem("calamity_leggings", new CalamityArmorItem(ArmorItem.Type.LEGGINGS));
    public static final Item CALAMITY_BOOTS = registerItem("calamity_boots", new CalamityArmorItem(ArmorItem.Type.BOOTS));
    public static final Item CALAMITY_INGOT = registerItem("calamity_ingot");

    public static final Item ARCANE_TRIDENT = registerItem("arcane_trident", new ArcaneTridentItem());
    public static final Item ARCANE_MACE = registerItem("arcane_mace", new ArcaneMaceItem());
    public static final Item ARCANE_SHIELD = registerItem("arcane_shield", new ArcaneShieldItem());
    public static final Item ARCANE_BOW = registerItem("arcane_bow", new ArcaneBowItem());
    public static final Item ARCANE_GOLD = registerItem("arcane_gold");
    public static final Item ARCANE_NUGGET = registerItem("arcane_nugget");

    public static final Item TWISTED_SWORD = registerItem("twisted_sword", new TwistedSwordItem());
    public static final Item TWISTED_AXE = registerItem("twisted_axe", new TwistedAxeItem());
    public static final Item TWISTED_PICKAXE = registerItem("twisted_pickaxe", new TwistedPickaxe());
    public static final Item TWISTED_SHOVEL = registerItem("twisted_shovel", new TwistedShovel());
    public static final Item TWISTED_HOE = registerItem("twisted_hoe", new TwistedHoeItem());
    public static final Item TWISTED_CHALICE = registerItem("twisted_chalice", new TwistedChaliceItem());
    public static final Item TWISTED_INGOT = registerItem("twisted_ingot");
    public static final Item TWISTED_ROTTEN_FLESH = registerItem("twisted_rotten_flesh");
    public static final Item TWISTED_BONE = registerItem("twisted_bone");
    public static final Item TWISTED_STRING = registerItem("twisted_string");
    public static final Item TWISTED_GUNPOWDER = registerItem("twisted_gunpowder");
    public static final Item TWISTED_PEARL = registerItem("twisted_pearl", new Item(new Item.Settings().maxCount(16)));
    public static final Item TWISTED_BLAZE_ROD = registerItem("twisted_blaze_rod");
    public static final Item LEMEGETON = registerItem("lemegeton", new LemegetonItem());
    public static final Item CATACLYSM_UPGRADE_TIER_1 = registerItem("cataclysm_upgrade_tier_1");
    public static final Item CATACLYSM_UPGRADE_TIER_2 = registerItem("cataclysm_upgrade_tier_2");
    public static final Item CATACLYSM_UPGRADE_TIER_3 = registerItem("cataclysm_upgrade_tier_3");
    public static final Item PARAGON_BLESSING = registerItem("paragon_blessing", new ParagonBlessingItem());
    public static final Item PARAGON_PEARL = registerItem("paragon_pearl", new ParagonPearlItem());
    public static final Item PARAGON_TOTEM = registerItem("paragon_totem", new ParagonTotem());
    public static final Item PARAGON_QUARTZ = registerItem("paragon_quartz");
    public static final Item PARAGON_KEY = registerItem("paragon_key");

    public static void registerAllItems() {
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