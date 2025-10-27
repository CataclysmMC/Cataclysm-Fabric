package org.cataclysm.registry.damage.type;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.cataclysm.Cataclysm;

public class CataclysmDamageTypes {
    public static final RegistryKey<DamageType> ARCANE_ARROW = registerDamageType("arcane_arrow");

    public static void registerAllDamageTypes() {};

    private static RegistryKey<DamageType> registerDamageType(String path) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Cataclysm.identifier(path));
    }
}
