package com.stargazer.mineframe.renderers;

import com.stargazer.mineframe.items.Drill;
import com.stargazer.mineframe.renderers.models.DrillModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class DrillRenderer extends GeoItemRenderer<Drill> {
    public DrillRenderer() {
        super(new DrillModel());
    }
}
