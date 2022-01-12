package com.lb.mineframe.world.gen;

import com.lb.mineframe.setups.Registrations;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static net.minecraftforge.common.BiomeManager.BiomeType.COOL;
import static net.minecraftforge.common.BiomeManager.BiomeType.WARM;

public class ModBiomeGen {
    public static void generateBiomes(){
        addBiome(Registrations.ALLIUM_FIELDS.get(),WARM,20, COLD, DENSE, WET);
        addBiome(Registrations.DARK_SEA_BIOME.get(),COOL,20,WET);
    }

    private static void addBiome(Biome biome, BiomeManager.BiomeType type, int weight , BiomeDictionary.Type... types){
        ResourceKey<Biome> key = ResourceKey.create(ForgeRegistries.Keys.BIOMES,Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome)));

        BiomeDictionary.addTypes(key,types);
        BiomeManager.addBiome(type,new BiomeManager.BiomeEntry(key,weight));
    }
}
