package org.cataclysm.registry.effect.custom;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class MortemStatusEffect extends StatusEffect {
    public MortemStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0xCCAC00);
    }
}