package org.cataclysm.client.data.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;

import java.util.concurrent.CompletableFuture;

public class CataclysmRecipeProvider extends FabricRecipeProvider {
    public CataclysmRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                createParagonRecipes(this, exporter, registryLookup);
            }
        };
    }

    private void createParagonRecipes(RecipeGenerator generator, RecipeExporter exporter, RegistryWrapper.WrapperLookup registryLookup) {
        var itemLookup = registryLookup.getOrThrow(RegistryKeys.ITEM);

        Item paragonQuartz = itemLookup.getOrThrow(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Cataclysm.MOD_ID, "paragon_quartz"))).value();
        Item paragonCore = itemLookup.getOrThrow(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Cataclysm.MOD_ID, "paragon_core"))).value();
        Item paragonBless = itemLookup.getOrThrow(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Cataclysm.MOD_ID, "paragon_blessing"))).value();

        generator.createShaped(RecipeCategory.TOOLS, paragonBless, 1)
                .pattern("###")
                .pattern("#N#")
                .pattern("###")
                .input('#', paragonQuartz)
                .input('N', Blocks.NETHERITE_BLOCK)
                .criterion("has_quartz", generator.conditionsFromItem(paragonQuartz))
                .offerTo(exporter);

        generator.createShaped(RecipeCategory.BUILDING_BLOCKS, paragonCore, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', paragonQuartz)
                .criterion("has_quartz", generator.conditionsFromItem(paragonQuartz))
                .offerTo(exporter);

        generator.createShapeless(RecipeCategory.MISC, paragonQuartz, 9)
                .input(paragonCore)
                .criterion("has_core", generator.conditionsFromItem(paragonCore))
                .offerTo(exporter);
    }

    @Override
    public String getName() {
        return "Cataclysm Recipe Provider";
    }
}