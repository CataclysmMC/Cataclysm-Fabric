package org.cataclysm.server.listener;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import org.cataclysm.server.registry.effect.CataclysmEffects;

import java.util.List;

public class TickListener {
    public static void registerEvents() {
        ServerTickEvents.START_SERVER_TICK.register(TickListener::onTick);
    }

    private static void onTick(MinecraftServer server) {
        server.getPlayerManager().getPlayerList().forEach(TickListener::processCleansingEffect);
    }

    private static void processCleansingEffect(PlayerEntity player) {
        if (!player.hasStatusEffect(CataclysmEffects.CLEANSING)) return;

        List<RegistryEntry<StatusEffect>> harmfulEffects = player.getActiveStatusEffects().keySet().stream()
                .filter(effect -> effect.value().getCategory() == StatusEffectCategory.HARMFUL)
                .toList();

        harmfulEffects.forEach(player::removeStatusEffect);
    }
}