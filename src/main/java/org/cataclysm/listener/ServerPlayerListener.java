package org.cataclysm.listener;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.cataclysm.registry.effect.CataclysmEffects;

import java.util.List;

public class ServerPlayerListener {
    public static void registerEvents() {
        onEndServerTick();
    }

    private static void onEndServerTick() {
        ServerTickEvents.START_SERVER_TICK.register(server ->
                server.getPlayerManager().getPlayerList().forEach(ServerPlayerListener::handleCleansingEffect));
    }

    private static void handleCleansingEffect(PlayerEntity player) {
        var effects = player.getActiveStatusEffects();

        if (!player.hasStatusEffect(CataclysmEffects.CLEANSING) || effects.isEmpty()) return;

        List<RegistryEntry<StatusEffect>> toRemove = effects.keySet().stream()
                .filter(entry -> entry.value().getCategory() == StatusEffectCategory.HARMFUL)
                .toList();

        if (toRemove.isEmpty()) return;

        toRemove.forEach(player::removeStatusEffect);

        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.NEUTRAL,
                0.75F, 2.0F);
    }
}
