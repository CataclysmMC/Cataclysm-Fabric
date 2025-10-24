package org.cataclysm.datagen.provider.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import org.cataclysm.global.registry.item.CataclysmItems;

import java.util.concurrent.CompletableFuture;

public final class CataclysmItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public static final TagKey<Item> PICKAXES = ItemTags.PICKAXES;
    public static final TagKey<Item> SHOVELS = ItemTags.SHOVELS;
    public static final TagKey<Item> SWORDS  = ItemTags.SWORDS;
    public static final TagKey<Item> AXES = ItemTags.AXES;
    public static final TagKey<Item> HOES = ItemTags.HOES;

    public CataclysmItemTagProvider(FabricDataOutput out, CompletableFuture<RegistryWrapper.WrapperLookup> lookup) {
        super(out, lookup);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries) {
        for (Item item : CataclysmItems.getItemList()) {
            this.configureIfTool(item);
        }
    }

    private void configureIfTool(Item item) {
        if (item instanceof PickaxeItem) getOrCreateTagBuilder(PICKAXES).add(item);
        if (item instanceof ShovelItem) getOrCreateTagBuilder(SHOVELS).add(item);
        if (item instanceof SwordItem) getOrCreateTagBuilder(SWORDS).add(item);
        if (item instanceof AxeItem) getOrCreateTagBuilder(AXES).add(item);
        if (item instanceof HoeItem) getOrCreateTagBuilder(HOES).add(item);
    }
}