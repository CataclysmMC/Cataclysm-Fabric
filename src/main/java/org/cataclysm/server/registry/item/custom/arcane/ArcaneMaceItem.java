package org.cataclysm.server.registry.item.custom.arcane;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ArcaneMaceItem extends MaceItem implements ArcaneItem {
    public ArcaneMaceItem() {
        super(ArcaneItem.getSettings()
                .component(DataComponentTypes.TOOL, createToolComponent())
                .attributeModifiers(createAttributeModifiers())
        );
    }

    @Override
    public int getEnchantability() {
        return 22;
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (world.isClient()) return TypedActionResult.pass(stack);

        WindChargeEntity charge = new WindChargeEntity(user, world, user.getX(), user.getEyeY(), user.getZ());
        charge.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.6F, 0.9F);
        world.spawnEntity(charge);

        playSound(world, user, SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY, 1.5F, 1.2F, 1.8F);
        playSound(world, user, SoundEvents.ENTITY_WIND_CHARGE_THROW, 1.5F, 0.9F, 1.3F);

        user.getItemCooldownManager().set(this, 30);
        user.incrementStat(Stats.USED.getOrCreateStat(this));

        return TypedActionResult.success(stack, false);
    }
}