package org.cataclysm.server.blocks.paragon;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ParagonCore extends Block {
    public ParagonCore(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends Block> getCodec() {
        return null;
    }

    @Override
    public Item asItem() {
        return null;
    }

    @Override
    protected Block asBlock() {
        return null;
    }
}
