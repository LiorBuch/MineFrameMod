package com.stargazer.mineframe.renderers.models;

import com.stargazer.mineframe.items.AntiAirFireworkLauncher;
import com.stargazer.mineframe.MineFrame;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AntiAirFireworkLauncherModel extends AnimatedGeoModel<AntiAirFireworkLauncher> {

    @Override
    public ResourceLocation getModelLocation(AntiAirFireworkLauncher object) {
        return new ResourceLocation(MineFrame.MODID, "geo/anti_air_firework_launcher.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AntiAirFireworkLauncher object) {
        return new ResourceLocation(MineFrame.MODID, "textures/items/anti_air_firework_launcher.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AntiAirFireworkLauncher animatable) {
        return new ResourceLocation(MineFrame.MODID, "animations/anti_air_firework_launcher.animation.json");
    }
}
