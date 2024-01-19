package net.skidcode.gh.twilightforest;


import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.registry.DimensionRegistryEvent;
import net.modificationstation.stationapi.api.registry.DimensionContainer;
import net.modificationstation.stationapi.api.util.Identifier;
import net.skidcode.gh.twilightforest.dimension.DimensionTwilightForest;
import static  net.skidcode.gh.twilightforest.TwilightForest.of;

public class DimensionRegistryTwilightForest {
	public static DimensionContainer<DimensionTwilightForest> TFOREST = new DimensionContainer<>(DimensionTwilightForest::new);
	public static Identifier ID;
	@EventListener
	public static void registerDimensions(DimensionRegistryEvent event) {
		event.registry.register(ID = of("twilight_forest"), TFOREST);
	}
	
}
