package net.skidcode.gh.twilightforest.entity.render;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class ModelTwilightBoar
extends QuadrupedEntityModel {
    public ModelPart snout;
    public ModelPart tusk1;
    public ModelPart tusk2;

    public ModelTwilightBoar() {
        super(6, 0.0f);
        this.head = new ModelPart(0, 0);
        this.head.addCuboid(-4.0f, -2.0f, -6.0f, 8, 7, 6, 0.0f);
        this.head.setPivot(0.0f, 12.0f, -6.0f);
        this.body = new ModelPart(28, 10);
        this.body.addCuboid(-5.0f, -8.0f, -7.0f, 10, 14, 8, 0.0f);
        this.body.setPivot(0.0f, 11.0f, 2.0f);
        this.body.pitch = 1.570796f;
        this.rightHindLeg = new ModelPart(0, 16);
        this.rightHindLeg.addCuboid(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.rightHindLeg.setPivot(-3.0f, 18.0f, 7.0f);
        this.leftHindLeg = new ModelPart(0, 16);
        this.leftHindLeg.addCuboid(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.leftHindLeg.setPivot(3.0f, 18.0f, 7.0f);
        this.rightFrontLeg = new ModelPart(0, 16);
        this.rightFrontLeg.addCuboid(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.rightFrontLeg.setPivot(-3.0f, 18.0f, -5.0f);
        this.leftFrontLeg = new ModelPart(0, 16);
        this.leftFrontLeg.addCuboid(-2.0f, 0.0f, -2.0f, 4, 6, 4, 0.0f);
        this.leftFrontLeg.setPivot(3.0f, 18.0f, -5.0f);
        this.snout = new ModelPart(28, 0);
        this.snout.addCuboid(-3.0f, 1.0f, -9.0f, 6, 4, 3, 0.0f);
        this.snout.setPivot(0.0f, 12.0f, -6.0f);
        this.tusk1 = new ModelPart(17, 17);
        this.tusk1.addCuboid(4.0f, 1.0f, -9.0f, 1, 2, 1, 0.0f);
        this.tusk1.setPivot(0.0f, 12.0f, -6.0f);
        this.tusk1.roll = 0.3490658f;
        this.tusk2 = new ModelPart(17, 17);
        this.tusk2.addCuboid(-5.0f, 1.0f, -9.0f, 1, 2, 1, 0.0f);
        this.tusk2.setPivot(0.0f, 12.0f, -6.0f);
        this.tusk2.roll = -0.3490658f;
    }

    @Override
    public void render(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.render(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        this.snout.render(scale);
        this.tusk1.render(scale);
        this.tusk2.render(scale);
    }

    @Override
    public void setAngles(float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch, float scale) {
        super.setAngles(limbAngle, limbDistance, animationProgress, headYaw, headPitch, scale);
        this.snout.yaw = this.head.yaw;
        this.snout.pitch = this.head.pitch;
        this.tusk1.yaw = this.head.yaw;
        this.tusk1.pitch = this.head.pitch;
        this.tusk2.yaw = this.head.yaw;
        this.tusk2.pitch = this.head.pitch;
    }
}