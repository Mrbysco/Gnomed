package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.Reference;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GnomeTab{

	public static final CreativeTabs GNOME_TAB = new CreativeTabs(Reference.MOD_ID)
	{
		@SideOnly(Side.CLIENT)
		public ItemStack createIcon()
		{
			return new ItemStack(GnomeItems.gnome_hat);
		}

		@Override
		public void displayAllRelevantItems(NonNullList<ItemStack> list) {
			super.displayAllRelevantItems(list);

			list.add(getEgg(Reference.MOD_PREFIX + "gnome"));
		}

		public ItemStack getEgg(String entityName)
		{
			ItemStack stack = new ItemStack(Items.SPAWN_EGG);
			NBTTagCompound entityTag = new NBTTagCompound();
			entityTag.setString("id", entityName);
			NBTTagCompound eggTag = new NBTTagCompound();
			eggTag.setTag("EntityTag", entityTag);
			stack.setTagCompound(eggTag);
			return stack;
		}
	};
}
