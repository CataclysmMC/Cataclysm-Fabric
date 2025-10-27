package org.cataclysm.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import org.cataclysm.client.render.entity.ArcaneArrowEntityRenderer;
import org.cataclysm.registry.entity.CataclysmEntityTypes;

public class CataclysmClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(
                CataclysmEntityTypes.ARCANE_ARROW,
                ArcaneArrowEntityRenderer::new
        );
    }
}