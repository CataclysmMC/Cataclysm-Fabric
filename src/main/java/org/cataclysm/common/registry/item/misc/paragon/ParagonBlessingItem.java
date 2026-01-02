package org.cataclysm.common.registry.item.misc.paragon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public final class ParagonBlessingItem extends ParagonItem {
    public ParagonBlessingItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!(world instanceof ServerWorld serverWorld)) {
            return TypedActionResult.pass(stack);
        }

        super.applyImmunity(serverWorld, player);
        player.getItemCooldownManager().set(this, 10);
        player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
        stack.decrementUnlessCreative(1, player);

        return TypedActionResult.pass(stack);
    }

    @Override
    public int getImmunityTicks() {
        return IMMUNITY_CONDITIONAL_DURATION;
    }
}
