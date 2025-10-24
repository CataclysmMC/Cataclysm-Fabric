package org.cataclysm.global.registry.item.custom.misc.totem;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.cataclysm.global.registry.item.custom.CataclysmItem;
import org.jetbrains.annotations.NotNull;

public interface CataclysmTotemItem extends CataclysmItem {
    static ItemStack getActiveTotemStack(@NotNull LivingEntity entity) {
        ItemStack main = entity.getMainHandStack();
        if (isTotemItem(main)) return main;

        ItemStack off = entity.getOffHandStack();
        if (isTotemItem(off)) return off;

        return ItemStack.EMPTY;
    }

    static boolean isTotemItem(@NotNull ItemStack stack) {
        Item item = stack.getItem();
        return item == Items.TOTEM_OF_UNDYING || item instanceof CataclysmTotemItem;
    }
}