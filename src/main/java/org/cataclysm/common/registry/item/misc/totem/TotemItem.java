package org.cataclysm.common.registry.item.misc.totem;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.world.event.GameEvent;
import org.cataclysm.common.registry.effect.CataclysmEffects;
import org.cataclysm.common.registry.item.interfaces.Resuscitator;

public class TotemItem extends Item implements Resuscitator {
    public TotemItem(Settings settings) {
        super(settings);
    }

    public float getMortality() {
        return 1.0F;
    }

    @Override
    public void onResurrect(LivingEntity entity) {
        if (!(entity.getWorld() instanceof ServerWorld)) return;

        if (entity instanceof ServerPlayerEntity player) {
            player.incrementStat(Stats.USED.getOrCreateStat(this));
            Criteria.USED_TOTEM.trigger(player, this.getDefaultStack());
            entity.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
        }

        entity.clearStatusEffects();

        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));

        entity.getWorld().sendEntityStatus(entity, (byte)35);
    }

    @Override
    public boolean canResurrect(LivingEntity entity, DamageSource source) {
        return !source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY) || !entity.hasStatusEffect(CataclysmEffects.MORTEM);
    }

    @Override
    public boolean shouldConsume() {
        return true;
    }
}