package org.cataclysm.datagen.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolItem;
import org.cataclysm.server.registry.item.CataclysmItems;
import org.jetbrains.annotations.NotNull;

public class CataclysmModelProvider extends FabricModelProvider {
    public CataclysmModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NotNull BlockStateModelGenerator blockStateModelGenerator) {
        CataclysmItems.getItemList().stream()
                .map(Item::asItem)
                .filter(item -> item instanceof BlockItem)
                .map(item -> ((BlockItem) item).getBlock())
                .forEach(blockStateModelGenerator::registerSimpleCubeAll);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        CataclysmItems.getItemList().stream()
                .filter(item -> !(item instanceof BlockItem))
                .forEach(item -> {
                    if (item instanceof ToolItem) itemModelGenerator.register(item, Models.HANDHELD);
                    else itemModelGenerator.register(item, Models.GENERATED);
                });
    }
}