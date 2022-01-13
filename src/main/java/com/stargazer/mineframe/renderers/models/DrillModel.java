package com.stargazer.mineframe.renderers.models;

import com.stargazer.mineframe.items.Drill;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.stargazer.mineframe.MineFrame.MODID;

public class DrillModel extends AnimatedGeoModel<Drill> {

    @Override
    public ResourceLocation getModelLocation(Drill object) {
        return new ResourceLocation(MODID, "geo/drill.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Drill object) {
        return new ResourceLocation(MODID, "textures/items/drill.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Drill animatable) {
        return new ResourceLocation(MODID, "animations/drill.animation.json");
    }
}
