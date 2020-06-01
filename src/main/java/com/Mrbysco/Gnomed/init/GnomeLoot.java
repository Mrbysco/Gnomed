package com.Mrbysco.Gnomed.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class GnomeLoot {
	public static final ResourceLocation GNOME_LOOT = new ResourceLocation("memeinabottle:entity/gnome");

	public static void registerLootTables()
	{
		LootTableList.register(GNOME_LOOT);
	}
}
