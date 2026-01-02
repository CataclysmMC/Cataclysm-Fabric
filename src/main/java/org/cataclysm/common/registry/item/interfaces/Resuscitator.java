package org.cataclysm.common.registry.item.interfaces;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public interface Resuscitator extends Modeled {

    boolean canResurrect(LivingEntity entity, DamageSource source);

    void onResurrect(LivingEntity entity);

    boolean shouldConsume();

    default float getRestoredHealth() {
        return 1.0F;
    }
}