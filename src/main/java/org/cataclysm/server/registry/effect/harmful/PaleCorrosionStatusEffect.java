package org.cataclysm.server.registry.effect.harmful;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class PaleCorrosionStatusEffect extends StatusEffect {
    public PaleCorrosionStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0xD4D4D4);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 40 == 0;
    }
}