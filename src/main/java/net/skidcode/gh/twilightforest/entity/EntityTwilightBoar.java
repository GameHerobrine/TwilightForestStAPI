package net.skidcode.gh.twilightforest.entity;

import net.minecraft.entity.passive.PigEntity;
import net.minecraft.world.World;

public class EntityTwilightBoar
extends PigEntity {
    public EntityTwilightBoar(World world) {
        super(world);
        this.texture = "tforest:textures/entity/wildboar.png";
        this.setBoundingBoxSpacing(0.9f, 0.9f);
    }
}