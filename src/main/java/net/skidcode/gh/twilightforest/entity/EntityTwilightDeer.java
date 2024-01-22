package net.skidcode.gh.twilightforest.entity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class EntityTwilightDeer
extends CowEntity {
    public EntityTwilightDeer(World world) {
        super(world);
        this.texture = "tforest:textures/entity/wilddeer.png";
        this.setBoundingBoxSpacing(0.7f, 2.3f);
    }

    @Override
    protected String method_911() {
        return null;
    }

    @Override
    public boolean method_1323(PlayerEntity entityplayer) {
        return false;
    }
}