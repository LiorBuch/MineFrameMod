package com.lb.mineframe.fluid;

import com.lb.mineframe.setups.Registrations;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;

public class OilFluid {
    public static final ForgeFlowingFluid.Properties OIL_PROPERTIES = new ForgeFlowingFluid.Properties(()-> Registrations.OIL_FLUID.get(),()-> Registrations.OIL_FLOWING.get(), FluidAttributes.builder
            (new ResourceLocation("block/water_still"),new ResourceLocation("block/water_flow")).density(12).luminosity(1).viscosity(12)
            .color(0x1c1f24)).slopeFindDistance(2).levelDecreasePerBlock(1).block(()->Registrations.OIL_BLOCK.get()).bucket(()->Registrations.OIL_BUCKET.get());
}
