package org.cataclysm.mixin.render;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.cataclysm.Cataclysm;
import org.cataclysm.registry.item.CataclysmItems;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.EnumSet;
import java.util.Set;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Shadow public abstract ItemModels getModels();

    @Unique private static final Set<ModelTransformationMode> INVENTORY_MODES = EnumSet.of(ModelTransformationMode.GUI, ModelTransformationMode.GROUND, ModelTransformationMode.FIXED);

    @ModifyVariable(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "HEAD"),
            argsOnly = true
    )
    private BakedModel cataclysm$renderItemReplaceInventoryModel(BakedModel bakedModel, @Local(argsOnly = true) @NotNull ItemStack stack, @Local(argsOnly = true) ModelTransformationMode mode) {
        if (!INVENTORY_MODES.contains(mode)) return bakedModel;

        String path = cataclysm$getInventoryPath(stack.getItem());
        return path == null ? bakedModel : cataclysm$model(path);
    }

    @ModifyVariable(method = "getModel", at = @At(value = "STORE"), ordinal = 1)
    private BakedModel cataclysm$getHeldItemModel(BakedModel bakedModel, @Local(argsOnly = true) @NotNull ItemStack stack) {
        String path = cataclysm$getHeldPath(stack.getItem());
        return path == null ? bakedModel : cataclysm$model(path);
    }

    @Unique
    private BakedModel cataclysm$model(String path) {
        return getModels().getModelManager().getModel(ModelIdentifier.ofInventoryVariant(Cataclysm.identifier(path)));
    }

    @Unique
    private String cataclysm$getInventoryPath(Item item) {
        if (item == CataclysmItems.ARCANE_TRIDENT) return "arcane_trident";
        if (item == CataclysmItems.ARCANE_MACE)    return "arcane_mace";
        if (item == CataclysmItems.ARCANE_SHIELD)  return "arcane_shield";
        return null;
    }

    @Unique
    private String cataclysm$getHeldPath(Item item) {
        if (item == CataclysmItems.ARCANE_TRIDENT) return "arcane_trident_3d";
        if (item == CataclysmItems.ARCANE_MACE)    return "arcane_mace_3d";
        if (item == CataclysmItems.ARCANE_SHIELD)  return "arcane_shield_3d";
        return null;
    }
}