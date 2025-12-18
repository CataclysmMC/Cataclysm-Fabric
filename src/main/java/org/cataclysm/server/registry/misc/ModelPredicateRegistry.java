package org.cataclysm.server.registry.misc;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;
import org.cataclysm.server.registry.item.CataclysmItems;

public class ModelPredicateRegistry {
    private static final String TWISTED_NBT_KEY = "twisted";
    private static final String ALT_MODE_KEY = "alt";
    private static final float BOW_DRAW_SPEED = 20.0F;

    public static void register() {
        registerBowPredicates(CataclysmItems.ARCANE_BOW);
        registerTwistedToolPredicates();
    }

    private static void registerTwistedToolPredicates() {
        registerAlternateMode(CataclysmItems.TWISTED_SWORD);
        registerAlternateMode(CataclysmItems.TWISTED_AXE);
        registerAlternateMode(CataclysmItems.TWISTED_PICKAXE);
        //registerAlternateMode(CataclysmItems.TWISTED_SHOVEL);
        //registerAlternateMode(CataclysmItems.TWISTED_HOE);
    }

    private static void registerAlternateMode(Item item) {
        ModelPredicateProviderRegistry.register(
                item,
                Cataclysm.identifier("alt_mode"),
                (itemStack, world, entity, seed) -> {
                    NbtCompound customData = itemStack.getOrDefault(
                            DataComponentTypes.CUSTOM_DATA,
                            NbtComponent.DEFAULT
                    ).copyNbt();

                    if (!customData.contains(TWISTED_NBT_KEY, NbtElement.COMPOUND_TYPE)) {
                        return 0.0F;
                    }

                    NbtCompound twistedData = customData.getCompound(TWISTED_NBT_KEY);
                    return twistedData.getBoolean(ALT_MODE_KEY) ? 1.0F : 0.0F;
                }
        );
    }

    private static void registerBowPredicates(Item bowItem) {
        registerBowPullProgress(bowItem);
        registerBowPullingState(bowItem);
    }

    private static void registerBowPullProgress(Item bowItem) {
        ModelPredicateProviderRegistry.register(
                bowItem,
                Identifier.ofVanilla("pull"),
                (itemStack, world, entity, seed) -> {
                    if (entity == null || !isEntityUsingItem(entity, itemStack)) {
                        return 0.0F;
                    }

                    int maxUseTime = itemStack.getMaxUseTime(entity);
                    int remainingUseTime = entity.getItemUseTimeLeft();
                    return (float)(maxUseTime - remainingUseTime) / BOW_DRAW_SPEED;
                }
        );
    }

    private static void registerBowPullingState(Item bowItem) {
        ModelPredicateProviderRegistry.register(
                bowItem,
                Identifier.ofVanilla("pulling"),
                (itemStack, world, entity, seed) ->
                        entity != null && isEntityUsingItem(entity, itemStack) ? 1.0F : 0.0F
        );
    }

    private static boolean isEntityUsingItem(net.minecraft.entity.LivingEntity entity, net.minecraft.item.ItemStack itemStack) {
        return entity.isUsingItem() && entity.getActiveItem() == itemStack;
    }
}