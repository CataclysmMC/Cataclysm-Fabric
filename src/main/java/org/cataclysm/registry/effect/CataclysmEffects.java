package org.cataclysm.registry.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;
import org.cataclysm.registry.effect.custom.CleansingStatusEffect;
import org.cataclysm.registry.effect.custom.PaleCorrosionStatusEffect;
import org.cataclysm.registry.effect.custom.ImmunityStatusEffect;
import org.cataclysm.registry.effect.custom.MortemStatusEffect;

public final class CataclysmEffects {
    public static final RegistryEntry<StatusEffect> IMMUNITY = registerEffect("immunity", new ImmunityStatusEffect());
    public static final RegistryEntry<StatusEffect> CLEANSING = registerEffect("cleansing", new CleansingStatusEffect());
    public static final RegistryEntry<StatusEffect> MORTEM = registerEffect("mortem", new MortemStatusEffect());
    public static final RegistryEntry<StatusEffect> PALE_CORROSION = registerEffect("pale_corrosion", new PaleCorrosionStatusEffect());

    public static void registerAllEffects() {}

    private static RegistryEntry<StatusEffect> registerEffect(String path, StatusEffect effect) {
        Identifier id = Cataclysm.identifier(path);
        return Registry.registerReference(Registries.STATUS_EFFECT, id, effect);
    }
}
