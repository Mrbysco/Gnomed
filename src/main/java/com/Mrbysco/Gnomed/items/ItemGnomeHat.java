package com.mrbysco.gnomed.items;

import com.mrbysco.gnomed.init.GnomeTab;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.item.Item.Properties;

public class ItemGnomeHat extends Item
{
	public ItemGnomeHat(Properties properties)
	{
		super(properties.tab(GnomeTab.GNOME_TAB));
	}

	@Override
	public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
		return EquipmentSlotType.HEAD;
	}

}
