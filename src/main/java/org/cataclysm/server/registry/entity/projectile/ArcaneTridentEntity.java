package org.cataclysm.server.registry.entity.projectile;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class ArcaneTridentEntity extends TridentEntity {
    public ArcaneTridentEntity(@NotNull World world, @NotNull LivingEntity owner, @NotNull net.minecraft.item.ItemStack stack) {
        super(world, owner, stack);
    }

    @Override
    protected void onEntityHit(EntityHitResult hit) {
        Entity target = hit.getEntity();
        World world = getWorld();
        Entity owner = getOwner() != null ? getOwner() : this;
        ItemStack weapon = getWeaponStack();
        if (weapon == null) weapon = ItemStack.EMPTY;

        DamageSource source = getDamageSources().trident(this, owner);
        float damage = 8.0F;
        if (world instanceof ServerWorld server) {
            damage = EnchantmentHelper.getDamage(server, weapon, target, source, damage);
        }

        if (!target.damage(source, damage * 4)) {
            setVelocity(getVelocity().multiply(-0.01, -0.1, -0.01));
            playSound(SoundEvents.ITEM_TRIDENT_HIT, 1.0F, 0.9F);
            return;
        }

        if (target instanceof LivingEntity living) {
            knockback(living, source);
            onHit(living);
        }

        setVelocity(getVelocity().multiply(-0.01, -0.1, -0.01));
        playSound(SoundEvents.ITEM_TRIDENT_HIT, 1.0F, 0.9F);
    }
}