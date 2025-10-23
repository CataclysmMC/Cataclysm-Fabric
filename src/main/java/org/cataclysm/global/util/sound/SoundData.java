package org.cataclysm.global.util.sound;

import net.minecraft.sound.SoundEvent;

public record SoundData(SoundEvent soundEvent, float pitch) {
    public static SoundData of(SoundEvent soundEvent, float pitch) {
        return new SoundData(soundEvent, pitch);
    }
}
