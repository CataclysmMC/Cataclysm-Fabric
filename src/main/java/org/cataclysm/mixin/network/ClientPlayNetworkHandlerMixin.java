package org.cataclysm.mixin.network;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.cataclysm.common.registry.item.interfaces.Resuscitator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Inject(method = "getActiveTotemOfUndying", at = @At("HEAD"), cancellable = true)
    private static void getActiveTotemOfUndying$0(PlayerEntity player, CallbackInfoReturnable<ItemStack> cir) {
        for (Hand hand : Hand.values()) {
            ItemStack stack = player.getStackInHand(hand);
            if (stack.getItem() instanceof Resuscitator) {
                cir.setReturnValue(stack);
                return;
            }
        }

        cir.setReturnValue(new ItemStack(Items.TOTEM_OF_UNDYING));
    }
}