package org.cataclysm.client.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.cataclysm.registry.effect.CataclysmEffects;
import org.cataclysm.registry.item.CataclysmItems;
import org.cataclysm.registry.block.CataclysmBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class CataclysmEnglishProvider extends FabricLanguageProvider {
    public CataclysmEnglishProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder builder) {
        this.addBlockTranslations(builder);
        this.addEffectTranslations(builder);
        this.addItemTranslations(builder);
    }

    private void addBlockTranslations(@NotNull TranslationBuilder builder) {
        builder.add(CataclysmBlocks.PARAGON_HEART, "Paragon's Heart");
    }

    private void addEffectTranslations(@NotNull TranslationBuilder builder) {
        builder.add(CataclysmEffects.IMMUNITY.value(), "Immortality");
    }

    private void addItemTranslations(@NotNull TranslationBuilder builder) {
        this.addArcaneTranslations(builder);
        this.addTwistedTranslations(builder);
        this.addParagonTranslations(builder);
    }
    private void addArcaneTranslations(@NotNull TranslationBuilder builder) {
        builder.add(CataclysmItems.ARCANE_MACE, "Arcane Mace");
        builder.add(CataclysmItems.ARCANE_TRIDENT, "Arcane Trident");
        builder.add(CataclysmItems.ARCANE_SHIELD, "Arcane Shield");
        builder.add(CataclysmItems.ARCANE_BOW, "Arcane Bow");
        builder.add(CataclysmItems.ARCANE_ARROW, "Arcane Arrow");
        builder.add(CataclysmItems.ARCANE_GOLD, "Arcane Gold");
    }
    private void addTwistedTranslations(@NotNull TranslationBuilder builder) {
        builder.add(CataclysmItems.TWISTED_SWORD, "Twisted Sword");
        builder.add(CataclysmItems.TWISTED_AXE, "Twisted Axe");
        builder.add(CataclysmItems.TWISTED_PICKAXE, "Twisted Pickaxe");
        builder.add(CataclysmItems.TWISTED_SHOVEL, "Twisted Shovel");
        builder.add(CataclysmItems.TWISTED_HOE, "Twisted Hoe");
        builder.add(CataclysmItems.TWISTED_CHALICE, "Twisted Chalice");
        builder.add(CataclysmItems.TWISTED_INGOT, "Twisted Ingot");
        builder.add(CataclysmItems.TWISTED_ROTTEN_FLESH, "Twisted Rotten Flesh");
        builder.add(CataclysmItems.TWISTED_BLAZE_ROD, "Twisted Blaze Rod");
        builder.add(CataclysmItems.TWISTED_BONE, "Twisted Bone");
        builder.add(CataclysmItems.TWISTED_STRING, "Twisted String");
        builder.add(CataclysmItems.TWISTED_GUNPOWDER, "Twisted Gunpowder");
        builder.add(CataclysmItems.TWISTED_PEARL, "Twisted Pearl");
    }
    private void addParagonTranslations(@NotNull TranslationBuilder builder) {
        builder.add(CataclysmItems.PARAGON_BLESSING, "Paragon's Blessing");
        builder.add(CataclysmItems.PARAGON_PEARL, "Paragon's Pearl");
        builder.add(CataclysmItems.PARAGON_TOTEM, "Paragon's Totem");
        builder.add(CataclysmItems.PARAGON_QUARTZ, "Paragon's Quartz");
        builder.add(CataclysmItems.PARAGON_KEY, "Paragon's Key");
    }
}