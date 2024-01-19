package net.skidcode.gh.twilightforest.entity.render;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class ModelTwilightBighorn
extends QuadrupedEntityModel {
    public ModelPart horn1a;
    public ModelPart horn1b;
    public ModelPart horn1c;
    public ModelPart horn1d;
    public ModelPart horn1e;
    public ModelPart horn2a;
    public ModelPart horn2b;
    public ModelPart horn2c;
    public ModelPart horn2d;
    public ModelPart horn2e;

    public ModelTwilightBighorn() {
        super(12, 0.0f);
        this.head = new ModelPart(0, 0);
        this.head.addCuboid(-3.0f, -4.0f, -6.0f, 6, 6, 7, 0.0f);
        this.head.setPivot(0.0f, 6.0f, -8.0f);
        this.body = new ModelPart(36, 10);
        this.body.addCuboid(-4.0f, -9.0f, -7.0f, 8, 15, 6, 0.0f);
        this.body.setPivot(0.0f, 5.0f, 2.0f);
        this.rightHindLeg = new ModelPart(0, 16);
        this.rightHindLeg.addCuboid(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.rightHindLeg.setPivot(-3.0f, 12.0f, 7.0f);
        this.leftHindLeg = new ModelPart(0, 16);
        this.leftHindLeg.addCuboid(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.leftHindLeg.setPivot(3.0f, 12.0f, 7.0f);
        this.rightFrontLeg = new ModelPart(0, 16);
        this.rightFrontLeg.addCuboid(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.rightFrontLeg.setPivot(-3.0f, 12.0f, -5.0f);
        this.leftFrontLeg = new ModelPart(0, 16);
        this.leftFrontLeg.addCuboid(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        this.leftFrontLeg.setPivot(3.0f, 12.0f, -5.0f);
        this.horn1a = new ModelPart(28, 16);
        this.horn1a.addCuboid(-5.0f, -4.0f, -4.0f, 2, 2, 2, 0.0f);
        this.horn1a.setPivot(0.0f, 6.0f, -8.0f);
        this.horn1b = new ModelPart(16, 13);
        this.horn1b.addCuboid(-6.0f, -5.0f, -3.0f, 2, 2, 4, 0.0f);
        this.horn1b.setPivot(0.0f, 6.0f, -8.0f);
        this.horn1c = new ModelPart(16, 19);
        this.horn1c.addCuboid(-7.0f, -4.0f, 0.0f, 2, 5, 2, 0.0f);
        this.horn1c.setPivot(0.0f, 6.0f, -8.0f);
        this.horn1d = new ModelPart(18, 27);
        this.horn1d.addCuboid(-8.0f, 0.0f, -2.0f, 2, 2, 3, 0.0f);
        this.horn1d.setPivot(0.0f, 6.0f, -8.0f);
        this.horn1e = new ModelPart(28, 27);
        this.horn1e.addCuboid(-9.0f, -1.0f, -3.0f, 2, 2, 1, 0.0f);
        this.horn1e.setPivot(0.0f, 6.0f, -8.0f);
        this.horn2a = new ModelPart(28, 16);
        this.horn2a.addCuboid(3.0f, -4.0f, -4.0f, 2, 2, 2, 0.0f);
        this.horn2a.setPivot(0.0f, 6.0f, -8.0f);
        this.horn2b = new ModelPart(16, 13);
        this.horn2b.addCuboid(4.0f, -5.0f, -3.0f, 2, 2, 4, 0.0f);
        this.horn2b.setPivot(0.0f, 6.0f, -8.0f);
        this.horn2c = new ModelPart(16, 19);
        this.horn2c.addCuboid(5.0f, -4.0f, 0.0f, 2, 5, 2, 0.0f);
        this.horn2c.setPivot(0.0f, 6.0f, -8.0f);
        this.horn2d = new ModelPart(18, 27);
        this.horn2d.addCuboid(6.0f, 0.0f, -2.0f, 2, 2, 3, 0.0f);
        this.horn2d.setPivot(0.0f, 6.0f, -8.0f);
        this.horn2e = new ModelPart(28, 27);
        this.horn2e.addCuboid(7.0f, -1.0f, -3.0f, 2, 2, 1, 0.0f);
        this.horn2e.setPivot(0.0f, 6.0f, -8.0f);
    }

    @Override
    public void render(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.render(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        this.horn1a.render(scale);
        this.horn1b.render(scale);
        this.horn1c.render(scale);
        this.horn1d.render(scale);
        this.horn1e.render(scale);
        this.horn2a.render(scale);
        this.horn2b.render(scale);
        this.horn2c.render(scale);
        this.horn2d.render(scale);
        this.horn2e.render(scale);
    }

    @Override
    public void setAngles(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.setAngles(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        this.horn1a.yaw = this.head.yaw;
        this.horn1a.pitch = this.head.pitch;
        this.horn1b.yaw = this.head.yaw;
        this.horn1b.pitch = this.head.pitch;
        this.horn1c.yaw = this.head.yaw;
        this.horn1c.pitch = this.head.pitch;
        this.horn1d.yaw = this.head.yaw;
        this.horn1d.pitch = this.head.pitch;
        this.horn1e.yaw = this.head.yaw;
        this.horn1e.pitch = this.head.pitch;
        this.horn2a.yaw = this.head.yaw;
        this.horn2a.pitch = this.head.pitch;
        this.horn2b.yaw = this.head.yaw;
        this.horn2b.pitch = this.head.pitch;
        this.horn2c.yaw = this.head.yaw;
        this.horn2c.pitch = this.head.pitch;
        this.horn2d.yaw = this.head.yaw;
        this.horn2d.pitch = this.head.pitch;
        this.horn2e.yaw = this.head.yaw;
        this.horn2e.pitch = this.head.pitch;
    }
}