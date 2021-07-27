package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.entities.Gnome;
import com.mrbysco.gnomed.items.CustomSpawnEggItem;
import com.mrbysco.gnomed.items.GnomeHatItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GnomeRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID);

    public static final RegistryObject<Item> GNOME_HAT = ITEMS.register("gnome_hat", () -> new GnomeHatItem(new Item.Properties()));
    public static final RegistryObject<Item> GNOME_SPAWN_EGG = ITEMS.register("gnome_spawn_egg", () -> new CustomSpawnEggItem(() -> GnomeRegistry.GNOME.get(), 1189750, 13442571, new Item.Properties()));

    public static final RegistryObject<EntityType<Gnome>> GNOME = ENTITIES.register("gnome", () -> register("gnome", EntityType.Builder.<Gnome>of(Gnome::new, MobCategory.CREATURE).sized(0.5F, 0.8F)));

    public static final RegistryObject<SoundEvent> GNOME_SPAWN = SOUND_EVENTS.register("gnome.summon", () -> new SoundEvent(new ResourceLocation(Reference.MOD_ID, "gnome.summon")));
    public static final RegistryObject<SoundEvent> GNOME_PASSIVE = SOUND_EVENTS.register("gnome.passive", () -> new SoundEvent(new ResourceLocation(Reference.MOD_ID, "gnome.passive")));
    public static final RegistryObject<SoundEvent> GNOME_HURT = SOUND_EVENTS.register("gnome.hurt", () -> new SoundEvent(new ResourceLocation(Reference.MOD_ID, "gnome.hurt")));
    public static final RegistryObject<SoundEvent> GNOME_DEATH = SOUND_EVENTS.register("gnome.death", () -> new SoundEvent(new ResourceLocation(Reference.MOD_ID, "gnome.death")));

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder, boolean sendVelocityUpdates) {
        return builder.setTrackingRange(64).setUpdateInterval(3).setShouldReceiveVelocityUpdates(sendVelocityUpdates).build(id);
    }

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
        return register(id, builder, true);
    }
}
