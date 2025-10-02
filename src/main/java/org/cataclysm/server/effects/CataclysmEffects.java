package org.cataclysm.server.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;

public final class CataclysmEffects {

    public static final RegistryEntry<StatusEffect> IMMUNITY = register("immunity", new ImmunityStatusEffect());

    public static void init() {
        Cataclysm.LOGGER.info(
                "✅ CataclysmEffects inicializado: {} efectos registrados para '{}'",
                getRegisteredEffectCount(),
                Cataclysm.MOD_ID
        );
    }

    private static RegistryEntry<StatusEffect> register(String name, StatusEffect effect) {
        return Registry.registerReference(
                Registries.STATUS_EFFECT,
                Identifier.of(Cataclysm.MOD_ID, name),
                effect
        );
    }

    private static long getRegisteredEffectCount() {
        return Registries.STATUS_EFFECT.stream()
                .filter(effect -> {
                    Identifier id = Registries.STATUS_EFFECT.getId(effect);
                    return id != null && id.getNamespace().equals(Cataclysm.MOD_ID);
                })
                .count();
    }
}