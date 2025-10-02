package org.cataclysm;

import net.fabricmc.api.ModInitializer;
import org.cataclysm.server.CataclysmCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cataclysm implements ModInitializer {
    public static final String MOD_ID = "cataclysm";
    public static final Logger LOGGER = LoggerFactory.getLogger("Cataclysm");

    private static Cataclysm instance;
    private static CataclysmCore core;

    @Override
    public void onInitialize() {
        instance = this;
        core = new CataclysmCore();
        core.init();;
    }

    public static Cataclysm getInstance() {
        return instance;
    }
}