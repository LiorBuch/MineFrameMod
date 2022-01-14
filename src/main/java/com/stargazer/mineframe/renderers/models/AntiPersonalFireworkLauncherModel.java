package com.stargazer.mineframe.renderers.models;

import com.stargazer.mineframe.MineFrame;
import com.stargazer.mineframe.items.AntiAirFireworkLauncher;
import com.stargazer.mineframe.items.AntiPersonalFireworkLauncher;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AntiPersonalFireworkLauncherModel extends AnimatedGeoModel<AntiPersonalFireworkLauncher> {

    @Override
    public ResourceLocation getModelLocation(AntiPersonalFireworkLauncher object) {
        return new ResourceLocation(MineFrame.MODID, "geo/anti_personal_firework_launcher.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AntiPersonalFireworkLauncher object) {
        return new ResourceLocation(MineFrame.MODID, "textures/items/anti_personal_firework_launcher.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AntiPersonalFireworkLauncher animatable) {
        return new ResourceLocation(MineFrame.MODID, "animations/anti_personal_firework_launcher.animation.json");
    }
}
