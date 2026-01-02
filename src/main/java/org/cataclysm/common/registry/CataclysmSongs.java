package org.cataclysm.common.registry;

import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.cataclysm.Cataclysm;

public final class CataclysmSongs {
    public static final RegistryKey<JukeboxSong> NUCLEAR_FUSION_REDUX = of("nuclear_fusion_redux");
    public static final RegistryKey<JukeboxSong> GRANDMA_DESTRUCTION = of("grandma_destruction");
    public static final RegistryKey<JukeboxSong> PIERCING_THE_ABYSS = of("piercing_the_abyss");
    public static final RegistryKey<JukeboxSong> AJOURA = of("ajoura");
    public static final RegistryKey<JukeboxSong> THATS_LIFE = of("thats_life");

    private static RegistryKey<JukeboxSong> of(String id) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Cataclysm.identifier(id));
    }
}