package org.cataclysm.mixin;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.cataclysm.server.registry.effect.CataclysmEffects;
import org.cataclysm.server.registry.item.custom.misc.CataclysmTotem;
import org.cataclysm.server.registry.item.custom.paragon.ParagonTotem;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow
    public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void onTryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            cir.setReturnValue(false);
            return;
        }

        ItemStack totem = CataclysmTotem.getActiveTotemStack(entity());
        if (!totem.isEmpty() && world() instanceof ServerWorld serverWorld) {
            this.useTotem(serverWorld, totem);
            cir.setReturnValue(true);
            return;
        }

        cir.setReturnValue(false);
    }
    @Unique
    private void useTotem(ServerWorld serverWorld, @NotNull ItemStack totem) {
        if (!(totem.getItem() instanceof ParagonTotem) && entity().hasStatusEffect(CataclysmEffects.MORTEM)) {
            world().playSound(null, entity().getBlockPos(), SoundEvents.ENTITY_BLAZE_DEATH, SoundCategory.PLAYERS, 5.0F, 0.5F);
            world().playSound(null, entity().getBlockPos(), SoundEvents.ITEM_TRIDENT_THUNDER.value(), SoundCategory.PLAYERS, 5.0F, 1.75F);
            return;
        }

        entity().setHealth(1.0F);
        entity().clearStatusEffects();

        if (totem.getItem() instanceof ParagonTotem paragon) {
            world().playSound(null, entity().getBlockPos(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1.0F, 0.5F);
            paragon.applyImmunity(serverWorld, entity());
        }

        world().sendEntityStatus(entity(), (byte) 35);
        entity().addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 900, 1));
        entity().addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));
        entity().addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 800, 0));

        if (entity() instanceof ServerPlayerEntity player) {
            player.incrementStat(Stats.USED.getOrCreateStat(Items.TOTEM_OF_UNDYING));
            Criteria.USED_TOTEM.trigger(player, totem);
            player.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
            totem.decrement(1);
        }
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!entity().hasStatusEffect(CataclysmEffects.IMMUNITY)) return;

        SoundCategory category = entity() instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
        world().playSound(null, entity().getBlockPos(), SoundEvents.ENTITY_IRON_GOLEM_REPAIR, category, 0.75f, 0.9f);

        cir.setReturnValue(false);
        cir.cancel();
    }

    private @Unique LivingEntity entity() {
        return (LivingEntity) (Object) this;
    }

    private @Unique World world() {
        return entity().getWorld();
    }
}