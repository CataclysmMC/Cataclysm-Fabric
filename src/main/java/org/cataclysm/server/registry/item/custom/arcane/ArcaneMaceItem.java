package org.cataclysm.server.registry.item.custom.arcane;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ArcaneMaceItem extends MaceItem {
    public ArcaneMaceItem() {
        super(new Settings()
                .component(DataComponentTypes.TOOL, MaceItem.createToolComponent())
                .attributeModifiers(MaceItem.createAttributeModifiers())
                .rarity(Rarity.EPIC)
                .maxDamage(250)
                .maxCount(1)
        );
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!(world instanceof ServerWorld server)) {
            return TypedActionResult.pass(stack);
        }

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY, SoundCategory.PLAYERS, 1.5F, 1.0F);
        this.spawnWindCharge(server, world, player);

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
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_WIND_CHARGE_THROW, SoundCategory.PLAYERS, 1.5F, 1.0F);
    }
}