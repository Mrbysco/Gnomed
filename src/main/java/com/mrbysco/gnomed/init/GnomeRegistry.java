package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.entities.Gnome;
import com.mrbysco.gnomed.items.GnomeHatItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GnomeRegistry {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Reference.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, Reference.MOD_ID);

	public static final DeferredItem<GnomeHatItem> GNOME_HAT = ITEMS.register("gnome_hat", () -> new GnomeHatItem(new Item.Properties()));
	public static final DeferredItem<DeferredSpawnEggItem> GNOME_SPAWN_EGG = ITEMS.register("gnome_spawn_egg", () -> new DeferredSpawnEggItem(() -> GnomeRegistry.GNOME.get(), 1189750, 13442571, new Item.Properties()));

	public static final DeferredHolder<EntityType<?>, EntityType<Gnome>> GNOME = ENTITY_TYPES.register("gnome", () ->
			EntityType.Builder.<Gnome>of(Gnome::new, MobCategory.CREATURE)
					.sized(0.5F, 0.8F).clientTrackingRange(10).build("gnome"));

	public static final DeferredHolder<SoundEvent, SoundEvent> GNOME_SPAWN = SOUND_EVENTS.register("gnome.summon", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MOD_ID, "gnome.summon")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GNOME_PASSIVE = SOUND_EVENTS.register("gnome.passive", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MOD_ID, "gnome.passive")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GNOME_HURT = SOUND_EVENTS.register("gnome.hurt", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MOD_ID, "gnome.hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GNOME_DEATH = SOUND_EVENTS.register("gnome.death", () ->
			SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MOD_ID, "gnome.death")));
}
