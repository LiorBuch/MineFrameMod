package com.stargazer.mineframe.events;

import com.stargazer.mineframe.MineFrame;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MineFrame.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent
    public static void headshotsDamage(LivingAttackEvent event){
        Entity hitEntity = event.getEntity();
        Entity ownerEntity = event.getSource().getEntity();
        if (ownerEntity instanceof Player player){

        }
    }

    @SubscribeEvent
    public static void onBiomeRegister(BiomeLoadingEvent event){
        //BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(Registrations.DARK_SEA_BIOME, 10));
        //BiomeDictionary.addTypes(Registrations.DARK_SEA_BIOME, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.OVERWORLD);
    }

}
