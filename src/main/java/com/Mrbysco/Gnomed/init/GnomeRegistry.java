package com.mrbysco.gnomed.init;

import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.entities.GnomeEntity;
import com.mrbysco.gnomed.items.CustomSpawnEggItem;
import com.mrbysco.gnomed.items.ItemGnomeHat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GnomeRegistry {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Reference.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID);

    public static final RegistryObject<Item> GNOME_HAT = ITEMS.register("gnome_hat", () -> new ItemGnomeHat(new Item.Properties()));
    public static final RegistryObject<Item> GNOME_SPAWN_EGG = ITEMS.register("gnome_spawn_egg", () -> new CustomSpawnEggItem(() -> GnomeRegistry.GNOME.get(), 1189750, 13442571, new Item.Properties()));

    public static final RegistryObject<EntityType<GnomeEntity>> GNOME = ENTITIES.register("gnome", () -> register("gnome", EntityType.Builder.<GnomeEntity>create(GnomeEntity::new, EntityClassification.MONSTER).size(0.5F, 0.8F)));

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
