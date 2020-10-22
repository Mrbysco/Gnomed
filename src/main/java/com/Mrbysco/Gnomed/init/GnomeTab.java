package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.Reference;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GnomeTab{

	public static final ItemGroup GNOME_TAB = new ItemGroup(Reference.MOD_ID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(GnomeRegistry.GNOME_HAT.get());
		}
	};
}
