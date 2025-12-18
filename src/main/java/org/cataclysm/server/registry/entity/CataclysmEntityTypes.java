package org.cataclysm.server.registry.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.cataclysm.Cataclysm;
import org.cataclysm.server.registry.entity.projectile.ArcaneTridentEntity;

public final class CataclysmEntityTypes {
    public static final EntityType<ArcaneTridentEntity> ARCANE_TRIDENT = register("arcane_trident", EntityType.Builder.<ArcaneTridentEntity>create(ArcaneTridentEntity::new, SpawnGroup.MISC).dimensions(0.5f, 0.5f).build());

    public static void initialize() {}

    private static <T extends Entity> EntityType<T> register(final String id, final EntityType<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, Cataclysm.identifier(id), type);
    }

    private CataclysmEntityTypes() {}
}