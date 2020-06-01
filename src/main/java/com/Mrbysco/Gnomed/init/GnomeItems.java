package com.mrbysco.gnomed.init;

import java.util.ArrayList;

import com.mrbysco.gnomed.items.ItemGnomeHat;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class GnomeItems {
	
	public static Item gnome_hat;
	
	public static ArrayList<Item> ITEMS = new ArrayList<>();
	
	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
		
        IForgeRegistry<Item> registry = event.getRegistry();

		gnome_hat = registerItem(new ItemGnomeHat("gnome_hat"));
		
		registry.registerAll(ITEMS.toArray(new Item[0]));
	}
	
	public static <T extends Item> T registerItem(T item)
    {
        ITEMS.add(item);
        return item;
    }
}