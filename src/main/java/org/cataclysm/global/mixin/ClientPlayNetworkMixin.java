package org.cataclysm.global.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.sound.GuardianAttackSoundInstance;
import net.minecraft.client.sound.SnifferDigSoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import org.cataclysm.server.registry.item.custom.misc.CataclysmTotem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkMixin {

    @Inject(method = "onEntityStatus(Lnet/minecraft/network/packet/s2c/play/EntityStatusS2CPacket;)V", at = @At("HEAD"), cancellable = true)
    private void onEntityStatus(EntityStatusS2CPacket packet, CallbackInfo ci) {
        final MinecraftClient client = MinecraftClient.getInstance();
        NetworkThreadUtils.forceMainThread(packet, (ClientPlayPacketListener) this, client);

        final ClientWorld world = client.world;
        if (world == null) return;

        final Entity entity = packet.getEntity(world);
        if (entity == null) return;

        final int status = packet.getStatus() & 0xFF;

        switch (status) {
            case 21: // Guardian attack
                if (entity instanceof GuardianEntity guardian) {
                    client.getSoundManager().play(new GuardianAttackSoundInstance(guardian));
                }
                break;

            case 35: // Totem pop
                world.addParticle(ParticleTypes.TOTEM_OF_UNDYING, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
                client.particleManager.addEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);
                world.playSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0F, 1.0F, false);

                if (entity == client.player) {
                    LivingEntity livingEntity = (LivingEntity) entity;
                    client.gameRenderer.showFloatingItem(CataclysmTotem.getActiveTotemStack(livingEntity));
                }
                break;

            case 63: // Sniffer dig
                if (entity instanceof SnifferEntity sniffer) {
                    client.getSoundManager().play(new SnifferDigSoundInstance(sniffer));
                }
                break;

            default:
                entity.handleStatus((byte) status);
        }

        ci.cancel();
    }
}
