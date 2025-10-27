package org.cataclysm.registry.item.custom.tool.arcane;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ArcaneMaceItem extends MaceItem {
    public ArcaneMaceItem() {
        super(ArcaneItem.getSettings()
                .attributeModifiers(MaceItem.createAttributeModifiers())
        );
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!(world instanceof ServerWorld server)) return TypedActionResult.pass(stack);

        playSound(world, player, SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY, 1.5F, 1.2F, 1.8F);
        spawnWindCharge(server, world, player);

        player.getItemCooldownManager().set(this, 30);
        player.incrementStat(Stats.USED.getOrCreateStat(this));

        if (!player.isInCreativeMode()) {
            stack.damage(1, player, hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
        }

        return TypedActionResult.success(stack, world.isClient());
    }

    private void spawnWindCharge(@NotNull ServerWorld server, World world, PlayerEntity player) {
        WindChargeEntity charge = new WindChargeEntity(player, world, player.getX(), player.getEyeY(), player.getZ());
        charge.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.6F, 0.9F);
        server.spawnEntity(charge);
        playSound(world, player, SoundEvents.ENTITY_WIND_CHARGE_THROW, 1.5F, 0.9F, 1.3F);
    }

    private void playSound(World world, LivingEntity user, SoundEvent sound, float volume, float minPitch, float maxPitch) {
        float pitch = new Random().nextFloat(minPitch, maxPitch);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), sound, SoundCategory.PLAYERS, volume, pitch);
    }
}