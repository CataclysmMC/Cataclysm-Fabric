package org.cataclysm.server.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;
import org.cataclysm.server.item.paragon.ParagonBlessingItem;
import org.cataclysm.server.item.paragon.ParagonPearlItem;
import org.cataclysm.server.item.paragon.ParagonTotemItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.function.Function;

public final class CataclysmItems {

    public static final Item PARAGON_BLESSING = register("paragon_blessing", ParagonBlessingItem::new);
    public static final Item PARAGON_TOTEM = register("paragon_totem", ParagonTotemItem::new);
    public static final Item PARAGON_PEARL = register("paragon_pearl", ParagonPearlItem::new);
    public static final Item PARAGON_KEY = register("paragon_key");
    public static final Item PARAGON_QUARTZ = register("paragon_quartz");

    public static void init() {
        // Forzar carga de clase y mostrar debug
        Cataclysm.LOGGER.info("✅ CataclysmItems inicializado: {} ítems registrados para '{}'", getItems().size(), Cataclysm.MOD_ID);
    }

    private static Item register(String name) {
        return register(name, Item::new);
    }

    private static Item register(String name, @NotNull Function<Item.Settings, Item> itemFactory) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Cataclysm.MOD_ID, name));
        Item item = itemFactory.apply(new Item.Settings().registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    public static @NotNull @Unmodifiable List<Item> getItems() {
        return Registries.ITEM.stream()
                .filter(item -> Registries.ITEM.getId(item).getNamespace().equals(Cataclysm.MOD_ID))
                .toList();
    }
}