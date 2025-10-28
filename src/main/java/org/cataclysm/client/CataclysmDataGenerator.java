package org.cataclysm.client;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.cataclysm.client.provider.CataclysmItemTagProvider;
import org.cataclysm.client.provider.CataclysmRecipeProvider;
import org.cataclysm.client.provider.CataclysmEnglishProvider;

public class CataclysmDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(CataclysmRecipeProvider::new);
        pack.addProvider(CataclysmItemTagProvider::new);
        pack.addProvider(CataclysmEnglishProvider::new);
    }
}
