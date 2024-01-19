package net.skidcode.gh.twilightforest;

import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Null;

public class TwilightForest {
	public static final Namespace MODID = Null.get();
	public static Identifier of(String id) {
        return Identifier.of(MODID, id);
    }
}
