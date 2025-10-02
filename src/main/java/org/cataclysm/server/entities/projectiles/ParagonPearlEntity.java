package org.cataclysm.server.entities.projectiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PositionFlag;
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
import org.cataclysm.server.item.CataclysmItems;
import org.cataclysm.server.item.paragon.ParagonItem;

public class ParagonPearlEntity extends EnderPearlEntity {
    private static final int IMMUNITY_TICKS = ParagonItem.IMMUNITY_BASE_TICKS / 2;

    public ParagonPearlEntity(World world, LivingEntity owner, ItemStack stack) {
        super(world, owner, stack);
    }

    @Override
    protected Item getDefaultItem() {
        return CataclysmItems.PARAGON_PEARL;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        World world = this.getWorld();
        if (!(world instanceof ServerWorld serverWorld)) return;

        Entity owner = this.getOwner();
        if (owner == null || !canTeleportEntityTo(owner, serverWorld)) {
            this.discard();
            return;
        }

        Vec3d targetPos = hitResult.getPos();

        // Partículas en el punto de impacto
        spawnPearlParticles(serverWorld, this.getX(), this.getY(), this.getZ());

        // Caso jugador
        if (owner instanceof ServerPlayerEntity player && player.networkHandler.isConnectionOpen()) {
            // Posible endermite
            if (this.random.nextFloat() < 0.05f &&
                    serverWorld.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
                EndermiteEntity endermite = EntityType.ENDERMITE.create(serverWorld, SpawnReason.TRIGGERED);
                if (endermite != null) {
                    endermite.refreshPositionAndAngles(player.getX(), player.getY(), player.getZ(),
                            player.getYaw(), player.getPitch());
                    serverWorld.spawnEntity(endermite);
                }
            }

            // Reset portal cooldown
            if (this.hasPortalCooldown()) owner.resetPortalCooldown();

            // Teletransportar
            ServerPlayerEntity teleported = player.teleportTo(new TeleportTarget(
                    serverWorld, targetPos, Vec3d.ZERO, 0.0f, 0.0f,
                    PositionFlag.combine(PositionFlag.ROT, PositionFlag.DELTA), TeleportTarget.NO_OP
            ));

            if (teleported != null) {
                teleported.onLanding();

                ItemStack stack = this.getStack();
                if (stack.getItem() instanceof ParagonItem paragonItem) {
                    paragonItem.applyImmunity(serverWorld, teleported, IMMUNITY_TICKS);
                }

                spawnPearlParticles(serverWorld, teleported.capeX, teleported.capeY, teleported.capeZ);
            }

            playTeleportSound(serverWorld, targetPos);
        }
        // Caso entidad no jugador
        else {
            Entity teleported = owner.teleportTo(new TeleportTarget(
                    serverWorld, targetPos, owner.getVelocity(),
                    owner.getYaw(), owner.getPitch(), TeleportTarget.NO_OP
            ));
            if (teleported != null) {
                teleported.onLanding();
                spawnPearlParticles(serverWorld, targetPos.x, targetPos.y, targetPos.z);
            }
            playTeleportSound(serverWorld, targetPos);
        }

        this.discard();
    }

    private void spawnPearlParticles(ServerWorld world, double x, double y, double z) {
        for (int i = 0; i < 32; ++i) {
            world.spawnParticles(ParticleTypes.PORTAL,
                    x, y + world.random.nextDouble() * 2.0, z,
                    1,
                    world.random.nextGaussian(), 0.0, world.random.nextGaussian(),
                    1.0
            );
        }
    }

    private void playTeleportSound(World world, Vec3d pos) {
        world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS, 0.5f, 0.9f);
        world.playSound(null, pos.x, pos.y, pos.z, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.PLAYERS, 0.5f, 1.5f);
    }

    private static boolean canTeleportEntityTo(Entity entity, World world) {
        if (entity.getWorld().getRegistryKey() == world.getRegistryKey()) {
            if (entity instanceof LivingEntity livingEntity) {
                return livingEntity.isAlive() && !livingEntity.isSleeping();
            }
            return entity.isAlive();
        }
        return entity.canUsePortals(true);
    }
}