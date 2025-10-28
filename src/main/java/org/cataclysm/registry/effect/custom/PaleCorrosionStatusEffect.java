package org.cataclysm.registry.effect.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;

public class PaleCorrosionStatusEffect extends StatusEffect {
    public PaleCorrosionStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0xD4D4D4);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 40 == 0;
    }
}