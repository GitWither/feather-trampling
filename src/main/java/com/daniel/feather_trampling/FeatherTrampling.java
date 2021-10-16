package com.daniel.feather_trampling;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FeatherTrampling implements ModInitializer {
    public static final String MOD_ID = "feather_trampling";

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        LOGGER.info("Initialized Feather Trampling!");
    }
}
