package org.cataclysm.datagen.data;

import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import org.cataclysm.Cataclysm;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class JukeboxSongProvider implements DataProvider {
    private final DataOutput.PathResolver pathResolver;

    public JukeboxSongProvider(FabricDataOutput output) {
        this.pathResolver = output.getResolver(DataOutput.OutputType.DATA_PACK, "jukebox_song");
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        return CompletableFuture.allOf(
                add(writer, "nuclear_fusion_redux", "music.nuclear_fusion_redux", 221, 15),
                add(writer, "grandma_destruction", "music.grandma_destruction", 361, 15),
                add(writer, "piercing_the_abyss", "music.piercing_the_abyss", 453, 15),
                add(writer, "ajoura", "music.ajoura", 409, 15),
                add(writer, "thats_life", "music.thats_life", 200, 15)
        );
    }

    private CompletableFuture<?> add(DataWriter writer, String name, String soundId, int lengthInSeconds, int comparatorOutput) {
        Path path = this.pathResolver.resolveJson(Cataclysm.identifier(name));

        JsonObject root = new JsonObject();
        root.addProperty("comparator_output", comparatorOutput);

        JsonObject description = new JsonObject();
        description.addProperty("translate", "item.cataclysm.music_disc_" + name + ".desc");
        root.add("description", description);

        root.addProperty("length_in_seconds", lengthInSeconds);

        JsonObject soundEvent = new JsonObject();
        soundEvent.addProperty("sound_id", Cataclysm.identifier(soundId).toString());
        root.add("sound_event", soundEvent);

        return DataProvider.writeToPath(writer, root, path);
    }

    @Override
    public String getName() {
        return "Jukebox Songs";
    }
}