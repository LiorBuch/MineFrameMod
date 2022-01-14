package com.stargazer.mineframe.renderers;

import com.stargazer.mineframe.items.AntiAirFireworkLauncher;
import com.stargazer.mineframe.items.AntiPersonalFireworkLauncher;
import com.stargazer.mineframe.renderers.models.AntiAirFireworkLauncherModel;
import com.stargazer.mineframe.renderers.models.AntiPersonalFireworkLauncherModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AntiPersonalFireworkLauncherRenderer extends GeoItemRenderer<AntiPersonalFireworkLauncher> {
    public AntiPersonalFireworkLauncherRenderer() {
        super(new AntiPersonalFireworkLauncherModel());
    }
}
