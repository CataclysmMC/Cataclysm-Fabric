package org.cataclysm.server.registry.item.custom.arcane;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.cataclysm.server.registry.item.custom.AbstractCataclysmTool;
import org.cataclysm.server.registry.item.CataclysmMaterials;
import org.jetbrains.annotations.NotNull;

public final class ArcaneSwordItem extends AbstractCataclysmTool {
    public ArcaneSwordItem() {
        super(CataclysmMaterials.ARCANE, Type.SWORD, new Settings().rarity(Rarity.EPIC));
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, @NotNull PlayerEntity miner) {
        return !miner.isCreative();
    }
}