package com.mrbysco.gnomed.config;

import com.mrbysco.gnomed.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID, name = "Gnomed", category = "")
@Config.LangKey("gnomed.config.title")
public class GnomeConfig
{
	@Config.Comment({"Gnome settings"})
	@Config.Name("Gnome settings")
	public static Gnome gnome = new Gnome();
	public static class Gnome {
		@Config.Comment("Setting this to true lets Gnome spawn in the world (Default: false)")
		public boolean GnomeSpawning = false;

		@Config.Comment("Sets the weigth, higher means it has a bigger spawns chance (Default: 12)")
		@Config.RangeInt(min = 1)
		public int GnomeWeigth = 12;

		@Config.Comment("Adding lines / removing lines specifies what biomes the gnome spawns in")
		public String[] GnomeBiomes = new String[]
		{
			"minecraft:forest",
			"minecraft:taiga",
			"minecraft:forest_hills",
			"minecraft:taiga_hills",
			"minecraft:jungle_edge",
			"minecraft:birch_forest",
			"minecraft:birch_forest_hills",
			"minecraft:roofed_forest",
			"minecraft:taiga_cold",
			"minecraft:taiga_cold_hills",
			"minecraft:redwood_taiga",
			"minecraft:redwood_taiga_hills",
			"minecraft:extreme_hills_with_trees",
			"minecraft:mutated_forest",
			"minecraft:mutated_taiga",
			"minecraft:mutated_birch_forest",
			"minecraft:mutated_birch_forest_hills",
			"minecraft:mutated_roofed_forest",
			"minecraft:mutated_taiga_cold",
			"minecraft:mutated_redwood_taiga",
			"minecraft:mutated_redwood_taiga_hills"
		};
	}

	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	private static class EventHandler 
	{
		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) 
		{
			if (event.getModID().equals(Reference.MOD_ID)) 
			{
				ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}