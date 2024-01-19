package net.skidcode.gh.twilightforest;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.render.entity.CowEntityRenderer;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.render.entity.SheepEntityRenderer;
import net.minecraft.client.render.entity.model.PigEntityModel;
import net.modificationstation.stationapi.api.client.event.render.entity.EntityRendererRegisterEvent;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.skidcode.gh.twilightforest.entity.EntityTwilightBighorn;
import net.skidcode.gh.twilightforest.entity.EntityTwilightBoar;
import net.skidcode.gh.twilightforest.entity.EntityTwilightDeer;
import net.skidcode.gh.twilightforest.entity.render.ModelBighornFur;
import net.skidcode.gh.twilightforest.entity.render.ModelTwilightBighorn;
import net.skidcode.gh.twilightforest.entity.render.ModelTwilightBoar;
import net.skidcode.gh.twilightforest.entity.render.ModelTwilightDeer;

public class EntityRegistry {
	@EventListener
	public void registerEntities(EntityRegister event) {
		event.register(EntityTwilightBoar.class, "Wild Boar");
        event.register(EntityTwilightBighorn.class, "Bighorn Sheep");
        event.register(EntityTwilightDeer.class, "Wild Deer");
	}
	
	@EventListener
	public static void registerRenderer(EntityRendererRegisterEvent event)
    {
		event.renderers.put(EntityTwilightBoar.class, new PigEntityRenderer(new ModelTwilightBoar(), new PigEntityModel(0.5f), 0.7f));
		event.renderers.put(EntityTwilightBighorn.class, new SheepEntityRenderer(new ModelTwilightBighorn(), new ModelBighornFur(), 0.7f));
		event.renderers.put(EntityTwilightDeer.class, new CowEntityRenderer(new ModelTwilightDeer(), 0.7f));
    }
}
