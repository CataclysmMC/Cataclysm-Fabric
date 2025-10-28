package org.cataclysm.registry.item.custom.calamity;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.cataclysm.registry.item.material.CataclysmArmorMaterial;

public class CalamityArmorItem extends ArmorItem {
    private static final int EFFECT_DURATION = 220;

    public CalamityArmorItem(Type type) {
        super(CataclysmArmorMaterial.CALAMITY, type, new Settings()
                .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))
                .rarity(Rarity.EPIC)
        );
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (world.isClient() || !(entity instanceof PlayerEntity player)) return;

        if (isWearingFullSetOf(player, CataclysmArmorMaterial.CALAMITY)) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, EFFECT_DURATION, 0, true, false, true));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,   EFFECT_DURATION, 0, true, false, true));
        }
    }

    private static boolean isWearingFullSetOf(PlayerEntity player, RegistryEntry<ArmorMaterial> material) {
        return matches(player.getEquippedStack(EquipmentSlot.HEAD),  material)
                && matches(player.getEquippedStack(EquipmentSlot.CHEST), material)
                && matches(player.getEquippedStack(EquipmentSlot.LEGS),  material)
                && matches(player.getEquippedStack(EquipmentSlot.FEET),  material);
    }

    private static boolean matches(ItemStack stack, RegistryEntry<ArmorMaterial> material) {
        if (!(stack.getItem() instanceof ArmorItem armor)) return false;
        return armor.getMaterial().equals(material);
    }
}