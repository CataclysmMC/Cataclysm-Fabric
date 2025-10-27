package org.cataclysm.registry.model;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;
import org.cataclysm.registry.item.CataclysmItems;

public class CataclysmModelPredicates {
    public static void registerAllModelPredicates() {
        registerBow(CataclysmItems.ARCANE_BOW);
        registerTwistedTools();
    }

    private static void registerTwistedTools() {
        registerTwistedAlt(CataclysmItems.TWISTED_SWORD);
        registerTwistedAlt(CataclysmItems.TWISTED_AXE);
        registerTwistedAlt(CataclysmItems.TWISTED_PICKAXE);
        //registerTwistedAlt(CataclysmItems.TWISTED_SHOVEL);
        //registerTwistedAlt(CataclysmItems.TWISTED_HOE);
    }

    private static void registerTwistedAlt(Item item) {
        ModelPredicateProviderRegistry.register(item, Cataclysm.identifier("alt_mode"), (stack, w, e, s) -> {
                    NbtCompound rootCompound = stack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT).copyNbt();
                    NbtCompound twistedCompound = rootCompound.contains("twisted", NbtElement.COMPOUND_TYPE) ? rootCompound.getCompound("twisted") : new NbtCompound();
                    return twistedCompound.getBoolean("alt") ? 1.0F : 0.0F;
                }
        );
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
