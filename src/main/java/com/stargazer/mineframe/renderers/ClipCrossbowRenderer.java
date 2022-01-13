package com.stargazer.mineframe.renderers;


import com.stargazer.mineframe.items.ClipCrossbow;
import com.stargazer.mineframe.renderers.models.ClipCrossbowModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ClipCrossbowRenderer extends GeoItemRenderer<ClipCrossbow> {
    public ClipCrossbowRenderer() {
        super(new ClipCrossbowModel());
    }
}
