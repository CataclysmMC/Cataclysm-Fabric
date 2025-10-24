package org.cataclysm.global.registry.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;
import org.cataclysm.global.registry.effect.custom.ImmunityStatusEffect;

public class CataclysmEffects {
    public static final RegistryEntry<StatusEffect> IMMUNITY = registerEffect("immunity", new ImmunityStatusEffect());

    public static void initEffects() {}

    private static RegistryEntry<StatusEffect> registerEffect(String path, StatusEffect effect) {
        Identifier id = Cataclysm.identifier(path);
        return Registry.registerReference(Registries.STATUS_EFFECT, id, effect);
    }
}
