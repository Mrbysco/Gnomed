package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.entity.Gnome;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GnomeRegistry {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MOD_ID);
	public static final DeferredRegister.Entities ENTITIES = DeferredRegister.createEntities(Reference.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, Reference.MOD_ID);

	public static final Supplier<EntityType<Gnome>> GNOME = ENTITIES.registerEntityType("gnome",
			Gnome::new,
			MobCategory.CREATURE,
			builder -> builder
					.sized(0.5F, 0.8F)
					.clientTrackingRange(10)
					.eyeHeight(0.7F)
	);

	public static final DeferredItem<Item> GNOME_HAT = ITEMS.registerSimpleItem("gnome_hat", () -> new Item.Properties()
			.stacksTo(1).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.HEAD)
					.setEquipSound(SoundEvents.ARMOR_EQUIP_LEATHER).build()));
	public static final DeferredItem<SpawnEggItem> GNOME_SPAWN_EGG = ITEMS.registerItem("gnome_spawn_egg", (properties) ->
			new SpawnEggItem(properties.spawnEgg(GnomeRegistry.GNOME.get())));

	public static final DeferredHolder<SoundEvent, SoundEvent> GNOME_SPAWN = SOUND_EVENTS.register("gnome.summon", () ->
			SoundEvent.createVariableRangeEvent(Reference.modLoc("gnome.summon")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GNOME_PASSIVE = SOUND_EVENTS.register("gnome.passive", () ->
			SoundEvent.createVariableRangeEvent(Reference.modLoc("gnome.passive")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GNOME_HURT = SOUND_EVENTS.register("gnome.hurt", () ->
			SoundEvent.createVariableRangeEvent(Reference.modLoc("gnome.hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> GNOME_DEATH = SOUND_EVENTS.register("gnome.death", () ->
			SoundEvent.createVariableRangeEvent(Reference.modLoc("gnome.death")));
}
