package org.cataclysm.mixin.render;

import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import org.cataclysm.Cataclysm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Shadow protected abstract void loadItemModel(ModelIdentifier id);

    @Unique private static final ModelIdentifier[] CATACLYSM_MODELS = new ModelIdentifier[] {
            ModelIdentifier.ofInventoryVariant(Cataclysm.identifier("arcane_trident_3d")),
            ModelIdentifier.ofInventoryVariant(Cataclysm.identifier("arcane_mace_3d")),
            ModelIdentifier.ofInventoryVariant(Cataclysm.identifier("arcane_shield_3d"))
    };

    @Inject(method = "<init>", at = @At("TAIL"))
    private void cataclysm$loadExtraModels(CallbackInfo ci) {
        for (ModelIdentifier id : CATACLYSM_MODELS) {
            this.loadItemModel(id);
        }
    }
}