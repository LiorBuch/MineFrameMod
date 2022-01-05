package com.lb.mineframe.renderers;

import com.lb.mineframe.items.AntiAirFireworkLauncher;
import com.lb.mineframe.items.ClipCrossbow;
import com.lb.mineframe.renderers.models.AntiAirFireworkLauncherModel;
import com.lb.mineframe.renderers.models.ClipCrossbowModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AntiAirFireworkLauncherRenderer extends GeoItemRenderer<AntiAirFireworkLauncher> {
    public AntiAirFireworkLauncherRenderer() {
        super(new AntiAirFireworkLauncherModel());
    }
}
