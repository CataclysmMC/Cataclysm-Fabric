package org.cataclysm.mixin.registry;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;
import org.cataclysm.common.registry.item.misc.totem.TotemItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Items.class)
public abstract class ItemsMixin {
    @Shadow @Final @Mutable public static Item TOTEM_OF_UNDYING;

    static {
        TOTEM_OF_UNDYING = Items.register("totem_of_undying", new TotemItem(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1)));
    }
}