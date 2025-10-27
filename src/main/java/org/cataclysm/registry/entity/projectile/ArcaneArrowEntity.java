package org.cataclysm.registry.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.cataclysm.registry.damage.type.CataclysmDamageTypes;

public class ArcaneArrowEntity extends ArrowEntity {
    public ArcaneArrowEntity(EntityType<? extends ArrowEntity> type, World world) {
        super(type, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.age >= 200) discard();
    }

    @Override
    protected void onEntityHit(EntityHitResult hit) {
        Entity target = hit.getEntity();
        Entity owner = this.getOwner();

        float damage = (float) this.getDamage();
        DamageSource damageSource = this.getDamageSources().create(CataclysmDamageTypes.ARCANE_ARROW, this, owner instanceof LivingEntity ? owner : null);

        target.damage(damageSource, damage);

        super.onEntityHit(hit);
    }
}