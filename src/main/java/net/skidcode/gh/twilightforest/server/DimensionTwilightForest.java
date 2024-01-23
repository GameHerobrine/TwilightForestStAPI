package net.skidcode.gh.twilightforest.server;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_51;
import net.minecraft.block.Block;
import net.minecraft.world.dimension.Dimension;
import net.modificationstation.stationapi.api.client.world.dimension.TravelMessageProvider;
import net.modificationstation.stationapi.api.registry.DimensionRegistry;
import net.skidcode.gh.twilightforest.DimensionRegistryTwilightForest;
import net.skidcode.gh.twilightforest.dimension.ChunkProviderTwilightForest;
import net.skidcode.gh.twilightforest.dimension.WorldChunkManagerTwilightForest;
@Environment(EnvType.SERVER)
public class DimensionTwilightForest extends Dimension{

	public DimensionTwilightForest()
	{
		this.id = DimensionRegistry.INSTANCE.getLegacyId(DimensionRegistryTwilightForest.ID).getAsInt();
	}
	
	@Override
    public float method_1771(long l, float f) {
        return 0.25f;
    }

    @Override
    public void method_1769() {
        this.field_2174 = new WorldChunkManagerTwilightForest(this.field_2173);
        this.id = DimensionRegistry.INSTANCE.getLegacyId(DimensionRegistryTwilightForest.ID).getAsInt();
    }

    @Override
    public class_51 method_1772() {
        return new ChunkProviderTwilightForest(this.field_2173, this.field_2173.getSeed());
    }

    @Override
    public float method_1764() {
        return 64.0f;
    }

    @Override
    public boolean method_1770(int i, int j) {
        int k = this.field_2173.method_152(i, j);
        if (k == 0) {
            return false;
        }
        return Block.BLOCKS[k].material.method_907();
    }
}
