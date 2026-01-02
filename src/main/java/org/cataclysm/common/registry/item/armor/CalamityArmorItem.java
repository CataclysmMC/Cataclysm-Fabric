package org.cataclysm.common.registry.item.armor;

import net.minecraft.item.ArmorItem;

public final class CalamityArmorItem extends ArmorItem {
    public CalamityArmorItem(Type type, Settings settings) {
        super(CataclysmArmorMaterial.CALAMITY.getEntry(), type, settings);
    }
}