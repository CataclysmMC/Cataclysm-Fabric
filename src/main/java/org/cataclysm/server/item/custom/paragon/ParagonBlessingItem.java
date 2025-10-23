package org.cataclysm.server.item.custom.paragon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.cataclysm.server.item.custom.ParagonItem;
import org.jetbrains.annotations.NotNull;

public class ParagonBlessingItem extends ParagonItem {
    public ParagonBlessingItem() {
        super(64);
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!(world instanceof ServerWorld serverWorld)) {
            return TypedActionResult.pass(stack);
        }

        super.applyImmunity(serverWorld, player);
        player.getItemCooldownManager().set(this, 20);
        super.consumeItem(player, stack);

        return TypedActionResult.pass(stack);
    }

    @Override
    public int getImmunityTicks() {
        return ParagonItem.getImmunityDefaultTicks();
    }
}
