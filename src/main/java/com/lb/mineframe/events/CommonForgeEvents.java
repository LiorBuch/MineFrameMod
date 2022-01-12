package com.lb.mineframe.events;

import com.lb.mineframe.MineFrame;
import com.lb.mineframe.setups.Registrations;
import com.lb.mineframe.world.gen.ModBiomeGen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
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

    @SubscribeEvent
    public static void onBiomeRegister(BiomeLoadingEvent event){
        //BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(Registrations.DARK_SEA_BIOME, 10));
        //BiomeDictionary.addTypes(Registrations.DARK_SEA_BIOME, BiomeDictionary.Type.OCEAN, BiomeDictionary.Type.OVERWORLD);
    }

}
