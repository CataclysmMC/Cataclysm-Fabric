package org.cataclysm.common.registry.item.armor;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.cataclysm.Cataclysm;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public enum CataclysmArmorMaterial {
    CALAMITY(4, 7, 9, 4, 12, 30, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.ofItems(Items.NETHERITE_INGOT)),
    PALE(4, 7, 9, 4, 12, 30, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> Ingredient.ofItems(Items.NETHERITE_INGOT));

    private final RegistryEntry<ArmorMaterial> entry;

    CataclysmArmorMaterial(int boots, int leggings, int chestplate, int helmet, int body, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        EnumMap<ArmorItem.Type, Integer> defense = new EnumMap<>(ArmorItem.Type.class);
        defense.put(ArmorItem.Type.BOOTS, boots);
        defense.put(ArmorItem.Type.LEGGINGS, leggings);
        defense.put(ArmorItem.Type.CHESTPLATE, chestplate);
        defense.put(ArmorItem.Type.HELMET, helmet);
        defense.put(ArmorItem.Type.BODY, body);

        EnumMap<ArmorItem.Type, Integer> complete = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            complete.put(type, defense.getOrDefault(type, 0));
        }

        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(Cataclysm.identifier(name().toLowerCase())));
        ArmorMaterial material = new ArmorMaterial(complete, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance);

        this.entry = Registry.registerReference(Registries.ARMOR_MATERIAL, Cataclysm.identifier(name().toLowerCase()), material);
    }

    public RegistryEntry<ArmorMaterial> getEntry() {
        return this.entry;
    }
}