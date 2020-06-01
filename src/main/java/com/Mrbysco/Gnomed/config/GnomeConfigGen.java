package com.Mrbysco.Gnomed.config;

import com.Mrbysco.Gnomed.Reference;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID)
@Config.LangKey("memeinabottle.config.title")
public class GnomeConfigGen 
{
	@Config.Comment({"Spawn settings"})
	@Config.Name("Spawn Settings")
	public static Spawning spawning = new Spawning();
	
	public static class Spawning
	{
		@Config.Comment({"Gnome settings"})
		@Config.Name("Gnome settings")
		public Gnome gnome = new Gnome();
		public class Gnome {
			@Config.Comment("Setting this to true lets Gnome spawn in the world (Default: false)")
			public boolean GnomeSpawning = false;
			@Config.Comment("Sets the weigth, higher means it has a bigger spawns chance (Default: 12)")
			@Config.RangeInt(min = 1)
			public int GnomeWeigth = 12;
		}
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