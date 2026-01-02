package org.cataclysm.common.registry.item.misc.paragon;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import org.cataclysm.common.registry.effect.CataclysmEffects;
import org.cataclysm.common.registry.item.interfaces.Modeled;

import java.util.List;

public abstract class ParagonItem extends Item implements Modeled {
    public static final int IMMUNITY_DEFAULT_DURATION = 300;
    public static final int IMMUNITY_CONDITIONAL_DURATION = 300;

    public ParagonItem(Settings settings) {
        super(settings);
    }

    public abstract int getImmunityTicks();

    public void applyImmunity(ServerWorld world, LivingEntity livingEntity) {
        this.applyImmunity(world, List.of(livingEntity));
    }

    public void applyImmunity(ServerWorld serverWorld, List<LivingEntity> livingEntities) {
        livingEntities.forEach(livingEntity -> {
            this.applyEffects(livingEntity);
            this.playSounds(livingEntity, serverWorld);
            serverWorld.spawnParticles(ParticleTypes.END_ROD, livingEntity.getX(), livingEntity.getBodyY(0.5), livingEntity.getZ(), 50, 0.3, 0.7, 0.3, 0.17);
        });
    }

    private void applyEffects(LivingEntity livingEntity) {
        List<RegistryEntry<StatusEffect>> effects = List.of(CataclysmEffects.IMMUNITY, StatusEffects.REGENERATION, StatusEffects.GLOWING);

        effects.forEach(effect -> applyOrStackEffect(livingEntity, new StatusEffectInstance(effect, this.getImmunityTicks(), 0)));
    }

    private void playSounds(LivingEntity entity, ServerWorld world) {
        float pitch = 1.0F - (float) this.getImmunityTicks() / IMMUNITY_DEFAULT_DURATION;

        world.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 0.5F, 0.7F + pitch * 0.2F);
        world.playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_IRON_GOLEM_DEATH, SoundCategory.PLAYERS, 0.5F, 0.9F + pitch * 0.2F);
        world.playSound(null, entity.getBlockPos(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 0.5F, 1.6F + pitch * 0.2F);
    }

    private static void applyOrStackEffect(LivingEntity livingEntity, StatusEffectInstance newEffect) {
        StatusEffectInstance currentEffect = livingEntity.getStatusEffect(newEffect.getEffectType());

        if (currentEffect != null) {
            int newDuration = currentEffect.getDuration() + newEffect.getDuration();
            int newAmplifier = Math.max(currentEffect.getAmplifier(), newEffect.getAmplifier());
            livingEntity.addStatusEffect(new StatusEffectInstance(newEffect.getEffectType(), newDuration, newAmplifier, newEffect.isAmbient(), newEffect.shouldShowParticles(), newEffect.shouldShowIcon()));
        } else {
            livingEntity.addStatusEffect(newEffect);
        }
    }

    public void consumeItem(PlayerEntity player, ItemStack stack) {
        player.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
        stack.decrementUnlessCreative(1, player);
    }
}