package com.stargazer.mineframe.renderers.models;

import com.stargazer.mineframe.items.Chainsaw;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.stargazer.mineframe.MineFrame.MODID;

public class ChainsawModel extends AnimatedGeoModel<Chainsaw> {

    @Override
    public ResourceLocation getModelLocation(Chainsaw object) {
        return new ResourceLocation(MODID, "geo/chainsaw.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Chainsaw object) {
        return new ResourceLocation(MODID, "textures/items/chainsaw.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Chainsaw animatable) {
        return new ResourceLocation(MODID, "animations/chainsaw.animation.json");
    }
}
