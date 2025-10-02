package org.cataclysm.client.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.cataclysm.client.data.providers.CataclysmModelProvider;
import org.cataclysm.client.data.providers.CataclysmRecipeProvider;
import org.cataclysm.client.data.providers.languages.CataclysmEnglishLanguageProvider;
import org.cataclysm.client.data.providers.lootTable.CataclysmBlockLootTableProvider;
import org.cataclysm.client.data.providers.tag.CataclysmBlockTagProvider;

public class CataclysmDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(CataclysmRecipeProvider::new);
        pack.addProvider(CataclysmModelProvider::new);
        pack.addProvider(CataclysmBlockTagProvider::new);
        pack.addProvider(CataclysmBlockLootTableProvider::new);
        pack.addProvider(CataclysmEnglishLanguageProvider::new);
    }
}