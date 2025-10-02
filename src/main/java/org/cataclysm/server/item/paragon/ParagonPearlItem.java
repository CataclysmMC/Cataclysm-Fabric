package org.cataclysm.server.item.paragon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.cataclysm.server.entities.projectiles.ParagonPearlEntity;
import org.jetbrains.annotations.NotNull;

public class ParagonPearlItem extends ParagonItem {
    public static float POWER = 1.5f;

    public ParagonPearlItem(@NotNull Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (!(world instanceof ServerWorld serverWorld)) {
            return ActionResult.PASS;
        }

        ProjectileEntity.spawnWithVelocity(ParagonPearlEntity::new, serverWorld, itemStack, user, 0.0f, POWER, 1.0f);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.9f);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.NEUTRAL, 0.5f, 1.5f);

        user.getItemCooldownManager().set(itemStack, 20);
        super.consumeItem(user, itemStack);

        return ActionResult.SUCCESS;
    }
}