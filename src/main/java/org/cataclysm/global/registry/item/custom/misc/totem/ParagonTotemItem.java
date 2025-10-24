package org.cataclysm.global.registry.item.custom.misc.totem;

import org.cataclysm.global.registry.item.custom.misc.paragon.ParagonItem;

public class ParagonTotemItem extends ParagonItem implements CataclysmTotemItem {
    public ParagonTotemItem() {
        super(1);
    }

    @Override
    public int getImmunityTicks() {
        return ParagonItem.getImmunityConditionalTicks() * 2;
    }
}