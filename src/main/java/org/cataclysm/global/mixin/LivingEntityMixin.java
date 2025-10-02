package org.cataclysm.global.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.cataclysm.server.effects.CataclysmEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;

        // Immunity status effect logic
        if (self.hasStatusEffect(CataclysmEffects.IMMUNITY)) {
            SoundCategory category = SoundCategory.HOSTILE;
            if (self instanceof PlayerEntity) category = SoundCategory.PLAYERS;

            world.playSound(null, self.getBlockPos(), SoundEvents.ENTITY_IRON_GOLEM_REPAIR, category, 0.75f, 0.9f);
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}