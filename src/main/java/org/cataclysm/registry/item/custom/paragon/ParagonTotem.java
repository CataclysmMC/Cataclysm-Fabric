package org.cataclysm.registry.item.custom.paragon;

import org.cataclysm.registry.item.custom.misc.CataclysmTotem;

public final class ParagonTotem extends AbstractParagonItem implements CataclysmTotem {
    public ParagonTotem() {
        super(1);
    }

    @Override
    public int getImmunityTicks() {
        return AbstractParagonItem.getImmunityConditionalTicks() * 2;
    }
}