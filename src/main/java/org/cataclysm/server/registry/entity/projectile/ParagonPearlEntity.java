package org.cataclysm.server.registry.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.cataclysm.server.registry.item.custom.misc.paragon.ParagonPearlItem;
import org.jetbrains.annotations.NotNull;

public class ParagonPearlEntity extends EnderPearlEntity {
    private final ParagonPearlItem item;

    public ParagonPearlEntity(ParagonPearlItem item, World world, LivingEntity owner) {
        super(world, owner);
        this.item = item;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        if (!(super.getWorld() instanceof ServerWorld serverWorld)) return;

        Entity owner = super.getOwner();
        if (owner == null || !this.canTeleportEntityTo(owner, serverWorld)) {
            super.discard();
            return;
        }

        if (owner instanceof ServerPlayerEntity player && player.networkHandler.isConnectionOpen()) {
            if (this.random.nextFloat() < 0.05f && serverWorld.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
                EndermiteEntity endermite = EntityType.ENDERMITE.create(serverWorld);
                if (endermite != null) {
                    endermite.refreshPositionAndAngles(player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch());
                    serverWorld.spawnEntity(endermite);
                }
            }

            if (super.hasPortalCooldown()) owner.resetPortalCooldown();

            player.teleportTo(new TeleportTarget(serverWorld, super.getPos(), player.getVelocity(), player.getYaw(), player.getPitch(), TeleportTarget.NO_OP));
            player.onLanding();

            this.item.applyImmunity(player.getServerWorld(), player);
            this.spawnPearlParticles(serverWorld, player.capeX, player.capeY, player.capeZ);
            this.playTeleportSound(serverWorld, hitResult.getPos());
        }

        super.discard();
    }

    private void spawnPearlParticles(ServerWorld world, double x, double y, double z) {
        for (int i = 0; i < 32; ++i) {
            world.spawnParticles(
                    ParticleTypes.PORTAL,
                    x, y + world.random.nextDouble() * 2.0, z,
                    1,
                    world.random.nextGaussian(), 0.0, world.random.nextGaussian(),
                    1.0
            );
        }
    }

    private void playTeleportSound(@NotNull World world, @NotNull Vec3d pos) {
        world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS, 0.5f, 0.9f);
        world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.PLAYERS, 0.5f, 1.5f);
    }

    private boolean canTeleportEntityTo(@NotNull Entity entity, @NotNull World world) {
        if (entity.getWorld().getRegistryKey() == world.getRegistryKey()) {
            if (entity instanceof LivingEntity livingEntity) {
                return livingEntity.isAlive() && !livingEntity.isSleeping();
            }
            return entity.isAlive();
        }
        return entity.canUsePortals(true);
    }
}
