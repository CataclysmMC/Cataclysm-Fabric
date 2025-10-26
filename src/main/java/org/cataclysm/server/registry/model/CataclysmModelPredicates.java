package org.cataclysm.server.registry.model;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.cataclysm.server.registry.item.CataclysmItems;

public class CataclysmModelPredicates {
    public static void registerModelPredicates() {
        registerBow(CataclysmItems.ARCANE_BOW);
    }

    private static void registerBow(Item item) {
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) return 0.0F;
            else return entity.getActiveItem() != stack ? 0.0F : (float)(stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft()) / 20.0F;
        });
        ModelPredicateProviderRegistry.register(
                item,
                Identifier.ofVanilla("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F
        );
    }
}
