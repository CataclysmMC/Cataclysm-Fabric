package org.cataclysm.server;

import org.cataclysm.server.blocks.CataclysmBlocks;
import org.cataclysm.server.effects.CataclysmEffects;
import org.cataclysm.server.item.CataclysmItemGroups;
import org.cataclysm.server.item.CataclysmItems;

public class CataclysmCore {

    public void init() {
        initEffects();
        initItems();
    }

    public void initEffects() {
        CataclysmEffects.init();
    }

    public void initItems() {
        CataclysmItems.init();
        CataclysmBlocks.init();
        CataclysmItemGroups.init();
    }
}
