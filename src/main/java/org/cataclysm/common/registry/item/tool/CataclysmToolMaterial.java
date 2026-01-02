package org.cataclysm.common.registry.item.tool;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import org.cataclysm.common.registry.item.CataclysmItems;

import java.util.Objects;
import java.util.function.Supplier;

public enum CataclysmToolMaterial implements ToolMaterial {
    ARCANE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 12.0F, 5.0F, 22, () -> Ingredient.ofItems(CataclysmItems.ARCANE_INGOT)),
    TWISTED(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 12.0F, 5.0F, 22, () -> Ingredient.ofItems(CataclysmItems.TWISTED_INGOT)),

    ;

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    CataclysmToolMaterial(final TagKey<Block> inverseTag, final int itemDurability, final float miningSpeed, final float attackDamage, final int enchantability, final Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        Objects.requireNonNull(repairIngredient);
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
