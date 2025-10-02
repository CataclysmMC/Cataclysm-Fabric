package org.cataclysm.client.data.providers.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import org.cataclysm.server.blocks.CataclysmBlocks;

import java.util.concurrent.CompletableFuture;

public class CataclysmBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public CataclysmBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registryLookup) {
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(CataclysmBlocks.PARAGON_CORE);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(CataclysmBlocks.PARAGON_CORE);
    }
}

