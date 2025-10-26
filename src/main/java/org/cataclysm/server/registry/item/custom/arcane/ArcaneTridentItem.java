package org.cataclysm.server.registry.item.custom.arcane;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ArcaneTridentItem extends TridentItem {
    public static final int MIN_DRAW_DURATION = 10;
    public static final float ATTACK_DAMAGE = 8.0F;
    public static final float THROW_SPEED = 2.5F;

    // Riptide / dash tuning mirrors vanilla behavior
    private static final int RIPTIDE_TICKS = 20;
    private static final float RIPTIDE_SPEED = 8.0F;
    private static final float GROUND_LAUNCH_Y = 1.2F;

    public ArcaneTridentItem() {
        super(new Item.Settings()
                .component(DataComponentTypes.TOOL, createToolComponent())
                .attributeModifiers(createAttributeModifiers())
                .rarity(Rarity.EPIC)
                .maxDamage(250)
                .maxCount(1)
        );
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, ATTACK_DAMAGE, Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.9F, Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2);
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72_000;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return;

        final int chargedTicks = getMaxUseTime(stack, user) - remainingUseTicks;
        if (!isChargeReady(chargedTicks)) return;

        final float riptideStrength = EnchantmentHelper.getTridentSpinAttackStrength(stack, player);

        // You cannot Riptide on land; mirror vanilla guard
        if (riptideStrength > 0.0F && !player.isTouchingWaterOrRain()) return;

        // About to break? Abort entirely.
        if (isAboutToBreak(stack)) return;

        final RegistryEntry<SoundEvent> throwOrRiptideSound = getThrowSound(stack);

        if (!world.isClient) {
            stack.damage(1, player, LivingEntity.getSlotForHand(user.getActiveHand()));

            if (riptideStrength == 0.0F) {
                // Normal throw path
                throwTrident(world, player, stack, throwOrRiptideSound);
            }
        }

        // Stat increment happens client/server
        player.incrementStat(Stats.USED.getOrCreateStat(this));

        // Riptide dash path (applies on both sides for motion/sound parity)
        if (riptideStrength > 0.0F) {
            performRiptideDash(world, player, stack, riptideStrength, throwOrRiptideSound);
        }
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // If it's about to break, fail immediately.
        if (isAboutToBreak(stack)) return TypedActionResult.fail(stack);

        // Riptide on land is disallowed (must be in water or rain)
        if (EnchantmentHelper.getTridentSpinAttackStrength(stack, user) > 0.0F && !user.isTouchingWaterOrRain()) {
            return TypedActionResult.fail(stack);
        }

        user.setCurrentHand(hand);
        return TypedActionResult.consume(stack);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Return true to keep vanilla post-hit semantics; damage is applied in postDamageEntity
        return true;
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        TridentEntity trident = new TridentEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1));
        trident.pickupType = PickupPermission.ALLOWED;
        return trident;
    }

    // -----------------------------
    // Helpers
    // -----------------------------

    private static boolean isChargeReady(int chargedTicks) {
        return chargedTicks >= MIN_DRAW_DURATION;
    }

    private static boolean isAboutToBreak(ItemStack stack) {
        return stack.getDamage() >= stack.getMaxDamage() - 1;
    }

    private static RegistryEntry<SoundEvent> getThrowSound(ItemStack stack) {
        return EnchantmentHelper
                .getEffect(stack, EnchantmentEffectComponentTypes.TRIDENT_SOUND)
                .orElse(SoundEvents.ITEM_TRIDENT_THROW);
    }

    private static void throwTrident(World world, PlayerEntity player, ItemStack stack, RegistryEntry<SoundEvent> sound) {
        TridentEntity trident = new TridentEntity(world, player, stack);
        trident.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, THROW_SPEED, 1.0F);

        if (player.isInCreativeMode()) {
            trident.pickupType = PickupPermission.CREATIVE_ONLY;
        }

        world.spawnEntity(trident);
        world.playSoundFromEntity(null, trident, sound.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);

        if (!player.isInCreativeMode()) {
            player.getInventory().removeOne(stack);
        }
    }

    private static void performRiptideDash(
            World world,
            PlayerEntity player,
            ItemStack stack,
            float riptideStrength,
            RegistryEntry<SoundEvent> sound
    ) {
        // Compute facing vector from yaw/pitch (vanilla math kept for parity)
        float yaw = player.getYaw();
        float pitch = player.getPitch();

        float x = -MathHelper.sin(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
        float y = -MathHelper.sin(pitch * ((float)Math.PI / 180F));
        float z =  MathHelper.cos(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));

        Vec3d dir = new Vec3d(x, y, z).normalize().multiply(riptideStrength);

        player.addVelocity(dir.x, dir.y, dir.z);
        player.useRiptide(RIPTIDE_TICKS, RIPTIDE_SPEED, stack);

        // Nudge upwards if starting on the ground (matches vanilla)
        if (player.isOnGround()) {
            player.move(MovementType.SELF, new Vec3d(0.0, GROUND_LAUNCH_Y, 0.0));
        }

        world.playSoundFromEntity(null, player, sound.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
}