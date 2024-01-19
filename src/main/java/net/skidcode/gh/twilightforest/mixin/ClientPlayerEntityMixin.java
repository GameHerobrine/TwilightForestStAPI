package net.skidcode.gh.twilightforest.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import net.minecraft.class_467;
import net.minecraft.block.Block;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.modificationstation.stationapi.api.entity.HasTeleportationManager;
import net.modificationstation.stationapi.api.registry.DimensionRegistry;
import net.modificationstation.stationapi.api.world.dimension.DimensionHelper;
import net.modificationstation.stationapi.api.world.dimension.TeleportationManager;
import net.skidcode.gh.twilightforest.DimensionRegistryTwilightForest;
import net.skidcode.gh.twilightforest.dimension.DimensionTwilightForest;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin implements TeleportationManager{
	@Unique
	boolean insideTF = false;
	@Inject(method = "method_937", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;method_2139()V"))
	private void handleTicking(CallbackInfo info) {
		ClientPlayerEntity player = (ClientPlayerEntity) (Object) this;
		//DimensionRegistry.INSTANCE.getByLegacyId(player.dimensionId);
		DimensionRegistry.INSTANCE.getByLegacyId(player.dimensionId).ifPresent(dim -> {
			insideTF = dim.equals(DimensionRegistryTwilightForest.TFOREST);
		});
		if((player.getHand() != null && player.getHand().itemId == Block.RED_MUSHROOM.id) || insideTF) {
			((HasTeleportationManager)player).setTeleportationManager(this);
		}
		System.out.println("im in portal!");
	}
	
	@Unique
	@Override
	public void switchDimension(PlayerEntity player) {
		if(!insideTF) {
			DimensionHelper.switchDimension(player, DimensionRegistryTwilightForest.ID, 1, new class_467());
			System.out.println(DimensionRegistry.INSTANCE.getLegacyId(DimensionRegistryTwilightForest.ID));
			insideTF = true;
		}else {
			//TODO
		}
		
	}
}
