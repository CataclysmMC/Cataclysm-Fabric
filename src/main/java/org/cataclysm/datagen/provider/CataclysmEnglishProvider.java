package org.cataclysm.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.cataclysm.server.effect.CataclysmEffects;
import org.cataclysm.server.item.CataclysmItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class CataclysmEnglishProvider extends FabricLanguageProvider {
    public CataclysmEnglishProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        this.addItemTranslations(translationBuilder);
        this.addEffectTranslations(translationBuilder);
    }

    private void addItemTranslations(@NotNull TranslationBuilder builder) {
        builder.add(CataclysmItems.PARAGON_BLESSING, "Paragon's Blessing");
        builder.add(CataclysmItems.PARAGON_PEARL, "Paragon's Pearl");
        builder.add(CataclysmItems.PARAGON_QUARTZ, "Paragon's Quartz");
    }

    private void addEffectTranslations(@NotNull TranslationBuilder builder) {
        builder.add(CataclysmEffects.IMMUNITY.value(), "Immortality");
    }
}