package com.mrbysco.gnomed.items;

import com.mrbysco.gnomed.Gnomed;
import com.mrbysco.gnomed.Reference;

import com.mrbysco.gnomed.init.GnomeTab;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGnomeHat extends Item
{
	public ItemGnomeHat(String registry) 
	{
		this.setTranslationKey(Reference.MOD_PREFIX + registry.replaceAll("_", ""));
		this.setRegistryName(registry);
		setCreativeTab(GnomeTab.GNOME_TAB);
	}
	
	@Override
	public EntityEquipmentSlot getEquipmentSlot(ItemStack stack) {
		return EntityEquipmentSlot.HEAD;
	}
}
