package org.cataclysm.common.registry.item.armor;

import net.minecraft.item.ArmorItem;

public final class PaleArmorItem extends ArmorItem {
    public PaleArmorItem(Type type, Settings settings) {
        super(CataclysmArmorMaterial.PALE.getEntry(), type, settings);
    }
}