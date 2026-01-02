package org.cataclysm.server.listener;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import org.cataclysm.common.registry.effect.CataclysmEffects;

import java.util.List;

public class ServerTickListener {
    public static void registerEvents() {
        ServerTickEvents.START_SERVER_TICK.register(ServerTickListener::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        server.getPlayerManager().getPlayerList().forEach(player -> {
            if (player.hasStatusEffect(CataclysmEffects.CLEANSING)) {
                List<RegistryEntry<StatusEffect>> harmfulEffects = player.getActiveStatusEffects().keySet().stream()
                        .filter(effect -> effect.value().getCategory() == StatusEffectCategory.HARMFUL)
                        .toList();
                harmfulEffects.forEach(player::removeStatusEffect);
            }
        });
    }
}