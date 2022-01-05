package com.lb.mineframe.renderers.models;

import com.lb.mineframe.items.ClipCrossbow;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import static com.lb.mineframe.MineFrame.MODID;


public class ClipCrossbowModel extends AnimatedGeoModel<ClipCrossbow> {

    @Override
    public ResourceLocation getModelLocation(ClipCrossbow object) {
        return new ResourceLocation(MODID, "geo/clip_crossbow.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ClipCrossbow object) {
        return new ResourceLocation(MODID, "textures/items/clip_crossbow.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ClipCrossbow animatable) {
        return new ResourceLocation(MODID, "animations/clip_crossbow.animation.json");
    }
}
