package org.cataclysm.global.mixin.render;

import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import org.cataclysm.Cataclysm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Shadow
    protected abstract void loadItemModel(ModelIdentifier id);

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;loadItemModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 1))
    private void onInit(CallbackInfo ci) {
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Cataclysm.identifier("arcane_trident_3d")));
        this.loadItemModel(ModelIdentifier.ofInventoryVariant(Cataclysm.identifier("arcane_mace_3d")));
    }
}