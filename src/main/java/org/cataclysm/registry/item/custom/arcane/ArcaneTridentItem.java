package org.cataclysm.registry.item.custom.arcane;

import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ArcaneTridentItem extends TridentItem implements ArcaneItem {
    public ArcaneTridentItem() {
        super(ArcaneItem.getSettings()
                .component(DataComponentTypes.TOOL, TridentItem.createToolComponent())
                .attributeModifiers(TridentItem.createAttributeModifiers())
        );
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, @NotNull PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (EnchantmentHelper.getTridentSpinAttackStrength(stack, user) > 0.0F && !user.isTouchingWaterOrRain()) {
            return TypedActionResult.fail(stack);
        }

        user.setCurrentHand(hand);
        return TypedActionResult.consume(stack);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return;

        int maxUseTime = this.getMaxUseTime(stack, user) - remainingUseTicks;
        if (maxUseTime < 10) return;

        float riptideStrength = EnchantmentHelper.getTridentSpinAttackStrength(stack, player) * 1.5F;
        if (riptideStrength > 0.0F && !player.isTouchingWaterOrRain()) return;

        RegistryEntry<SoundEvent> sound = EnchantmentHelper
                .getEffect(stack, EnchantmentEffectComponentTypes.TRIDENT_SOUND)
                .orElse(SoundEvents.ITEM_TRIDENT_THROW);

        if (riptideStrength > 0.0F) this.performRiptideDash(world, player, stack, riptideStrength, sound);
        else if (!world.isClient()) this.throwTrident(world, player, stack, 2.5F * 1.5F, sound);

        player.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    private void throwTrident(World world, PlayerEntity player, ItemStack stack, float throwStrength, RegistryEntry<SoundEvent> sound) {
        TridentEntity trident = new TridentEntity(world, player, stack);
        trident.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, throwStrength, 1.0F);
        if (player.isInCreativeMode()) {
            trident.pickupType = PickupPermission.CREATIVE_ONLY;
        }
        world.spawnEntity(trident);
        world.playSoundFromEntity(null, trident, sound.value(), SoundCategory.PLAYERS, 1.0F, 1.05F);

        if (!player.isInCreativeMode()) {
            player.getInventory().removeOne(stack);
        }
    }

    private void performRiptideDash(World world, PlayerEntity player, ItemStack stack, float riptideStrength, RegistryEntry<SoundEvent> sound) {
        float yaw = player.getYaw();
        float pitch = player.getPitch();

        float x = -MathHelper.sin(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
        float y = -MathHelper.sin(pitch * ((float)Math.PI / 180F));
        float z = MathHelper.cos(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));

        float sqrt = MathHelper.sqrt(x * x + y * y + z * z);
        x *= riptideStrength / sqrt;
        y *= riptideStrength / sqrt;
        z *= riptideStrength / sqrt;

        player.addVelocity(x, y, z);
        player.useRiptide(20, 8.0F, stack);
        if (player.isOnGround()) {
            player.move(MovementType.SELF, new Vec3d(0.0F, 1.2F, 0.0F));
        }

        world.playSoundFromEntity(null, player, sound.value(), SoundCategory.PLAYERS, 1.0F, 1.05F);
    }
}