package com.lb.mineframe.renderers.models;

import com.lb.mineframe.items.AntiAirFireworkLauncher;
import com.lb.mineframe.items.ClipCrossbow;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static com.lb.mineframe.MineFrame.MODID;

public class AntiAirFireworkLauncherModel extends AnimatedGeoModel<AntiAirFireworkLauncher> {

    @Override
    public ResourceLocation getModelLocation(AntiAirFireworkLauncher object) {
        return new ResourceLocation(MODID, "geo/anti_air_firework_launcher.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AntiAirFireworkLauncher object) {
        return new ResourceLocation(MODID, "textures/items/anti_air_firework_launcher.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AntiAirFireworkLauncher animatable) {
        return new ResourceLocation(MODID, "animations/anti_air_firework_launcher.animation.json");
    }
}
