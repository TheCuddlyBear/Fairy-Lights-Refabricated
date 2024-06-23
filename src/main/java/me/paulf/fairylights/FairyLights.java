package me.paulf.fairylights;

import me.paulf.fairylights.server.block.FLBlocks;
import me.paulf.fairylights.server.block.entity.FLBlockEntities;
import me.paulf.fairylights.server.item.FLItems;
import me.paulf.fairylights.server.item.crafting.FLCraftingRecipes;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

public class FairyLights implements ModInitializer {
    public static final String ID = "fairylights";
    public static final ResourceLocation STRING_TYPE = new ResourceLocation(ID, "string_type");


    @Override
    public void onInitialize() {

        // Register registries
        FLBlocks.REG.register();
        FLBlockEntities.REG.register();
        FLItems.REG.register();
        FLCraftingRecipes.REG.register();
    }
}
