package com.stargazer.mineframe.renderers;

import com.stargazer.mineframe.items.AntiAirFireworkLauncher;
import com.stargazer.mineframe.renderers.models.AntiAirFireworkLauncherModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AntiAirFireworkLauncherRenderer extends GeoItemRenderer<AntiAirFireworkLauncher> {
    public AntiAirFireworkLauncherRenderer() {
        super(new AntiAirFireworkLauncherModel());
    }
}
