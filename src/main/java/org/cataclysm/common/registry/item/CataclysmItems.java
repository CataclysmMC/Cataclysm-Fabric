package org.cataclysm.common.registry.item;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.cataclysm.Cataclysm;
import org.cataclysm.common.registry.CataclysmSongs;
import org.cataclysm.common.registry.item.armor.CalamityArmorItem;
import org.cataclysm.common.registry.item.armor.PaleArmorItem;
import org.cataclysm.common.registry.item.misc.CataclysmUpgradelItem;
import org.cataclysm.common.registry.item.misc.LemegetonItem;
import org.cataclysm.common.registry.item.misc.paragon.ParagonBlessingItem;
import org.cataclysm.common.registry.item.misc.paragon.ParagonPearlItem;
import org.cataclysm.common.registry.item.misc.relic.MidwayRelicItem;
import org.cataclysm.common.registry.item.misc.relic.TwistedRelicItem;
import org.cataclysm.common.registry.item.misc.totem.CalamityTotemItem;
import org.cataclysm.common.registry.item.misc.totem.ParagonTotemItem;
import org.cataclysm.common.registry.item.tool.arcane.ArcaneBowItem;
import org.cataclysm.common.registry.item.tool.arcane.ArcaneMaceItem;
import org.cataclysm.common.registry.item.tool.arcane.ArcaneShieldItem;
import org.cataclysm.common.registry.item.tool.arcane.ArcaneTridentItem;
import org.cataclysm.common.registry.item.tool.twisted.*;

public final class CataclysmItems {
    private static final Registry<Item> REGISTRY = Registries.ITEM;

