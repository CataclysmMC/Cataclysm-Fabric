package org.cataclysm.server.item.paragon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ParagonBlessingItem extends ParagonItem {
    private static final int IMMUNITY_TICKS = ParagonItem.IMMUNITY_BASE_TICKS;

    public ParagonBlessingItem(@NotNull Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(@NotNull World world, @NotNull PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!(world instanceof ServerWorld serverWorld)) {
            return ActionResult.PASS;
        }

        super.applyImmunity(serverWorld, List.of(user), IMMUNITY_TICKS);

        user.getItemCooldownManager().set(stack, 20);
        super.consumeItem(user, stack);

        return ActionResult.SUCCESS;
    }
}
