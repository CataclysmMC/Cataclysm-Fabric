package org.cataclysm.client.data.providers.lootTable;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import org.cataclysm.server.blocks.CataclysmBlocks;
import org.cataclysm.server.item.CataclysmItems;

import java.util.concurrent.CompletableFuture;

public class CataclysmBlockLootTableProvider extends FabricBlockLootTableProvider {
    public CataclysmBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        super.addDrop(CataclysmBlocks.PARAGON_CORE, CataclysmItems.PARAGON_QUARTZ);
    }
}