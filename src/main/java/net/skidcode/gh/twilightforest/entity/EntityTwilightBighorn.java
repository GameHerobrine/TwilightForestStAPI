package net.skidcode.gh.twilightforest.entity;
import java.util.Random;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.world.World;

public class EntityTwilightBighorn
extends SheepEntity {
    public EntityTwilightBighorn(World world) {
        super(world);
        this.texture = "/twilightforest/bighorn.png";
        this.setBoundingBoxSpacing(0.9f, 1.3f);
    }

    public static int a(Random random) {
        if (random.nextInt(2) == 0) {
            return 12;
        }
        return random.nextInt(15);
    }
}