package org.cataclysm.registry.item.custom.twisted;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryOps;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.cataclysm.registry.item.material.CataclysmToolMaterial;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public abstract class AbstractTwistedItem extends ToolItem {
    public static final ToolMaterial TOOL_MATERIAL = CataclysmToolMaterial.TWISTED;

    public AbstractTwistedItem(Settings settings) {
        super(TOOL_MATERIAL, settings
                .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))
                .rarity(Rarity.EPIC)
                .maxCount(1)
        );
    }

    @Override
    public TypedActionResult<ItemStack> use(@NotNull World world, @NotNull PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (world.isClient() || !user.isSneaking()) {
            return TypedActionResult.pass(stack);
        }

        NbtComponent component = stack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT);
        NbtCompound rootCompound = component.copyNbt();
        NbtCompound twistedCompound = rootCompound.contains("twisted", NbtElement.COMPOUND_TYPE) ? rootCompound.getCompound("twisted") : new NbtCompound();

        boolean alt = twistedCompound.getBoolean("alt");
        ItemEnchantmentsComponent currentEnchants = stack.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
        RegistryOps<NbtElement> opsRegistry = world.getRegistryManager().getOps(NbtOps.INSTANCE);

        twistedCompound.put(alt ? "enchB" : "enchA",
                ItemEnchantmentsComponent.CODEC.encodeStart(opsRegistry, currentEnchants).result().orElse(new NbtCompound()));

        ItemEnchantmentsComponent nextEnchants = ItemEnchantmentsComponent.DEFAULT;
        String loadKey = alt ? "enchA" : "enchB";
        if (twistedCompound.contains(loadKey, NbtElement.COMPOUND_TYPE)) {
            nextEnchants = ItemEnchantmentsComponent.CODEC.parse(opsRegistry, twistedCompound.get(loadKey)).result().orElse(ItemEnchantmentsComponent.DEFAULT);
        }

        stack.set(DataComponentTypes.ENCHANTMENTS, nextEnchants);
        twistedCompound.putBoolean("alt", !alt);
        rootCompound.put("twisted", twistedCompound);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(rootCompound));

        user.getItemCooldownManager().set(this, 5);

        float pitch = new Random().nextFloat(1.8F, 2.0F);
        world.playSound(null, user.capeX, user.capeY, user.capeZ, SoundEvents.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 0.75F, pitch);
        world.playSound(null, user.capeX, user.capeY, user.capeZ, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.PLAYERS, 0.75F, pitch);

        return TypedActionResult.consume(stack);
    }
}