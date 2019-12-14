package me.paulf.fairylights.util.crafting.ingredient;

import com.google.common.collect.ImmutableList;
import me.paulf.fairylights.util.crafting.GenericRecipe;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Collections;
import java.util.Objects;

public class BasicRegularIngredient implements RegularIngredient {
	protected final ItemStack ingredient;

	public BasicRegularIngredient(Item item) {
		this(new ItemStack(Objects.requireNonNull(item, "item")));
	}

	public BasicRegularIngredient(Block block) {
		this(new ItemStack(Objects.requireNonNull(block, "block")));
	}

	public BasicRegularIngredient(ItemStack stack) {
		ingredient = Objects.requireNonNull(stack, "stack");
		Objects.requireNonNull(stack.getItem(), "item");
	}

	@Override
	public final GenericRecipe.MatchResultRegular matches(ItemStack input, ItemStack output) {
		return new GenericRecipe.MatchResultRegular(this, input, ingredient.getItem() == input.getItem(), Collections.emptyList());
	}

	@Override
	public ImmutableList<ItemStack> getInputs() {
		return getMatchingSubtypes(ingredient);
	}

	@Override
	public String toString() {
		return ingredient.toString();
	}
}