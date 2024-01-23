package net.skidcode.gh.twilightforest;


import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.world.dimension.Dimension;
import net.modificationstation.stationapi.api.event.registry.DimensionRegistryEvent;
import net.modificationstation.stationapi.api.registry.DimensionContainer;
import net.modificationstation.stationapi.api.util.Identifier;
import net.skidcode.gh.twilightforest.dimension.DimensionTwilightForest;
import static  net.skidcode.gh.twilightforest.TwilightForest.of;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class DimensionRegistryTwilightForest {
	public static DimensionContainer<Dimension> TFOREST;
	public static Identifier ID;
	@EventListener
	@Environment(EnvType.CLIENT)
	public static void registerDimensions(DimensionRegistryEvent event) {
		TFOREST = new DimensionContainer<>(DimensionTwilightForest::new);
		event.registry.register(ID = of("twilight_forest"), TFOREST);
	}
	@Environment(EnvType.SERVER)
	@EventListener
	public static void registerDimensions_server(DimensionRegistryEvent event) {
		TFOREST = new DimensionContainer<>(net.skidcode.gh.twilightforest.server.DimensionTwilightForest::new);
		event.registry.register(ID = of("twilight_forest"), TFOREST);
	}
}
