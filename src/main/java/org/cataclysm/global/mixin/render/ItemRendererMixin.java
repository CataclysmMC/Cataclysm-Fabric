package org.cataclysm.global.mixin.render;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.ItemStack;
import org.cataclysm.Cataclysm;
import org.cataclysm.server.registry.item.CataclysmItems;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    private @Shadow @Final ItemModels models;

    @ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel renderItem(BakedModel bakedModel, @Local(argsOnly = true) @NotNull ItemStack stack, @Local(argsOnly = true) ModelTransformationMode renderMode) {
        if (stack.getItem() == CataclysmItems.ARCANE_TRIDENT && (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED)) {
            return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Cataclysm.identifier("arcane_trident")));
        }

        if (stack.getItem() == CataclysmItems.ARCANE_MACE && (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED)) {
            return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Cataclysm.identifier("arcane_mace")));
        }

        return bakedModel;
    }

    public @Shadow abstract ItemModels getModels();

    @ModifyVariable(method = "getModel", at = @At(value = "STORE"), ordinal = 1)
    public BakedModel getHeldItemModelMixin(BakedModel bakedModel, @Local(argsOnly = true) @NotNull ItemStack stack) {
        if (stack.getItem() == CataclysmItems.ARCANE_TRIDENT) {
            return this.models.getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Cataclysm.identifier("arcane_trident_3d")));
        }

        if (stack.getItem() == CataclysmItems.ARCANE_MACE) {
            return this.models.getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Cataclysm.identifier("arcane_mace_3d")));
        }

        return bakedModel;
    }
}