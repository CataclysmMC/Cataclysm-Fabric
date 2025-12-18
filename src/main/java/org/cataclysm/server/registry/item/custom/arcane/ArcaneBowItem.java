package org.cataclysm.server.registry.item.custom.arcane;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Unit;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ArcaneBowItem extends BowItem implements ArcaneItem {
    public ArcaneBowItem() {
        super(ArcaneItem.getSettings());
    }

    @Override
    public void onStoppedUsing(ItemStack bow, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return;

        ItemStack ammo = findSpectralAmmo(player);
        if (ammo.isEmpty()) return;

        int usedTicks = getMaxUseTime(bow, user) - remainingUseTicks;
        float pull = charge(usedTicks);
        if (pull < 0.1F) return;

        if (!(world instanceof ServerWorld server)) return;

        List<ItemStack> shots = loadProjectiles(bow, ammo, player, server);
        if (shots.isEmpty()) return;

        Hand hand = player.getActiveHand() != null ? player.getActiveHand() : Hand.MAIN_HAND;
        float speed = (pull * 3.0F) * 1.5F;
        shootAll(server, player, hand, bow, shots, speed, 1.0F, pull >= 1.0F);

        playSound(world, user, SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK, 1.0F, 1.5F, 2.0F);
        playSound(world, user, SoundEvents.ENTITY_ARROW_SHOOT, 0.8F, 0.9F, 1.1F);
    }

    private static ItemStack findSpectralAmmo(PlayerEntity player) {
        if (player.isCreative()) return Items.SPECTRAL_ARROW.getDefaultStack();

        ItemStack inHand = player.getProjectileType(ItemStack.EMPTY);
        if (inHand.isOf(Items.SPECTRAL_ARROW)) return inHand;

        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack s = player.getInventory().getStack(i);
            if (s.isOf(Items.SPECTRAL_ARROW)) return s;
        }
        return ItemStack.EMPTY;
    }

    private static float charge(int ticks) {
        float t = ticks / 20.0F;
        t = (t * t + 2.0F * t) / 3.0F;
        return Math.min(t, 1.0F);
    }

    private void shootAll(ServerWorld world, LivingEntity shooter, Hand hand, ItemStack bow, List<ItemStack> projectiles, float speed, float inaccuracy, boolean crit) {
        float spread = EnchantmentHelper.getProjectileSpread(world, bow, shooter, 0.0F);
        int n = projectiles.size();
        float step = n <= 1 ? 0.0F : (2.0F * spread) / (n - 1);

        for (int i = 0; i < n; i++) {
            ItemStack ammo = projectiles.get(i);
            if (ammo.isEmpty()) continue;

            float yawOffset = (i - (n - 1) * 0.5F) * step;
            ProjectileEntity proj = createSpectralArrow(world, shooter, bow, ammo, crit);
            proj.setVelocity(shooter, shooter.getPitch(), shooter.getYaw() + yawOffset, 0.0F, speed, inaccuracy);
            world.spawnEntity(proj);
        }
    }

    private ProjectileEntity createSpectralArrow(World world, LivingEntity shooter, ItemStack bow, ItemStack ammo, boolean crit) {
        ArrowItem spectral = (ArrowItem) Items.SPECTRAL_ARROW;
        PersistentProjectileEntity arrow = spectral.createArrow(world, ammo.isEmpty() ? new ItemStack(Items.SPECTRAL_ARROW) : ammo, shooter, bow);
        if (crit) arrow.setCritical(true);
        return arrow;
    }

    private List<ItemStack> loadProjectiles(ItemStack bow, ItemStack ammo, LivingEntity shooter, ServerWorld server) {
        if (!ammo.isOf(Items.SPECTRAL_ARROW)) return List.of();

        int count = EnchantmentHelper.getProjectileCount(server, bow, shooter, 1);
        List<ItemStack> list = new ArrayList<>(count);

        ItemStack first = takeAmmo(bow, ammo, shooter, server);
        if (first.isEmpty()) return List.of();
        list.add(first);

        for (int i = 1; i < count; i++) {
            ItemStack ghost = new ItemStack(Items.SPECTRAL_ARROW);
            ghost.set(DataComponentTypes.INTANGIBLE_PROJECTILE, Unit.INSTANCE);
            list.add(ghost);
        }
        return list;
    }

    private ItemStack takeAmmo(ItemStack bow, ItemStack ammo, LivingEntity shooter, ServerWorld server) {
        int use = 0;

        int infinityLevel = getEnchantmentLevel(server, bow, Enchantments.INFINITY);
        if (infinityLevel == 0 && shooter instanceof PlayerEntity player && !player.isInCreativeMode()) {
            use = EnchantmentHelper.getAmmoUse(server, bow, ammo, 1);
        }

        if (use > ammo.getCount()) return ItemStack.EMPTY;

        if (use == 0) {
            ItemStack ghost = new ItemStack(Items.SPECTRAL_ARROW);
            ghost.set(DataComponentTypes.INTANGIBLE_PROJECTILE, Unit.INSTANCE);
            return ghost;
        }

        ItemStack consumed = ammo.split(use);
        if (ammo.isEmpty() && shooter instanceof PlayerEntity p) p.getInventory().removeOne(ammo);
        return consumed;
    }
}