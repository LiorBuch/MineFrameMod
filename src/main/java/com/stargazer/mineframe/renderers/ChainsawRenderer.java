package com.stargazer.mineframe.renderers;

import com.stargazer.mineframe.items.Chainsaw;
import com.stargazer.mineframe.renderers.models.ChainsawModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class ChainsawRenderer extends GeoItemRenderer<Chainsaw> {
public ChainsawRenderer() {
        super(new ChainsawModel());
        }
}
