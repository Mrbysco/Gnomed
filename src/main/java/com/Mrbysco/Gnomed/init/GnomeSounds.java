package com.Mrbysco.Gnomed.init;

import com.Mrbysco.Gnomed.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class GnomeSounds {
	public static SoundEvent gnome_spawn;
	public static SoundEvent gnome_passive;
	public static SoundEvent gnome_hurt;
	public static SoundEvent gnome_death;

	public static void registerSounds() 
	{
		gnome_spawn = registerSound("gnome.summon");
		gnome_passive = registerSound("gnome.passive");
		gnome_hurt = registerSound("gnome.hurt");
		gnome_death = registerSound("gnome.death");
	}
	
	private static SoundEvent registerSound(String soundName)
	{
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, soundName);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(location);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}