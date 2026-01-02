package org.cataclysm.common.registry.item.misc.paragon;

import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.cataclysm.common.registry.entity.projectile.ParagonPearlEntity;

public final class ParagonPearlItem extends ParagonItem {
    public ParagonPearlItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!(world instanceof ServerWorld serverWorld)) {
            return TypedActionResult.pass(stack);
        }

        ParagonPearlEntity pearlEntity = new ParagonPearlEntity(this, serverWorld, player);
        pearlEntity.setItem(stack);
        pearlEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);

        serverWorld.spawnEntity(pearlEntity);
        serverWorld.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5f, 0.9f);
        serverWorld.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.NEUTRAL, 0.5f, 1.5f);

        player.getItemCooldownManager().set(this, 20);
        super.consumeItem(player, stack);

        return TypedActionResult.pass(stack);
    }

    @Override
    public int getImmunityTicks() {
        return ParagonItem.IMMUNITY_CONDITIONAL_DURATION / 3;
    }

    @Override
    public Model getModel() {
        return Models.HANDHELD;
    }
}
