package com.stargazer.mineframe.setups;

import com.stargazer.mineframe.blocks.SulfuricBloomTreeLeaves;
import com.stargazer.mineframe.items.*;
import com.stargazer.mineframe.utils.ModFlammableBlock;
import com.stargazer.mineframe.utils.ModFlammablePillarBlocks;
import com.stargazer.mineframe.effects.HeavyWeight;
import com.stargazer.mineframe.fluid.OilFluid;
import com.stargazer.mineframe.utils.KeyMaps;
import com.stargazer.mineframe.world.biome.AlliumFields;
import com.stargazer.mineframe.world.biome.DarkSea;
import com.stargazer.mineframe.world.trees.SulfuricBloomtreeGrower;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.stargazer.mineframe.MineFrame.MINEFRAME_CREATIVE_TAB;
import static com.stargazer.mineframe.MineFrame.MODID;

public class Registrations {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES,MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,MODID);
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS,MODID);
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES,MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        KeyMaps.init();
        SOUNDS.register(bus);
        EFFECTS.register(bus);
        POTIONS.register(bus);
        FLUIDS.register(bus);
        BIOMES.register(bus);

        ITEMS.register(bus);
    }

    //******************************ITEMS******************************
    public static final RegistryObject<Item> PLASTICS = ITEMS.register("plastics", () -> new Item(new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));
    public static final RegistryObject<ClipCrossbow> CLIP_CROSSBOW = ITEMS.register("clip_crossbow",()-> new ClipCrossbow(new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));
    public static final RegistryObject<AntiAirFireworkLauncher> ANTI_AIR_FIREWORK_LAUNCHER = ITEMS.register("anti_air_firework_launcher",()-> new AntiAirFireworkLauncher(new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));
    public static final RegistryObject<AntiPersonalFireworkLauncher> ANTI_PERSONAL_FIREWORK_LAUNCHER = ITEMS.register("anti_personal_firework_launcher",()-> new AntiPersonalFireworkLauncher(new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));
    public static final RegistryObject<Chainsaw> CHAINSAW = ITEMS.register("chainsaw",()-> new Chainsaw(Tiers.NETHERITE,1F,10F,new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));
    public static final RegistryObject<Drill> DRILL = ITEMS.register("drill",()-> new Drill(Tiers.NETHERITE,6,4F,new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));
    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",()-> new BucketItem(()-> Registrations.OIL_FLUID.get(),new Item.Properties().tab(MINEFRAME_CREATIVE_TAB).durability(500)));

        //******************************FLUIDS******************************
    public static final RegistryObject<FlowingFluid> OIL_FLUID = FLUIDS.register("oil_fluid",()-> new ForgeFlowingFluid.Source(OilFluid.OIL_PROPERTIES));
    public static final RegistryObject<FlowingFluid> OIL_FLOWING = FLUIDS.register("oil_flowing",()-> new ForgeFlowingFluid.Flowing(OilFluid.OIL_PROPERTIES));
    public static final RegistryObject<LiquidBlock> OIL_BLOCK = BLOCKS.register("oil_block",()-> new LiquidBlock(()-> OIL_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER)
            .noCollission().strength(100f).noDrops()));

    //******************************BLOCKS******************************
    public static final RegistryObject<Block> SULFURIC_BLOOMTREE_LOG = BLOCKS.register("sulfuric_bloomtree_log",
            ()-> new ModFlammablePillarBlocks(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> SULFURIC_BLOOMTREE_WOOD = BLOCKS.register("sulfuric_bloomtree_wood",
            ()-> new ModFlammablePillarBlocks(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> STRIPPED_SULFURIC_BLOOMTREE_LOG = BLOCKS.register("stripped_sulfuric_bloomtree_log",
            ()-> new ModFlammablePillarBlocks(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<Block> STRIPPED_SULFURIC_BLOOMTREE_WOOD = BLOCKS.register("stripped_sulfuric_bloomtree_wood",
            ()-> new ModFlammablePillarBlocks(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> SULFURIC_BLOOMTREE_PLANKS = BLOCKS.register("sulfuric_bloomtree_planks",
            ()-> new ModFlammableBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS),true,20,60));

    public static final RegistryObject<Block> SULFURIC_BLOOMTREE_LEAVES = BLOCKS.register("sulfuric_bloomtree_leaves",
            ()-> new SulfuricBloomTreeLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> SULFURIC_BLOOMTREE_SAPLING = BLOCKS.register("sulfuric_bloomtree_sapling",
            ()-> new SaplingBlock(new SulfuricBloomtreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    //******************************BLOCK_ITEM******************************

    public static final RegistryObject<BlockItem> SULFURIC_BLOOMTREE_LOG_BI = ITEMS.register("sulfuric_bloomtree_log_bi",()->
            new BlockItem(SULFURIC_BLOOMTREE_LOG.get(), new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));

    public static final RegistryObject<BlockItem> SULFURIC_BLOOMTREE_WOOD_BI = ITEMS.register("sulfuric_bloomtree_wood_bi",()->
            new BlockItem(SULFURIC_BLOOMTREE_WOOD.get(), new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));

    public static final RegistryObject<BlockItem> STRIPPED_SULFURIC_BLOOMTREE_LOG_BI = ITEMS.register("stripped_sulfuric_bloomtree_log_bi",()->
            new BlockItem(STRIPPED_SULFURIC_BLOOMTREE_LOG.get(), new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));

    public static final RegistryObject<BlockItem> STRIPPED_SULFURIC_BLOOMTREE_WOOD_BI = ITEMS.register("stripped_sulfuric_bloomtree_wood_bi",()->
            new BlockItem(STRIPPED_SULFURIC_BLOOMTREE_WOOD.get(), new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));

    public static final RegistryObject<BlockItem> SULFURIC_BLOOMTREE_PLANKS_BI = ITEMS.register("sulfuric_bloomtree_planks_bi",()->
            new BlockItem(SULFURIC_BLOOMTREE_PLANKS.get(), new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));

    public static final RegistryObject<BlockItem> SULFURIC_BLOOMTREE_LEAVES_BI = ITEMS.register("sulfuric_bloomtree_leaves_bi",()->
            new BlockItem(SULFURIC_BLOOMTREE_LEAVES.get(), new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));

    public static final RegistryObject<BlockItem> SULFURIC_BLOOMTREE_SAPLING_BI = ITEMS.register("sulfuric_bloomtree_sapling_bi",()->
            new BlockItem(SULFURIC_BLOOMTREE_SAPLING.get(), new Item.Properties().tab(MINEFRAME_CREATIVE_TAB)));

    //******************************EFFECTS******************************
    public static final RegistryObject<MobEffect> HEAVY_WEIGHT = EFFECTS.register("zen_poison_effect", () -> new HeavyWeight(MobEffectCategory.HARMFUL,0x0d8c2d));


    //******************************SOUNDS******************************
    public static final RegistryObject<SoundEvent> AUTOLOADER_SOUND = SOUNDS.register("clip_crossbow_autoloader",
            ()-> new SoundEvent(new ResourceLocation(MODID,"item.clip_crossbow.ambient")));
    public static final RegistryObject<SoundEvent> RELOAD_SOUND = SOUNDS.register("clip_crossbow_reload",
            ()-> new SoundEvent(new ResourceLocation(MODID,"item.clip_crossbow_reload.ambient")));

    // ******************************ENTITIES******************************

    // ******************************BIOMES******************************
    public static RegistryObject<Biome> ALLIUM_FIELDS = BIOMES.register("allium_fields", AlliumFields::alliumFields);
    public static RegistryObject<Biome> DARK_SEA_BIOME = BIOMES.register("dark_sea_biome", DarkSea::darkSea);

    //******************************PARTICLES******************************
    //public static final RegistryObject<ParticleType<?>> NEON_BLUE_PARTICLE = PARTICLES.register("neon_blue_particle",()->new ParticleType<ParticleStatus>)


    
}
