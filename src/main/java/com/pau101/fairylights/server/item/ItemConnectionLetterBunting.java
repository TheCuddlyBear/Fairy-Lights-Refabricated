package com.pau101.fairylights.server.item;

import com.pau101.fairylights.FairyLights;
import com.pau101.fairylights.server.fastener.connection.ConnectionType;
import com.pau101.fairylights.util.styledstring.StyledString;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

import java.util.List;

public class ItemConnectionLetterBunting extends ItemConnection {
	public ItemConnectionLetterBunting() {
		setCreativeTab(FairyLights.fairyLightsTab);
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		if (!stack.hasTagCompound()) {
			return;
		}
		NBTTagCompound compound = stack.getTagCompound();
		if (compound.hasKey("text", NBT.TAG_COMPOUND)) {
			NBTTagCompound text = compound.getCompoundTag("text");
			String val = text.getString("value");
			if (val.length() > 0) {
				tooltip.add(I18n.translateToLocalFormatted("format.text", val));
			}
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		ItemStack bunting = new ItemStack(this, 1);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setTag("text", StyledString.serialize(new StyledString()));
		bunting.setTagCompound(compound);
		items.add(bunting);
	}

	@Override
	public ConnectionType getConnectionType() {
		return ConnectionType.LETTER_BUNTING;
	}
}
