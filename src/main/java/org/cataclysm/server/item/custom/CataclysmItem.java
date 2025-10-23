package org.cataclysm.server.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import org.jetbrains.annotations.NotNull;

public abstract class CataclysmItem extends Item {
    public CataclysmItem(Settings settings) {
        super(settings);
    }

    public void consumeItem(@NotNull PlayerEntity player, @NotNull ItemStack itemStack) {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, player);
    }
}
