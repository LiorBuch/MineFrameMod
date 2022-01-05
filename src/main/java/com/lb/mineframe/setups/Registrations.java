package com.lb.mineframe.setups;

import com.lb.mineframe.items.ClipCrossbow;
import com.lb.mineframe.utils.KeyMaps;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.lb.mineframe.MineFrame.MINEFRAME_CREATIVE_TAB;
import static com.lb.mineframe.MineFrame.MODID;

public class Registrations {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES,MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,MODID);


    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        KeyMaps.init();

        ITEMS.register(bus);

    }

    //******************************ITEMS******************************
    public static final RegistryObject<Item> PLASTICS = ITEMS.register("plastics", () -> new Item(new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));
    public static final RegistryObject<ClipCrossbow> CLIP_CROSSBOW = ITEMS.register("clip_crossbow",()-> new ClipCrossbow(new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));

    //******************************BLOCKS******************************

    // ******************************Entities******************************


    //******************************PARTICLES******************************
    //public static final RegistryObject<ParticleType<?>> NEON_BLUE_PARTICLE = PARTICLES.register("neon_blue_particle",()->new ParticleType<ParticleStatus>)
}
