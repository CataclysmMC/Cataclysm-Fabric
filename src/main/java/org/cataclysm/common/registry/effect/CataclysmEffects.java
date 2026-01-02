package org.cataclysm.common.registry.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;
import org.cataclysm.common.registry.effect.beneficial.CleansingStatusEffect;
import org.cataclysm.common.registry.effect.beneficial.ImmunityStatusEffect;
import org.cataclysm.common.registry.effect.harmful.MortemStatusEffect;

public final class CataclysmEffects {
    private static final Registry<StatusEffect> REGISTRY = Registries.STATUS_EFFECT;

    public static final RegistryEntry<StatusEffect> IMMUNITY = registerEffect("immunity", new ImmunityStatusEffect());
    public static final RegistryEntry<StatusEffect> CLEANSING = registerEffect("cleansing", new CleansingStatusEffect());
    public static final RegistryEntry<StatusEffect> MORTEM = registerEffect("mortem", new MortemStatusEffect());

    private CataclysmEffects() {}

    public static Registry<StatusEffect> initialize() {
        return REGISTRY;
    }

    private static RegistryEntry<StatusEffect> registerEffect(String path, StatusEffect effect) {
        Identifier identifier = Cataclysm.identifier(path);
        return Registry.registerReference(REGISTRY, identifier, effect);
    }
}