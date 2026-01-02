package org.cataclysm.common.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;

public final class EffectUtils {
    private EffectUtils() {}

    public static void applyStackableEffect(LivingEntity entity, RegistryEntry<StatusEffect> effect, int duration, int amplifier, boolean stackDuration, boolean stackAmplifier) {
        StatusEffectInstance existing = entity.getStatusEffect(effect);

        if (existing != null) {
            int newDuration = stackDuration ? existing.getDuration() + duration : Math.max(existing.getDuration(), duration);
            int newAmplifier = stackAmplifier ? existing.getAmplifier() + amplifier + 1 : Math.max(existing.getAmplifier(), amplifier);
            entity.addStatusEffect(new StatusEffectInstance(effect, newDuration, newAmplifier, existing.isAmbient(), existing.shouldShowParticles(), existing.shouldShowIcon()));
        } else {
            entity.addStatusEffect(new StatusEffectInstance(effect, duration, amplifier));
        }
    }
}