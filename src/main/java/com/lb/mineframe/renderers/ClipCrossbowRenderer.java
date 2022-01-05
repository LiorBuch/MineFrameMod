package com.lb.mineframe.renderers;


import com.lb.mineframe.items.ClipCrossbow;
import com.lb.mineframe.renderers.models.ClipCrossbowModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ClipCrossbowRenderer extends GeoItemRenderer<ClipCrossbow> {
    public ClipCrossbowRenderer() {
        super(new ClipCrossbowModel());
    }
}
