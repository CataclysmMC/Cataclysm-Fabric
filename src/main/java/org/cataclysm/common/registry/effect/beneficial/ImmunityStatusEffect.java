package org.cataclysm.common.registry.effect.beneficial;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ImmunityStatusEffect extends StatusEffect {
    public ImmunityStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xFFD700);
    }
}