    public static final Item MIDWAY_RELIC = register("midway_relic", new MidwayRelicItem(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item TWISTED_RELIC = register("twisted_relic", new TwistedRelicItem(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));

    public static final Item OVERWORLD_CHALICE = register("overworld_chalice", new MidwayRelicItem(new Item.Settings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item NETHER_CHALICE = register("nether_chalice", new MidwayRelicItem(new Item.Settings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item END_CHALICE = register("end_chalice", new MidwayRelicItem(new Item.Settings().rarity(Rarity.RARE).maxCount(1)));

    public static final Item PALE_HELMET = register("pale_helmet", new PaleArmorItem(ArmorItem.Type.HELMET, new Item.Settings().rarity(Rarity.EPIC).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item PALE_CHESTPLATE = register("pale_chestplate", new PaleArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Settings().rarity(Rarity.EPIC).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item PALE_LEGGINGS = register("pale_leggings", new PaleArmorItem(ArmorItem.Type.LEGGINGS, new Item.Settings().rarity(Rarity.EPIC).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item PALE_BOOTS = register("pale_boots", new PaleArmorItem(ArmorItem.Type.BOOTS, new Item.Settings().rarity(Rarity.EPIC).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item PALE_SWORD = register("pale_sword");
    public static final Item PALE_BOW = register("pale_bow");

    public static final Item ENDER_BAG = register("ender_bag");
    public static final Item WANDERING_HEART = register("wandering_heart");
    public static final Item WANDERING_SOUL = register("wandering_soul");

    public static final Item MIRAGE_HELMET = register("mirage_helmet");
    public static final Item MIRAGE_ELYTRA = register("mirage_elytra");
    public static final Item MIRAGE_MACE = register("mirage_mace");
    public static final Item MIRAGE_BLESSING = register("mirage_blessing");
    public static final Item MIRAGE_UPGRADE = register("mirage_upgrade");
    public static final Item MIRAGE_APPLE = register("mirage_apple");
    public static final Item MIRAGE_SHARD = register("mirage_shard");
    public static final Item MIRAGE_BONE = register("mirage_bone");
    public static final Item MIRAGE_ESSENCE = register("mirage_essence");
    public static final Item MIRAGE_EYEBALL = register("mirage_eyeball");
    public static final Item MIRAGE_FLESH = register("mirage_flesh");
    public static final Item MIRAGE_INGOT = register("mirage_ingot");
    public static final Item MIRAGE_PEARL = register("mirage_pearl");
    public static final Item MIRAGE_POWDER = register("mirage_powder");
    public static final Item MIRAGE_QUARTZ = register("mirage_quartz");
    public static final Item MIRAGE_SCUTE = register("mirage_scute");
    public static final Item MIRAGE_TEAR = register("mirage_tear");
    public static final Item MIRAGE_WING = register("mirage_wing");

    public static final Item CATALYST_BONE = register("cataclyst_bone");
    public static final Item DROWNED_CROWN = register("drowned_crown");
    public static final Item GOLDEN_CREAM = register("golden_cream");
    public static final Item PALE_BALL = register("pale_ball");
    public static final Item GUARDIAN_HEART = register("guardian_heart");
    public static final Item LLAMA_FUR = register("llama_fur");
    public static final Item NIGHTMARE_BONE = register("nightmare_bone");
    public static final Item TOXIC_MEMBRANE = register("toxic_membrane");
    public static final Item UR_TEAR = register("ur_tear");

    public static final Item CALAMITY_HELMET = register("calamity_helmet", new CalamityArmorItem(ArmorItem.Type.HELMET, new Item.Settings().component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item CALAMITY_CHESTPLATE = register("calamity_chestplate", new CalamityArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Settings().component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item CALAMITY_LEGGINGS = register("calamity_leggings", new CalamityArmorItem(ArmorItem.Type.LEGGINGS, new Item.Settings().component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item CALAMITY_BOOTS = register("calamity_boots", new CalamityArmorItem(ArmorItem.Type.BOOTS, new Item.Settings().component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item CALAMITY_TOTEM = register("calamity_totem", new CalamityTotemItem(new Item.Settings().maxCount(1)));
    public static final Item CALAMITY_APPLE = register("calamity_apple");
    public static final Item CALAMITY_CARROT = register("calamity_carrot");
    public static final Item CALAMITY_INGOT = register("calamity_ingot");
    public static final Item CALAMITY_NUGGET = register("calamity_nugget");
    public static final Item CALAMITY_KEY = register("calamity_key");
    public static final Item CALAMITY_PIGLIN_HEAD = register("calamity_piglin_head");
    public static final Item CALAMITY_SKELETON_SKULL = register("calamity_skeleton_skull");

    public static final Item ARCANE_TRIDENT = register("arcane_trident", new ArcaneTridentItem(new Item.Settings().maxCount(1).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item ARCANE_SHIELD = register("arcane_shield", new ArcaneShieldItem(new Item.Settings().maxCount(1).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item ARCANE_MACE = register("arcane_mace", new ArcaneMaceItem(new Item.Settings().maxCount(1).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item ARCANE_BOW = register("arcane_bow", new ArcaneBowItem(new Item.Settings().maxCount(1).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item ARCANE_INGOT = register("arcane_ingot");
    public static final Item ARCANE_NUGGET = register("arcane_nugget");
    public static final Item ARCANE_ROD = register("arcane_rod");
    public static final Item TWISTED_SWORD = register("twisted_sword", new TwistedSwordItem(new Item.Settings().maxCount(1).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item TWISTED_AXE = register("twisted_axe", new TwistedAxeItem(new Item.Settings().maxCount(1).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item TWISTED_PICKAXE = register("twisted_pickaxe", new TwistedPickaxeItem(new Item.Settings().maxCount(1).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item TWISTED_SHOVEL = register("twisted_shovel", new TwistedShovelItem(new Item.Settings().maxCount(1).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item TWISTED_HOE = register("twisted_hoe", new TwistedHoeItem(new Item.Settings().maxCount(1).component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))));
    public static final Item TWISTED_INGOT = register("twisted_ingot");
    public static final Item TWISTED_FLESH = register("twisted_flesh");
    public static final Item TWISTED_BONE = register("twisted_bone");
    public static final Item TWISTED_STRING = register("twisted_string");
    public static final Item TWISTED_POWDER = register("twisted_powder");
    public static final Item TWISTED_PEARL = register("twisted_pearl", new Item(new Item.Settings().maxCount(16)));
    public static final Item TWISTED_ROD = register("twisted_rod");
    public static final Item LEMEGETON = register("lemegeton", new LemegetonItem(new Item.Settings().maxCount(1)));
    public static final Item CATACLYSM_UPGRADE_TIER_1 = register("cataclysm_upgrade_tier_1", new CataclysmUpgradelItem(new Item.Settings()));
    public static final Item CATACLYSM_UPGRADE_TIER_2 = register("cataclysm_upgrade_tier_2", new CataclysmUpgradelItem(new Item.Settings()));
    public static final Item CATACLYSM_UPGRADE_TIER_3 = register("cataclysm_upgrade_tier_3", new CataclysmUpgradelItem(new Item.Settings()));
    public static final Item PARAGON_BLESSING = register("paragon_blessing", new ParagonBlessingItem(new Item.Settings()));
    public static final Item PARAGON_PEARL = register("paragon_pearl", new ParagonPearlItem(new Item.Settings().maxCount(16)));
    public static final Item PARAGON_TOTEM = register("paragon_totem", new ParagonTotemItem(new Item.Settings().maxCount(1)));
    public static final Item PARAGON_QUARTZ = register("paragon_quartz");
    public static final Item MUSIC_DISC_THATS_LIFE = createMusicDisc("music_disc_thats_life", CataclysmSongs.THATS_LIFE, new Item.Settings().rarity(Rarity.RARE).maxCount(1));
    public static final Item MUSIC_DISC_NUCLEAR_FUSION_REDUX = createMusicDisc("music_disc_nuclear_fusion_redux", CataclysmSongs.NUCLEAR_FUSION_REDUX, new Item.Settings().rarity(Rarity.RARE).maxCount(1));
    public static final Item MUSIC_DISC_GRANDMA_DESTRUCTION = createMusicDisc("music_disc_grandma_destruction", CataclysmSongs.GRANDMA_DESTRUCTION, new Item.Settings().rarity(Rarity.RARE).maxCount(1));
    public static final Item MUSIC_DISC_PIERCING_THE_ABYSS = createMusicDisc("music_disc_piercing_the_abyss", CataclysmSongs.PIERCING_THE_ABYSS, new Item.Settings().rarity(Rarity.RARE).maxCount(1));
    public static final Item MUSIC_DISC_AJOURA = createMusicDisc("music_disc_ajoura", CataclysmSongs.AJOURA, new Item.Settings().rarity(Rarity.EPIC).maxCount(1));

    public static Registry<Item> initialize() {
        return REGISTRY;
    }

    private static Item register(String path) {
        return register(path, new Item(new Item.Settings()));
    }

    private static Item register(String path, Item item) {
        Identifier identifier = Cataclysm.identifier(path);
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(REGISTRY, identifier, item);
    }

    private static Item createMusicDisc(String path, RegistryKey<JukeboxSong> song, Item.Settings settings) {
        return register(path, new Item(settings.jukeboxPlayable(song)));
    }
}