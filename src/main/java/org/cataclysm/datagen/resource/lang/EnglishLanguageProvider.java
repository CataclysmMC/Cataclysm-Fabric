package org.cataclysm.datagen.resource.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.cataclysm.common.registry.effect.CataclysmEffects;
import org.cataclysm.common.registry.item.CataclysmItems;

import java.util.concurrent.CompletableFuture;

public class EnglishLanguageProvider extends FabricLanguageProvider {
    public EnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder builder) {
        this.buildEffects(builder);
        this.buildItems(builder);
        this.buildDiscs(builder);
    }

    private void buildEffects(TranslationBuilder builder) {
        builder.add(CataclysmEffects.IMMUNITY.value(), "Immunity");
        builder.add(CataclysmEffects.CLEANSING.value(), "Cleansing");
        builder.add(CataclysmEffects.MORTEM.value(), "Mortem");
    }

    private void buildItems(TranslationBuilder builder) {
        builder.add(CataclysmItems.MIDWAY_RELIC, "Midway Relic");
        builder.add(CataclysmItems.TWISTED_RELIC, "Twisted Relic");
        builder.add(CataclysmItems.OVERWORLD_CHALICE, "Chalice of The Overworld");
        builder.add(CataclysmItems.NETHER_CHALICE, "Chalice of The Nether");
        builder.add(CataclysmItems.END_CHALICE, "Chalice of The End");
        builder.add(CataclysmItems.PALE_HELMET, "Pale Helmet");
        builder.add(CataclysmItems.PALE_CHESTPLATE, "Pale Chestplate");
        builder.add(CataclysmItems.PALE_LEGGINGS, "Pale Leggings");
        builder.add(CataclysmItems.PALE_BOOTS, "Pale Boots");
        builder.add(CataclysmItems.PALE_SWORD, "Pale Sword");
        builder.add(CataclysmItems.PALE_BOW, "Pale Bow");
        builder.add(CataclysmItems.ENDER_BAG, "Ender Bag");
        builder.add(CataclysmItems.WANDERING_HEART, "Wandering Heart");
        builder.add(CataclysmItems.WANDERING_SOUL, "Wandering Soul");
        builder.add(CataclysmItems.MIRAGE_HELMET, "Mirage Helmet");
        builder.add(CataclysmItems.MIRAGE_ELYTRA, "Mirage Elytra");
        builder.add(CataclysmItems.MIRAGE_MACE, "Mirage Mace");
        builder.add(CataclysmItems.MIRAGE_BLESSING, "Mirage Blessing");
        builder.add(CataclysmItems.MIRAGE_UPGRADE, "Mirage Upgrade");
        builder.add(CataclysmItems.MIRAGE_APPLE, "Mirage Apple");
        builder.add(CataclysmItems.MIRAGE_SHARD, "Mirage Shard");
        builder.add(CataclysmItems.MIRAGE_BONE, "Mirage Bone");
        builder.add(CataclysmItems.MIRAGE_ESSENCE, "Mirage Essence");
        builder.add(CataclysmItems.MIRAGE_EYEBALL, "Mirage Eyeball");
        builder.add(CataclysmItems.MIRAGE_FLESH, "Mirage Flesh");
        builder.add(CataclysmItems.MIRAGE_INGOT, "Mirage Ingot");
        builder.add(CataclysmItems.MIRAGE_PEARL, "Mirage Pearl");
        builder.add(CataclysmItems.MIRAGE_POWDER, "Mirage Powder");
        builder.add(CataclysmItems.MIRAGE_QUARTZ, "Mirage Quartz");
        builder.add(CataclysmItems.MIRAGE_SCUTE, "Mirage Scute");
        builder.add(CataclysmItems.MIRAGE_TEAR, "Mirage Tear");
        builder.add(CataclysmItems.MIRAGE_WING, "Mirage Wing");
        builder.add(CataclysmItems.CATALYST_BONE, "Catalyst Bone");
        builder.add(CataclysmItems.DROWNED_CROWN, "Drowned Crown");
        builder.add(CataclysmItems.GOLDEN_CREAM, "Golden Cream");
        builder.add(CataclysmItems.PALE_BALL, "Pale Ball");
        builder.add(CataclysmItems.GUARDIAN_HEART, "Guardian Heart");
        builder.add(CataclysmItems.LLAMA_FUR, "Llama Fur");
        builder.add(CataclysmItems.NIGHTMARE_BONE, "Nightmare Bone");
        builder.add(CataclysmItems.TOXIC_MEMBRANE, "Toxic Membrane");
        builder.add(CataclysmItems.UR_TEAR, "Ur Tear");
        builder.add(CataclysmItems.CALAMITY_HELMET, "Calamity Helmet");
        builder.add(CataclysmItems.CALAMITY_CHESTPLATE, "Calamity Chestplate");
        builder.add(CataclysmItems.CALAMITY_LEGGINGS, "Calamity Leggings");
        builder.add(CataclysmItems.CALAMITY_BOOTS, "Calamity Boots");
        builder.add(CataclysmItems.CALAMITY_TOTEM, "Calamity Totem");
        builder.add(CataclysmItems.CALAMITY_APPLE, "Calamity Apple");
        builder.add(CataclysmItems.CALAMITY_CARROT, "Calamity Carrot");
        builder.add(CataclysmItems.CALAMITY_INGOT, "Calamity Ingot");
        builder.add(CataclysmItems.CALAMITY_NUGGET, "Calamity Nugget");
        builder.add(CataclysmItems.CALAMITY_KEY, "Calamity Key");
        builder.add(CataclysmItems.CALAMITY_PIGLIN_HEAD, "Calamity Piglin Head");
        builder.add(CataclysmItems.CALAMITY_SKELETON_SKULL, "Calamity Skeleton Skull");
        builder.add(CataclysmItems.ARCANE_TRIDENT, "Arcane Trident");
        builder.add(CataclysmItems.ARCANE_SHIELD, "Arcane Shield");
        builder.add(CataclysmItems.ARCANE_MACE, "Arcane Mace");
        builder.add(CataclysmItems.ARCANE_BOW, "Arcane Bow");
        builder.add(CataclysmItems.ARCANE_INGOT, "Arcane Ingot");
        builder.add(CataclysmItems.ARCANE_NUGGET, "Arcane Nugget");
        builder.add(CataclysmItems.ARCANE_ROD, "Arcane Rod");
        builder.add(CataclysmItems.TWISTED_SWORD, "Twisted Sword");
        builder.add(CataclysmItems.TWISTED_AXE, "Twisted Axe");
        builder.add(CataclysmItems.TWISTED_PICKAXE, "Twisted Pickaxe");
        builder.add(CataclysmItems.TWISTED_SHOVEL, "Twisted Shovel");
        builder.add(CataclysmItems.TWISTED_HOE, "Twisted Hoe");
        builder.add(CataclysmItems.TWISTED_INGOT, "Twisted Ingot");
        builder.add(CataclysmItems.TWISTED_FLESH, "Twisted Flesh");
        builder.add(CataclysmItems.TWISTED_BONE, "Twisted Bone");
        builder.add(CataclysmItems.TWISTED_STRING, "Twisted String");
        builder.add(CataclysmItems.TWISTED_POWDER, "Twisted Powder");
        builder.add(CataclysmItems.TWISTED_PEARL, "Twisted Pearl");
        builder.add(CataclysmItems.TWISTED_ROD, "Twisted Rod");
        builder.add(CataclysmItems.LEMEGETON, "Lemegeton");
        builder.add(CataclysmItems.CATACLYSM_UPGRADE_TIER_1, "Cataclysm Upgrade");
        builder.add(CataclysmItems.CATACLYSM_UPGRADE_TIER_2, "Cataclysm Upgrade");
        builder.add(CataclysmItems.CATACLYSM_UPGRADE_TIER_3, "Cataclysm Upgrade");
        builder.add(CataclysmItems.PARAGON_BLESSING, "Paragon Blessing");
        builder.add(CataclysmItems.PARAGON_PEARL, "Paragon Pearl");
        builder.add(CataclysmItems.PARAGON_TOTEM, "Paragon Totem");
        builder.add(CataclysmItems.PARAGON_QUARTZ, "Paragon Quartz");
        builder.add(CataclysmItems.MUSIC_DISC_NUCLEAR_FUSION_REDUX, "Music Disc");
        builder.add(CataclysmItems.MUSIC_DISC_GRANDMA_DESTRUCTION, "Music Disc");
        builder.add(CataclysmItems.MUSIC_DISC_PIERCING_THE_ABYSS, "Music Disc");
        builder.add(CataclysmItems.MUSIC_DISC_AJOURA, "Music Disc");
        builder.add(CataclysmItems.MUSIC_DISC_THATS_LIFE, "Music Disc");
    }

    private void buildDiscs(TranslationBuilder builder) {
        this.buildDisc(builder, "nuclear_fusion_redux", "SSJ3", "Nuclear Fusion Redux");
        this.buildDisc(builder, "grandma_destruction", "Diego Mitre", "Grandma (Destruction)");
        this.buildDisc(builder, "piercing_the_abyss", "Dutonic", "Piercing the Abyss");
        this.buildDisc(builder, "ajoura", "Adlai130s", "Ajoura");
        this.buildDisc(builder, "thats_life", "Frank Sinatra", "That's Life");
    }

    private void buildDisc(TranslationBuilder builder, String id, String author, String title) {
        String displayName = author + " - " + title;
        builder.add("item.cataclysm.music_disc_" + id + ".desc", displayName);
        builder.add("jukebox_song.cataclysm." + id, displayName);
    }
}