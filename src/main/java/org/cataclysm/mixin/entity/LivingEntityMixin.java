package org.cataclysm.mixin.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.cataclysm.common.registry.item.interfaces.Resuscitator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void onTryUseTotem(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        ItemStack protectorStack = null;
        Resuscitator deathProtector = null;

        for (Hand hand : Hand.values()) {
            ItemStack stackInHand = entity.getStackInHand(hand);
            Item item = stackInHand.getItem();

            if (item instanceof Resuscitator protector && protector.canResurrect(entity, source)) {
                protectorStack = stackInHand.copy();
                deathProtector = protector;
                stackInHand.decrement(1);
                break;
            }
        }

        if (protectorStack == null || deathProtector == null) {
            cir.setReturnValue(false);
            return;
        }

        if (!deathProtector.canResurrect(entity, source)) {
            cir.setReturnValue(false);
            return;
        }

        entity.setHealth(deathProtector.getRestoredHealth());
        deathProtector.onResurrect(entity);

        cir.setReturnValue(true);
    }
}