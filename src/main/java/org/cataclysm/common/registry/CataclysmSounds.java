package org.cataclysm.common.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.cataclysm.Cataclysm;

public final class CataclysmSounds {
    private static final Registry<SoundEvent> REGISTRY = Registries.SOUND_EVENT;

    public static final RegistryEntry.Reference<SoundEvent> MUSIC_NUCLEAR_FUSION_REDUX = registerReference("music.nuclear_fusion_redux");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_GRANDMA_DESTRUCTION = registerReference("music.grandma_destruction");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_PIERCING_THE_ABYSS = registerReference("music.piercing_the_abyss");
    public static final RegistryEntry.Reference<SoundEvent> MUSIC_AJOURA = registerReference("music.ajoura");

    private CataclysmSounds() {}

    public static Registry<SoundEvent> initialize() {
        return REGISTRY;
    }

    private static SoundEvent register(String path) {
        Identifier identifier = Cataclysm.identifier(path);
        return Registry.register(REGISTRY, identifier, SoundEvent.of(identifier));
    }

    private static RegistryEntry.Reference<SoundEvent> registerReference(String path) {
        Identifier identifier = Cataclysm.identifier(path);
        return Registry.registerReference(REGISTRY, identifier, SoundEvent.of(identifier));
    }
}