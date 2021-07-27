package com.mrbysco.gnomed.items;

import com.mrbysco.gnomed.init.GnomeTab;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Wearable;

import javax.annotation.Nullable;

public class ItemGnomeHat extends Item implements Wearable {
	public ItemGnomeHat(Properties properties) {
		super(properties.tab(GnomeTab.GNOME_TAB));
	}

	@Override
	public EquipmentSlot getEquipmentSlot(ItemStack stack) {
		System.out.println("hey");
		return EquipmentSlot.HEAD;
	}

	@Nullable
	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ARMOR_EQUIP_LEATHER;
	}
}
