package org.cataclysm.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.cataclysm.datagen.data.CataclysmItemTagProvider;
import org.cataclysm.datagen.data.CataclysmRecipeProvider;
import org.cataclysm.datagen.data.JukeboxSongProvider;
import org.cataclysm.datagen.resource.CataclysmModelProvider;
import org.cataclysm.datagen.resource.lang.EnglishLanguageProvider;

public final class CataclysmDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        this.addLanguageProviders(pack);
        this.addResourceProviders(pack);
        this.addDataProviders(pack);
    }

    private void addLanguageProviders(FabricDataGenerator.Pack pack) {
        pack.addProvider(EnglishLanguageProvider::new);
    }

    private void addResourceProviders(FabricDataGenerator.Pack pack) {
        pack.addProvider(CataclysmModelProvider::new);
    }

    private void addDataProviders(FabricDataGenerator.Pack pack) {
        pack.addProvider(CataclysmRecipeProvider::new);
        pack.addProvider(CataclysmItemTagProvider::new);
        pack.addProvider(JukeboxSongProvider::new);
    }
}