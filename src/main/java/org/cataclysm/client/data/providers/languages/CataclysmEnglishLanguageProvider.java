package org.cataclysm.client.data.providers.languages;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.cataclysm.server.blocks.CataclysmBlocks;
import org.cataclysm.server.effects.CataclysmEffects;
import org.cataclysm.server.item.CataclysmItems;

import java.util.concurrent.CompletableFuture;

public class CataclysmEnglishLanguageProvider extends FabricLanguageProvider {
    public CataclysmEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        // Items
        translationBuilder.add(CataclysmItems.PARAGON_BLESSING, "Paragon's Blessing");
        translationBuilder.add(CataclysmItems.PARAGON_TOTEM, "Paragon's Totem");
        translationBuilder.add(CataclysmItems.PARAGON_PEARL, "Paragon's Pearl");
        translationBuilder.add(CataclysmItems.PARAGON_QUARTZ, "Paragon Quartz");
        translationBuilder.add(CataclysmItems.PARAGON_KEY, "Paragon Key");

        // Blocks
        translationBuilder.add(CataclysmBlocks.PARAGON_CORE, "Paragon Core");

        // Effects
        translationBuilder.add(CataclysmEffects.IMMUNITY.value(), "Immortality");
    }

    @Override
    public String getName() {
        return "Cataclysm Language Provider (en_us)";
    }
}