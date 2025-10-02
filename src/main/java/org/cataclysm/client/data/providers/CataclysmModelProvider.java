package org.cataclysm.client.data.providers;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.cataclysm.server.item.CataclysmItems;

public class CataclysmModelProvider extends FabricModelProvider {
    public CataclysmModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Si quieres registrar todos los bloques como cube_all automáticamente
        CataclysmItems.getItems().stream()
                .map(Item::asItem)
                .filter(item -> item instanceof BlockItem)
                .map(item -> ((BlockItem) item).getBlock())
                .forEach(blockStateModelGenerator::registerSimpleCubeAll);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Todos los ítems de tu mod se registran como "generated"
        CataclysmItems.getItems().stream()
                .filter(item -> !(item instanceof BlockItem)) // evitar duplicar los BlockItem
                .forEach(item -> itemModelGenerator.register(item, Models.GENERATED));
    }

    @Override
    public String getName() {
        return "Cataclysm Model Provider";
    }
}
