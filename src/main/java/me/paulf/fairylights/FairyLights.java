package me.paulf.fairylights;

import me.paulf.fairylights.server.block.FLBlocks;
import me.paulf.fairylights.server.block.entity.FLBlockEntities;
import net.fabricmc.api.ModInitializer;

public class FairyLights implements ModInitializer {
    public static final String ID = "fairylights";

    @Override
    public void onInitialize() {

        // Register registries
        FLBlocks.REG.register();
        FLBlockEntities.REG.register();
    }
}
