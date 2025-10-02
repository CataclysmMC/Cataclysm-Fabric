package org.cataclysm.server.item.paragon;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Rarity;
import org.cataclysm.Cataclysm;
import org.cataclysm.server.effects.CataclysmEffects;
import org.cataclysm.server.item.CataclysmItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class ParagonItem extends CataclysmItem {

    public static int IMMUNITY_BASE_TICKS = 300;

    public ParagonItem(@NotNull Settings settings) {
        super(settings
                .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                .rarity(Rarity.EPIC));
    }

    public void applyImmunity(ServerWorld serverWorld, LivingEntity livingEntity, int immunityDuration) {
        this.applyImmunity(serverWorld, List.of(livingEntity), immunityDuration);
    }

    public void applyImmunity(ServerWorld serverWorld, List<LivingEntity> livingEntities, int immunityDuration) {
        livingEntities.forEach(livingEntity -> {
            this.applyEffects(livingEntity, immunityDuration);
            this.spawnParticles(serverWorld, livingEntity.getX(), livingEntity.getBodyY(0.5), livingEntity.getZ());
            this.playSounds(livingEntity, serverWorld, immunityDuration);
        });
    }

    private void applyEffects(LivingEntity livingEntity, int immunityDuration) {
        applyOrStackEffect(livingEntity, new StatusEffectInstance(CataclysmEffects.IMMUNITY, immunityDuration, 0, false, true, true));
        applyOrStackEffect(livingEntity, new StatusEffectInstance(StatusEffects.GLOWING, immunityDuration, 0, false, false, true));
    }

    private void applyOrStackEffect(LivingEntity livingEntity, StatusEffectInstance newEffect) {
        var currentEffect = livingEntity.getStatusEffect(newEffect.getEffectType());

        if (currentEffect != null) {
            int newDuration = currentEffect.getDuration() + newEffect.getDuration();
            int newAmplifier = Math.max(currentEffect.getAmplifier(), newEffect.getAmplifier());
            livingEntity.addStatusEffect(new StatusEffectInstance(
                    newEffect.getEffectType(),
                    newDuration,
                    newAmplifier,
                    newEffect.isAmbient(),
                    newEffect.shouldShowParticles(),
                    newEffect.shouldShowIcon()
            ));
        } else {
            livingEntity.addStatusEffect(newEffect);
        }
    }

    /**
     * Genera partículas en una posición específica, no dependiente del TP inmediato del jugador.
     */
    public void spawnParticles(ServerWorld world, double x, double y, double z) {
        world.spawnParticles(
                ParticleTypes.END_ROD,
                x, y, z,
                50,
                0.3, 0.7, 0.3,
                0.17
        );
    }

    private void playSounds(@NotNull LivingEntity livingEntity, @NotNull ServerWorld serverWorld, int immunityDuration) {
        float factor = 1.0F - (float) immunityDuration / IMMUNITY_BASE_TICKS;

        List<SoundData> soundDataList = List.of(
                SoundData.of(SoundEvents.BLOCK_BEACON_ACTIVATE, 0.7F),
                SoundData.of(SoundEvents.ENTITY_IRON_GOLEM_DEATH, 0.9F),
                SoundData.of(SoundEvents.ITEM_TOTEM_USE, 1.6F)
        );

        soundDataList.forEach(soundData -> {
            Cataclysm.LOGGER.info("{}", soundData.pitch + (factor * 0.3F));
            serverWorld.playSound(null, livingEntity.getBlockPos(), soundData.soundEvent,
                    SoundCategory.PLAYERS, 0.5F, soundData.pitch + (factor * 0.2F));
        });
    }

    record SoundData(SoundEvent soundEvent, float pitch) {
        public static SoundData of(SoundEvent soundEvent, float pitch) {
            return new SoundData(soundEvent, pitch);
        }
    }
}