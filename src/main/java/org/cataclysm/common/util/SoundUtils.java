package org.cataclysm.common.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

import java.util.Random;

public class SoundUtils {
    public static void playSound(World world, LivingEntity user, SoundEvent sound, float volume, float minPitch, float maxPitch) {
        float pitch = new Random().nextFloat(minPitch, maxPitch);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), sound, SoundCategory.PLAYERS, volume, pitch);
    }
}
