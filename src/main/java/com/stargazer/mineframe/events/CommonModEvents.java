package com.stargazer.mineframe.events;

import com.stargazer.mineframe.MineFrame;
import com.stargazer.mineframe.setups.Registrations;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = MineFrame.MODID,bus= Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){}

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){}

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {}

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(Registrations.SULFURIC_BLOOMTREE_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(Registrations.SULFURIC_BLOOMTREE_SAPLING.get(), RenderType.cutout());
    }




}
