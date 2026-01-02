package org.cataclysm.common.registry;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;
import org.cataclysm.common.registry.item.CataclysmItems;

public final class ModelPredicateRegistry {
    public static void initialize() {
        initializeTwistedTools();
        registerBow(CataclysmItems.ARCANE_BOW);
    }

    private static void initializeTwistedTools() {
        registerTwisted(CataclysmItems.TWISTED_SWORD);
        registerTwisted(CataclysmItems.TWISTED_AXE);
        registerTwisted(CataclysmItems.TWISTED_PICKAXE);
        registerTwisted(CataclysmItems.TWISTED_SHOVEL);
        registerTwisted(CataclysmItems.TWISTED_HOE);
    }

    private static void registerTwisted(Item item) {
        ModelPredicateProviderRegistry.register(item, Cataclysm.identifier("alt_mode"),
                (itemStack, world, entity, seed) -> {
                    NbtCompound customData = itemStack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT).copyNbt();
                    NbtCompound twistedData = customData.getCompound("twisted");
                    return twistedData.getBoolean("alt") ? 1.0F : 0.0F;
                }
        );
    }

    private static void registerBow(Item item) {
        registerBowPullProgress(item);
        registerBowPullState(item);
    }

    private static void registerBowPullProgress(Item item) {
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pull"),
                (itemStack, world, entity, seed) -> {
                    if (entity == null || !isEntityUsingItem(entity, itemStack)) return 0.0F;

                    int maxUseTime = itemStack.getMaxUseTime(entity);
                    int remainingUseTime = entity.getItemUseTimeLeft();
                    return (float)(maxUseTime - remainingUseTime) / 20.0F;
                }
        );
    }

    private static void registerBowPullState(Item item) {
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pulling"),
                (itemStack, world, entity, seed) -> entity != null && isEntityUsingItem(entity, itemStack) ? 1.0F : 0.0F
        );
    }

    private static boolean isEntityUsingItem(LivingEntity entity, ItemStack itemStack) {
        return entity.isUsingItem() && entity.getActiveItem() == itemStack;
    }
}