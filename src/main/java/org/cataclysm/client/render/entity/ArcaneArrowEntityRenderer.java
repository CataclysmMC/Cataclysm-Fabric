package org.cataclysm.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import org.cataclysm.registry.entity.projectile.ArcaneArrowEntity;

@Environment(EnvType.CLIENT)
public class ArcaneArrowEntityRenderer extends ProjectileEntityRenderer<ArcaneArrowEntity> {
    public static final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/projectiles/arrow.png");
    public static final Identifier TIPPED_TEXTURE = Identifier.ofVanilla("textures/entity/projectiles/tipped_arrow.png");

    public ArcaneArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(ArcaneArrowEntity arrowEntity) {
        return arrowEntity.getColor() > 0 ? TIPPED_TEXTURE : TEXTURE;
    }
}