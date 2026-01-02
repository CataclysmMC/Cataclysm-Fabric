package org.cataclysm.common.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;

public class EnchantmentUtils {

    public static int getEnchantmentLevel(ServerWorld world, ItemStack stack, RegistryKey<Enchantment> key) {
        var wrapper = world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        RegistryEntry<Enchantment> entry = wrapper.getOptional(key).orElse(null);
        if (entry == null) return 0;
        return EnchantmentHelper.getLevel(entry, stack);
    }
}