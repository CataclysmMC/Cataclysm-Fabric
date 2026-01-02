package org.cataclysm.common.util;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;

import java.util.List;

public final class RegistryUtils {
    private RegistryUtils() {}

    public static <T> void logRegistrations(Registry<T> registry) {
        String registryName = registry.getKey().getValue().getPath().toUpperCase();
        int entryCount = getEntries(registry).size();

        if (entryCount > 0) {
            Cataclysm.LOGGER.info("Successfully registered {} {} entries", entryCount, registryName);
        } else {
            Cataclysm.LOGGER.debug("No {} entries registered for mod '{}'", registryName, Cataclysm.MOD_ID);
        }
    }

    public static <T> List<T> getEntries(Registry<T> registry) {
        return registry.stream()
                .filter(entry -> {
                    Identifier id = registry.getId(entry);
                    return id != null && Cataclysm.MOD_ID.equals(id.getNamespace());
                })
                .toList();
    }
}