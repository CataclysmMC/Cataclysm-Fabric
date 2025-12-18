package org.cataclysm.server.registry.item.custom.paragon;

import net.minecraft.component.DataComponentTypes;
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
import net.minecraft.util.Rarity;
import org.cataclysm.server.registry.effect.CataclysmEffects;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractParagonItem extends Item {
    private static final int IMMUNITY_DEFAULT_DURATION = 300;

    public AbstractParagonItem(int maxCount) {
        super(new Settings()
                .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                .rarity(Rarity.EPIC)
                .maxCount(maxCount)
        );
    }

    public abstract int getImmunityTicks();

    public void applyImmunity(ServerWorld world, LivingEntity livingEntity) {
        this.applyImmunity(world, List.of(livingEntity));
    }
    public void applyImmunity(ServerWorld serverWorld, List<LivingEntity> livingEntities) {
        livingEntities.forEach(livingEntity -> {
            this.applyEffects(livingEntity);
            this.spawnParticles(serverWorld, livingEntity.getX(), livingEntity.getBodyY(0.5), livingEntity.getZ());
            this.playSounds(livingEntity, serverWorld);
        });
    }

    private void applyEffects(LivingEntity livingEntity) {
        List<RegistryEntry<StatusEffect>> effects = List.of(
                CataclysmEffects.IMMUNITY,
                StatusEffects.REGENERATION,
                StatusEffects.GLOWING
        );

        effects.forEach(effect ->
                applyOrStackEffect(livingEntity, new StatusEffectInstance(effect, this.getImmunityTicks(), 0)));
    }

    private void spawnParticles(ServerWorld world, double x, double y, double z) {
        world.spawnParticles(
                ParticleTypes.END_ROD,
                x, y, z,
                50,
                0.3, 0.7, 0.3,
                0.17
        );
    }

    private void playSounds(LivingEntity entity, ServerWorld world) {
        float f = 1.0F - (float) this.getImmunityTicks() / getImmunityDefaultTicks();

        world.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE,
                SoundCategory.PLAYERS, 0.5F, 0.7F + f * 0.2F);
        world.playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_IRON_GOLEM_DEATH,
                SoundCategory.PLAYERS, 0.5F, 0.9F + f * 0.2F);
        world.playSound(null, entity.getBlockPos(), SoundEvents.ITEM_TOTEM_USE,
                SoundCategory.PLAYERS, 0.5F, 1.6F + f * 0.2F);
    }

    private static void applyOrStackEffect(LivingEntity livingEntity, StatusEffectInstance newEffect) {
        StatusEffectInstance currentEffect = livingEntity.getStatusEffect(newEffect.getEffectType());

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
     * @return 300
     */
    public static int getImmunityDefaultTicks() {
        return IMMUNITY_DEFAULT_DURATION;
    }

    public static int getImmunityConditionalTicks() {
        return IMMUNITY_DEFAULT_DURATION;
    }

    public void consumeItem(@NotNull PlayerEntity player, @NotNull ItemStack itemStack) {
        player.incrementStat(Stats.USED.getOrCreateStat(itemStack.getItem()));
        itemStack.decrementUnlessCreative(1, player);
    }
}