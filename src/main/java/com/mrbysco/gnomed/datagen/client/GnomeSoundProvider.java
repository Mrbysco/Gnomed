package com.mrbysco.gnomed.datagen.client;

import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.init.GnomeRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class GnomeSoundProvider extends SoundDefinitionsProvider {
	public GnomeSoundProvider(PackOutput output) {
		super(output, Reference.MOD_ID);
	}

	@Override
	public void registerSounds() {
		this.add(GnomeRegistry.GNOME_SPAWN, definition()
				.subtitle(modSubtitle(GnomeRegistry.GNOME_SPAWN.getId()))
				.with(
						sound(modLoc("gnome/gnome_spawn")).stream()
				)
		);

		this.add(GnomeRegistry.GNOME_PASSIVE, definition()
				.subtitle(modSubtitle(GnomeRegistry.GNOME_PASSIVE.getId()))
				.with(
						sound(modLoc("gnome/gnome_ambient")),
						sound(modLoc("gnome/gnome_ambient2")),
						sound(modLoc("gnome/gnome_ambient3")),
						sound(modLoc("gnome/gnome_ambient4")),
						sound(modLoc("gnome/gnome_ambient5"))
				)
		);

		this.add(GnomeRegistry.GNOME_HURT, definition()
				.subtitle(modSubtitle(GnomeRegistry.GNOME_HURT.getId()))
				.with(
						sound(modLoc("gnome/gnome_ambient"))
				)
		);

		this.add(GnomeRegistry.GNOME_DEATH, definition()
				.subtitle(modSubtitle(GnomeRegistry.GNOME_DEATH.getId()))
				.with(
						sound(modLoc("gnome/gnome_death"))
				)
		);
	}

	public String modSubtitle(Identifier id) {
		return Reference.MOD_ID + ".subtitle." + id.getPath();
	}

	public Identifier modLoc(String name) {
		return Reference.modLoc(name);
	}
}
