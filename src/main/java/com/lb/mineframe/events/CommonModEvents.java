package com.lb.mineframe.events;

import com.lb.mineframe.renderers.ClipCrossbowRenderer;
import com.lb.mineframe.setups.Registrations;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import static com.lb.mineframe.MineFrame.MODID;

@Mod.EventBusSubscriber(modid = MODID,bus= Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){}

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){}

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {}




}
