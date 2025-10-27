package org.cataclysm.registry.item.custom.tool.arcane;

import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.cataclysm.registry.entity.CataclysmEntityTypes;
import org.cataclysm.registry.entity.projectile.ArcaneArrowEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class ArcaneBowItem extends BowItem implements ArcaneItem {
    private static final int TICKS_PER_SECOND = 20;
    private static final float[] SPREAD = {-10f, 0f, 10f};

    public ArcaneBowItem() {
        super(ArcaneItem.getSettings());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!user.getAbilities().creativeMode) {
            boolean infinity = hasEnchant(world, stack, Enchantments.INFINITY);
            int needed = infinity ? 1 : 3;
            if (user.getInventory().count(Items.SPECTRAL_ARROW) < needed) {
                return TypedActionResult.fail(stack);
            }
        }

        user.setCurrentHand(hand);
        return TypedActionResult.consume(stack);
    }

    @Override
    public void onStoppedUsing(@NotNull ItemStack stack, World world, @NotNull LivingEntity user, int remainingUseTicks) {
        if (!(world instanceof ServerWorld server)) return;

        boolean infinity = hasEnchant(world, stack, Enchantments.INFINITY);
        boolean flame = hasEnchant(world, stack, Enchantments.FLAME);
        int punch = getLevel(world, stack, Enchantments.PUNCH);

        int charge = getMaxUseTime(stack, user) - remainingUseTicks;
        float power = getPullProgress(charge);
        if (power < 0.1F) return;

        for (float yaw : SPREAD)
            spawnArcaneArrow(server, user, power, yaw, infinity, punch, flame);

        if (!infinity && user instanceof PlayerEntity p)
            consumeArcaneArrow(p, 3);

        playSound(world, user, SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, 1.5F, 0.8F, 1.2F);

        if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
            stack.damage(1, user, LivingEntity.getSlotForHand(user.getActiveHand()));
        }
    }

    private void playSound(World world, LivingEntity user, SoundEvent sound, float volume, float minPitch, float maxPitch) {
        float pitch = new Random().nextFloat(minPitch, maxPitch);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), sound, SoundCategory.PLAYERS, volume, pitch);
    }

    private void spawnArcaneArrow(ServerWorld world, LivingEntity shooter, float power, float yawSpread, boolean infinity, int punch, boolean flame) {
        ArcaneArrowEntity arrow = new ArcaneArrowEntity(CataclysmEntityTypes.ARCANE_ARROW, world);
        arrow.setOwner(shooter);
        arrow.setPosition(shooter.getX(), shooter.getEyeY() - 0.1, shooter.getZ());
        arrow.setCritical(power >= 1.0F);
        if (flame) arrow.setOnFireFor(5 * TICKS_PER_SECOND);

        float speed = power * (3.0F + punch * 0.3F);

        arrow.pickupType = infinity
                ? PersistentProjectileEntity.PickupPermission.DISALLOWED
                : PersistentProjectileEntity.PickupPermission.ALLOWED;

        arrow.setVelocity(shooter, shooter.getPitch(), shooter.getYaw() + yawSpread, 0.0F, speed, 1.0F);
        world.spawnEntity(arrow);
    }

    private void consumeArcaneArrow(PlayerEntity player, int n) {
        var inv = player.getInventory();
        for (int i = 0; i < inv.size() && n > 0; i++) {
            ItemStack s = inv.getStack(i);
            if (s.isOf(Items.SPECTRAL_ARROW)) {
                int take = Math.min(n, s.getCount());
                s.decrement(take);
                n -= take;
                if (s.isEmpty()) inv.setStack(i, ItemStack.EMPTY);
            }
        }
    }

    private int getLevel(World world, ItemStack stack, RegistryKey<Enchantment> key) {
        var reg = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT);
        var entry = reg.getEntry(key).orElse(null);
        if (entry == null) return 0;
        ItemEnchantmentsComponent ench = stack.getEnchantments();
        return ench.getLevel(entry);
    }

    private boolean hasEnchant(World world, ItemStack stack, RegistryKey<Enchantment> key) {
        return getLevel(world, stack, key) > 0;
    }
}