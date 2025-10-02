package org.cataclysm.server.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;

public abstract class CataclysmItem extends Item {
    public CataclysmItem(Settings settings) {
        super(settings);
    }

    public void consumeItem(PlayerEntity user, ItemStack itemStack) {
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
    }
}