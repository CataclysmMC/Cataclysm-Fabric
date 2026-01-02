package org.cataclysm.common.registry.entity.projectile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.world.World;

public class ArcaneTridentEntity extends TridentEntity {
    public ArcaneTridentEntity(EntityType<ArcaneTridentEntity> type, World world) {
        super(type, world);
    }
}