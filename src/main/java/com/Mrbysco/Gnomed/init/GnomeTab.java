package com.Mrbysco.Gnomed.init;

import com.Mrbysco.Gnomed.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public class GnomeTab extends CreativeTabs{

	public GnomeTab() 
	{
		super(Reference.MOD_ID);
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(GnomeItems.gnome_hat);
	}
	
	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> list) {
		super.displayAllRelevantItems(list);
		
		list.add(getEgg(Reference.MOD_PREFIX + "gnome"));
	}
	
	public static ItemStack getEgg(String entityName)
    {
        ItemStack stack = new ItemStack(Items.SPAWN_EGG);
        NBTTagCompound entityTag = new NBTTagCompound();
        entityTag.setString("id", entityName);
        NBTTagCompound eggTag = new NBTTagCompound();
        eggTag.setTag("EntityTag", entityTag);
        stack.setTagCompound(eggTag);
        return stack;
    }
}
