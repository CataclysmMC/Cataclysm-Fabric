package org.cataclysm.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import org.cataclysm.Cataclysm;
import org.cataclysm.server.registry.item.CataclysmItems;

import java.util.concurrent.CompletableFuture;

public final class CataclysmRecipeProvider extends FabricRecipeProvider {
    public CataclysmRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, CataclysmItems.PARAGON_BLESSING)
                .pattern("PPP")
                .pattern("PNP")
                .pattern("PPP")
                .input('P', CataclysmItems.PARAGON_QUARTZ)
                .input('N', Items.NETHERITE_BLOCK)
                .criterion("has_paragon_quartz", conditionsFromItem(CataclysmItems.PARAGON_QUARTZ))
                .offerTo(exporter, Cataclysm.identifier("paragon_blessing"));
    }
}