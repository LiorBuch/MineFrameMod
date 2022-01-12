package com.lb.mineframe.renderers;

import com.lb.mineframe.items.Chainsaw;
import com.lb.mineframe.items.Drill;
import com.lb.mineframe.renderers.models.ChainsawModel;
import com.lb.mineframe.renderers.models.DrillModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class DrillRenderer extends GeoItemRenderer<Drill> {
    public DrillRenderer() {
        super(new DrillModel());
    }
}
