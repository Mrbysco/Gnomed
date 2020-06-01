package com.Mrbysco.Gnomed.items;

import com.Mrbysco.Gnomed.Gnomed;
import com.Mrbysco.Gnomed.Reference;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGnomeHat extends Item
{
	public ItemGnomeHat(String registry) 
	{
		this.setUnlocalizedName(Reference.MOD_PREFIX + registry.replaceAll("_", ""));
		this.setRegistryName(registry);
		setCreativeTab(Gnomed.gnometab);
	}
	
	@Override
	public EntityEquipmentSlot getEquipmentSlot(ItemStack stack) {
		return EntityEquipmentSlot.HEAD;
	}
}
