package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.Reference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class GnomeTab {
	public static final CreativeModeTab GNOME_TAB = new CreativeModeTab(Reference.MOD_ID) {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(GnomeRegistry.GNOME_HAT.get());
		}
	};
}
