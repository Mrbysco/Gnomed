package com.mrbysco.gnomed.config;

import com.mrbysco.gnomed.Gnomed;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class GnomeConfig
{
	public static class Server {
		public final BooleanValue GnomeSpawning;

		Server(ForgeConfigSpec.Builder builder) {
			builder.comment("Gnome settings")
					.push("Gnome");

			GnomeSpawning = builder
					.comment("Setting this to true lets Gnome spawn in the world (Default: true)")
					.define("GnomeSpawning", true);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec serverSpec;
	public static final GnomeConfig.Server SERVER;

	static {
		final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(GnomeConfig.Server::new);
		serverSpec = specPair.getRight();
		SERVER = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {
		Gnomed.logger.debug("Loaded Gnomed's config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfig.ModConfigEvent configEvent) {
		Gnomed.logger.debug("Gnomed's config just got changed on the file system!");
	}
}