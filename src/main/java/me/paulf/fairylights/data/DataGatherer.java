package me.paulf.fairylights.data;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import me.paulf.fairylights.FairyLights;
import me.paulf.fairylights.server.block.FLBlocks;
import me.paulf.fairylights.server.item.FLItems;
import me.paulf.fairylights.server.item.crafting.FLCraftingRecipes;
import me.paulf.fairylights.util.styledstring.StyledString;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.Tags;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = FairyLights.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGatherer {
    @SubscribeEvent
    public static void onGatherData(final GatherDataEvent event) {
        final DataGenerator gen = event.getGenerator();
        gen.addProvider(event.includeServer(), new RecipeGenerator(gen.getPackOutput()));
        gen.addProvider(event.includeServer(), new LootTableGenerator(gen));
    }

    static class RecipeGenerator extends RecipeProvider {
        RecipeGenerator(final PackOutput generator) {
            super(generator);
        }

        @Override
        protected void buildRecipes(final Consumer<FinishedRecipe> consumer) {
            final CompoundTag nbt = new CompoundTag();
            nbt.put("text", StyledString.serialize(new StyledString()));
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, FLItems.LETTER_BUNTING.get())
                .pattern("I-I")
                .pattern("PBF")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('-', Tags.Items.STRING)
                .define('P', Items.PAPER)
                .define('B', Items.INK_SAC)
                .define('F', Tags.Items.FEATHERS)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_string", has(Tags.Items.STRING))
                .save(addNbt(consumer, nbt));
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,FLItems.GARLAND.get(), 2)
                .pattern("I-I")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('-', Items.VINE)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_vine", has(Items.VINE))
                .save(consumer);
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,FLItems.OIL_LANTERN.get(), 4)
                .pattern(" I ")
                .pattern("STS")
                .pattern("IGI")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('S', Items.STICK)
                .define('T', Items.TORCH)
                .define('G', Tags.Items.GLASS_PANES_COLORLESS)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_torch", has(Items.TORCH))
                .save(consumer);
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,FLItems.CANDLE_LANTERN.get(), 4)
                .pattern(" I ")
                .pattern("GTG")
                .pattern("IGI")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('G', Tags.Items.NUGGETS_GOLD)
                .define('T', Items.TORCH)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_torch", has(Items.TORCH))
                .save(consumer);
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS,FLItems.INCANDESCENT_LIGHT.get(), 4)
                .pattern(" I ")
                .pattern("ITI")
                .pattern(" G ")
                .define('I', Tags.Items.INGOTS_IRON)
                .define('G', Tags.Items.GLASS_PANES_COLORLESS)
                .define('T', Items.TORCH)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_torch", has(Items.TORCH))
                .save(consumer);
            GenericRecipeBuilder.customRecipe(FLCraftingRecipes.HANGING_LIGHTS.get())
                .unlockedBy("has_lights", has(FLCraftingRecipes.LIGHTS))
                .build(consumer, new ResourceLocation(FairyLights.ID, "hanging_lights"));
            GenericRecipeBuilder.customRecipe(FLCraftingRecipes.HANGING_LIGHTS_AUGMENTATION.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "hanging_lights_augmentation"));
            GenericRecipeBuilder.customRecipe(FLCraftingRecipes.TINSEL_GARLAND.get())
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_string", has(Tags.Items.STRING))
                .build(consumer, new ResourceLocation(FairyLights.ID, "tinsel_garland"));
            GenericRecipeBuilder.customRecipe(FLCraftingRecipes.PENNANT_BUNTING.get())
                .unlockedBy("has_pennants", has(FLCraftingRecipes.PENNANTS))
                .build(consumer, new ResourceLocation(FairyLights.ID, "pennant_bunting"));
            GenericRecipeBuilder.customRecipe(FLCraftingRecipes.PENNANT_BUNTING_AUGMENTATION.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "pennant_bunting_augmentation"));
            GenericRecipeBuilder.customRecipe(FLCraftingRecipes.EDIT_COLOR.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "edit_color"));
            GenericRecipeBuilder.customRecipe(FLCraftingRecipes.COPY_COLOR.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "copy_color"));
            this.pennantRecipe(FLCraftingRecipes.TRIANGLE_PENNANT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "triangle_pennant"));
            this.pennantRecipe(FLCraftingRecipes.SPEARHEAD_PENNANT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "spearhead_pennant"));
            this.pennantRecipe(FLCraftingRecipes.SWALLOWTAIL_PENNANT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "swallowtail_pennant"));
            this.pennantRecipe(FLCraftingRecipes.SQUARE_PENNANT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "square_pennant"));
            this.lightRecipe(FLCraftingRecipes.FAIRY_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "fairy_light"));
            this.lightRecipe(FLCraftingRecipes.PAPER_LANTERN.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "paper_lantern"));
            this.lightRecipe(FLCraftingRecipes.ORB_LANTERN.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "orb_lantern"));
            this.lightRecipe(FLCraftingRecipes.FLOWER_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "flower_light"));
            this.lightRecipe(FLCraftingRecipes.CANDLE_LANTERN_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "candle_lantern_light"));
            this.lightRecipe(FLCraftingRecipes.OIL_LANTERN_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "oil_lantern_light"));
            this.lightRecipe(FLCraftingRecipes.JACK_O_LANTERN.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "jack_o_lantern"));
            this.lightRecipe(FLCraftingRecipes.SKULL_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "skull_light"));
            this.lightRecipe(FLCraftingRecipes.GHOST_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "ghost_light"));
            this.lightRecipe(FLCraftingRecipes.SPIDER_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "spider_light"));
            this.lightRecipe(FLCraftingRecipes.WITCH_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "witch_light"));
            this.lightRecipe(FLCraftingRecipes.SNOWFLAKE_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "snowflake_light"));
            this.lightRecipe(FLCraftingRecipes.HEART_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "heart_light"));
            this.lightRecipe(FLCraftingRecipes.MOON_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "moon_light"));
            this.lightRecipe(FLCraftingRecipes.STAR_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "star_light"));
            this.lightRecipe(FLCraftingRecipes.ICICLE_LIGHTS.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "icicle_lights"));
            this.lightRecipe(FLCraftingRecipes.METEOR_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "meteor_light"));
            GenericRecipeBuilder.customRecipe(FLCraftingRecipes.LIGHT_TWINKLE.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "light_twinkle"));
            GenericRecipeBuilder.customRecipe(FLCraftingRecipes.COLOR_CHANGING_LIGHT.get())
                .build(consumer, new ResourceLocation(FairyLights.ID, "color_changing_light"));
        }

        GenericRecipeBuilder lightRecipe(final RecipeSerializer<?> serializer) {
            return GenericRecipeBuilder.customRecipe(serializer)
                .unlockedBy("has_iron", has(Tags.Items.INGOTS_IRON))
                .unlockedBy("has_dye", has(Tags.Items.DYES));
        }

        GenericRecipeBuilder pennantRecipe(final RecipeSerializer<?> serializer) {
            return GenericRecipeBuilder.customRecipe(serializer)
                .unlockedBy("has_paper", has(Items.PAPER))
                .unlockedBy("has_string", has(Tags.Items.STRING));
        }
    }

    static class LootTableGenerator extends LootTableProvider {
        LootTableGenerator(final DataGenerator generator) {
            super(generator.getPackOutput(), Set.of(), VanillaLootTableProvider.create(generator.getPackOutput()).getTables());
        }

        @Override
        public List<SubProviderEntry> getTables()
        {
            return ImmutableList.of(new SubProviderEntry(BlockLootTableGenerator::new, LootContextParamSets.BLOCK));
        }

        @Override
        protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext tracker) {
            // For built-in mod loot tables
            /*for (final ResourceLocation name : Sets.difference(MyBuiltInLootTables.getAll(), map.defineSet())) {
                tracker.addProblem("Missing built-in table: " + name);
            }*/
            map.forEach((name, table) -> table.validate(tracker));
        }
    }

    static class BlockLootTableGenerator extends BlockLootSubProvider
    {
        protected BlockLootTableGenerator()
        {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return FLBlocks.REG.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
        }

        @Override
        protected void generate()
        {
            this.add(FLBlocks.FASTENER.get(), noDrop());
            this.add(FLBlocks.FAIRY_LIGHT.get(), noDrop());
            this.add(FLBlocks.PAPER_LANTERN.get(), noDrop());
            this.add(FLBlocks.ORB_LANTERN.get(), noDrop());
            this.add(FLBlocks.FLOWER_LIGHT.get(), noDrop());
            this.add(FLBlocks.CANDLE_LANTERN_LIGHT.get(), noDrop());
            this.add(FLBlocks.OIL_LANTERN_LIGHT.get(), noDrop());
            this.add(FLBlocks.JACK_O_LANTERN.get(), noDrop());
            this.add(FLBlocks.SKULL_LIGHT.get(), noDrop());
            this.add(FLBlocks.GHOST_LIGHT.get(), noDrop());
            this.add(FLBlocks.SPIDER_LIGHT.get(), noDrop());
            this.add(FLBlocks.WITCH_LIGHT.get(), noDrop());
            this.add(FLBlocks.SNOWFLAKE_LIGHT.get(), noDrop());
            this.add(FLBlocks.HEART_LIGHT.get(), noDrop());
            this.add(FLBlocks.MOON_LIGHT.get(), noDrop());
            this.add(FLBlocks.STAR_LIGHT.get(), noDrop());
            this.add(FLBlocks.ICICLE_LIGHTS.get(), noDrop());
            this.add(FLBlocks.METEOR_LIGHT.get(), noDrop());
            this.add(FLBlocks.OIL_LANTERN.get(), noDrop());
            this.add(FLBlocks.CANDLE_LANTERN.get(), noDrop());
            this.add(FLBlocks.INCANDESCENT_LIGHT.get(), noDrop());
        }
    }

    static Consumer<FinishedRecipe> addNbt(final Consumer<FinishedRecipe> consumer, final CompoundTag nbt) {
        return recipe -> consumer.accept(new ForwardingFinishedRecipe() {
            @Override
            protected FinishedRecipe delegate() {
                return recipe;
            }

            @Override
            public void serializeRecipeData(final JsonObject json) {
                super.serializeRecipeData(json);
                json.getAsJsonObject("result").addProperty("nbt", nbt.toString());
            }
        });
    }

    abstract static class ForwardingFinishedRecipe implements FinishedRecipe {
        protected abstract FinishedRecipe delegate();

        @Override
        public void serializeRecipeData(final JsonObject json) {
            this.delegate().serializeRecipeData(json);
        }

        @Override
        public ResourceLocation getId() {
            return this.delegate().getId();
        }

        @Override
        public RecipeSerializer<?> getType() {
            return this.delegate().getType();
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.delegate().serializeAdvancement();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return this.delegate().getAdvancementId();
        }
    }
}
