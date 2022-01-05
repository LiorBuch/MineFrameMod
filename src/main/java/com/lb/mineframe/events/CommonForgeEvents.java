package com.lb.mineframe.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.lb.mineframe.MineFrame.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void headshotsDamage(LivingAttackEvent event){
        Entity hitEntity = event.getEntity();
        Entity ownerEntity = event.getSource().getEntity();
        if (ownerEntity instanceof Player player){

        }
    }
}
