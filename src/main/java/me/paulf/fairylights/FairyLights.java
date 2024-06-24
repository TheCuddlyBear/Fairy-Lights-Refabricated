package me.paulf.fairylights;

import me.paulf.fairylights.server.block.FLBlocks;
import me.paulf.fairylights.server.block.entity.FLBlockEntities;
import me.paulf.fairylights.server.connection.ConnectionTypes;
import me.paulf.fairylights.server.creativetabs.FairyLightsItemGroup;
import me.paulf.fairylights.server.entity.FLEntities;
import me.paulf.fairylights.server.item.FLItems;
import me.paulf.fairylights.server.item.crafting.FLCraftingRecipes;
import me.paulf.fairylights.server.sound.FLSounds;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public class FairyLights implements ModInitializer {
    public static final String ID = "fairylights";
    public static final ResourceLocation STRING_TYPE = new ResourceLocation(ID, "string_type");
    public static final ResourceLocation CONNECTION_TYPE = new ResourceLocation(ID, "connection_type");

    @Override
    public void onInitialize() {

        // Register registries
        FLBlocks.REG.register();
        FLSounds.REG.register();
        FLEntities.REG.register();
        FairyLightsItemGroup.TAB_REG.register();
        FLBlockEntities.REG.register();
        FLItems.REG.register();
        ConnectionTypes.REG.register();
        FLCraftingRecipes.REG.register();
    }
}
