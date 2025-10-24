package org.cataclysm.server.registry.item.custom.util.totem;

import org.cataclysm.server.registry.item.custom.util.paragon.ParagonItem;

public class ParagonTotemItem extends ParagonItem implements CataclysmTotemItem {
    public ParagonTotemItem() {
        super(1);
    }

    @Override
    public int getImmunityTicks() {
        return ParagonItem.getImmunityConditionalTicks() * 2;
    }
}