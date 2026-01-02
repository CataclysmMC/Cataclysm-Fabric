package org.cataclysm.datagen.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import org.cataclysm.common.registry.item.CataclysmItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public final class CataclysmItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public CataclysmItemTagProvider(FabricDataOutput out, CompletableFuture<RegistryWrapper.WrapperLookup> lookup) {
        super(out, lookup);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries) {
        this.configureTwistedItemTags();
        this.configureArcaneItemTags();
    }

    private void configureTwistedItemTags() {
        this.configureItem(CataclysmItems.TWISTED_SWORD, ItemTags.SWORDS);
        this.configureItem(CataclysmItems.TWISTED_AXE, ItemTags.AXES);
        this.configureItem(CataclysmItems.TWISTED_PICKAXE, ItemTags.PICKAXES);
        this.configureItem(CataclysmItems.TWISTED_SHOVEL, ItemTags.SHOVELS);
        this.configureItem(CataclysmItems.TWISTED_HOE, ItemTags.HOES);
    }

    private void configureArcaneItemTags() {
        this.configureItem(CataclysmItems.ARCANE_TRIDENT, ItemTags.TRIDENT_ENCHANTABLE);
        this.configureItem(CataclysmItems.ARCANE_BOW, ItemTags.BOW_ENCHANTABLE);
        this.configureMace(CataclysmItems.ARCANE_MACE);
    }

    private void configureMace(Item item) {
        this.configureItem(item,
                ItemTags.MACE_ENCHANTABLE,
                ItemTags.SHARP_WEAPON_ENCHANTABLE,
                ItemTags.WEAPON_ENCHANTABLE,
                ItemTags.FIRE_ASPECT_ENCHANTABLE);
    }

    @SafeVarargs
    private void configureItem(Item item, TagKey<Item> @NotNull ... tags) {
        for (var tag : tags) {
            getOrCreateTagBuilder(tag).add(item);
        }
    }
}