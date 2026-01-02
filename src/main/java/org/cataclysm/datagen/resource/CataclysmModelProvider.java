package org.cataclysm.datagen.resource;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.registry.Registries;
import org.cataclysm.common.registry.item.interfaces.Modeled;
import org.cataclysm.common.util.RegistryUtils;

public class CataclysmModelProvider extends FabricModelProvider {
    public CataclysmModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        RegistryUtils.getEntries(Registries.ITEM).forEach(item -> {
            if (item instanceof Modeled modelType) {
                generator.register(item, modelType.getModel());
            } else {
                generator.register(item, Models.GENERATED);
            }
        });
    }
}