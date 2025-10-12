package com.mrbysco.gnomed.datagen.client;

import com.mrbysco.gnomed.Reference;
import com.mrbysco.gnomed.init.GnomeRegistry;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class GnomeModelProvider extends ModelProvider {
	public GnomeModelProvider(PackOutput output) {
		super(output, Reference.MOD_ID);
	}

	@Override
	protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
		itemModels.itemModelOutput.accept(GnomeRegistry.GNOME_HAT.get(), ItemModelUtils.plainModel(GnomeRegistry.GNOME_HAT.getId().withPrefix("item/")));
		itemModels.generateFlatItem(GnomeRegistry.GNOME_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);
	}
}
