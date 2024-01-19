package net.skidcode.gh.twilightforest.entity.render;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class ModelBighornFur
extends QuadrupedEntityModel {
    public ModelBighornFur() {
        super(12, 0.0f);
        this.head = new ModelPart(0, 0);
        this.head.addCuboid(-3.0f, -4.0f, -4.0f, 6, 6, 6, 0.6f);
        this.head.setPivot(0.0f, 6.0f, -8.0f);
        this.body = new ModelPart(28, 8);
        this.body.addCuboid(-4.0f, -9.0f, -7.0f, 8, 15, 6, 0.5f);
        this.body.setPivot(0.0f, 5.0f, 2.0f);
        float f = 0.4f;
        this.rightHindLeg = new ModelPart(0, 16);
        this.rightHindLeg.addCuboid(-2.0f, 0.0f, -2.0f, 4, 6, 4, f);
        this.rightHindLeg.setPivot(-3.0f, 12.0f, 7.0f);
        this.leftHindLeg = new ModelPart(0, 16);
        this.leftHindLeg.addCuboid(-2.0f, 0.0f, -2.0f, 4, 6, 4, f);
        this.leftHindLeg.setPivot(3.0f, 12.0f, 7.0f);
        this.rightFrontLeg = new ModelPart(0, 16);
        this.rightFrontLeg.addCuboid(-2.0f, 0.0f, -2.0f, 4, 6, 4, f);
        this.rightFrontLeg.setPivot(-3.0f, 12.0f, -5.0f);
        this.leftFrontLeg = new ModelPart(0, 16);
        this.leftFrontLeg.addCuboid(-2.0f, 0.0f, -2.0f, 4, 6, 4, f);
        this.leftFrontLeg.setPivot(3.0f, 12.0f, -5.0f);
    }
}