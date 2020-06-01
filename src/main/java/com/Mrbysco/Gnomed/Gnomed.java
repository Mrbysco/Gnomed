package com.mrbysco.gnomed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mrbysco.gnomed.config.GnomeConfig;
import com.mrbysco.gnomed.init.GnomeEntities;
import com.mrbysco.gnomed.init.GnomeSounds;
import com.mrbysco.gnomed.init.GnomeSpawning;
import com.mrbysco.gnomed.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID,
	name = Reference.MOD_NAME, 
	version = Reference.VERSION, 
	acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)

public class Gnomed
{
	@Instance(Reference.MOD_ID)
	public static Gnomed instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);

	@EventHandler
    public void PreInit(FMLPreInitializationEvent event)
    {
		logger.debug("Registering Config");
		MinecraftForge.EVENT_BUS.register(new GnomeConfig());
    	
		logger.debug("Registering sounds");
    	GnomeSounds.registerSounds();
    	
    	logger.debug("Registering entities");
    	GnomeEntities.register();
    	
    	proxy.Preinit();
    }
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		logger.debug("Registered gnome spawning");
		GnomeSpawning.registerSpawning();

		proxy.Init();
    }
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		
    }
}
