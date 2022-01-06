package com.lb.mineframe.renderers;

import com.lb.mineframe.items.Chainsaw;
import com.lb.mineframe.renderers.models.ChainsawModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ChainsawRenderer extends GeoItemRenderer<Chainsaw> {
public ChainsawRenderer() {
        super(new ChainsawModel());
        }
}
