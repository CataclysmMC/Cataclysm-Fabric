package org.cataclysm.server.registry.item.custom.arcane;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.Random;

public interface ArcaneItem {
    static Item.Settings getSettings() {
        return new Item.Settings()
                .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))
                .rarity(Rarity.EPIC)
                .maxCount(1);
    }

    default void playSound(World world, LivingEntity user, SoundEvent sound, float volume, float minPitch, float maxPitch) {
        float pitch = new Random().nextFloat(minPitch, maxPitch);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), sound, SoundCategory.PLAYERS, volume, pitch);
    }

    default int getEnchantmentLevel(ServerWorld world, ItemStack stack, RegistryKey<Enchantment> key) {
        var wrapper = world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        RegistryEntry<Enchantment> entry = wrapper.getOptional(key).orElse(null);
        if (entry == null) return 0;
        return EnchantmentHelper.getLevel(entry, stack);
    }
}
