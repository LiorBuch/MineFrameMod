package com.stargazer.mineframe.world.biome;

import com.mojang.datafixers.util.Pair;
import com.stargazer.mineframe.setups.Registrations;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraftforge.registries.ForgeRegistries;
import terrablender.api.BiomeProvider;
import terrablender.worldgen.TBClimate;

import java.util.List;
import java.util.function.Consumer;

public class MFBiomeProvider extends BiomeProvider {
    public MFBiomeProvider(ResourceLocation name, int weight) {
        super(name, weight);
    }

    private List<ResourceKey<Biome>> BIOME_LIST;
    private ResourceKey<Biome> ALLIUM_FIELDS_KEY;

    @Override
    public void addOverworldBiomes(Registry<Biome> registry, Consumer<Pair<TBClimate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        super.addOverworldBiomes(registry, mapper);
        ALLIUM_FIELDS_KEY = ResourceKey.create(ForgeRegistries.Keys.BIOMES, Registrations.ALLIUM_FIELDS.getId()); // biome key

        this.addBiome(mapper,new Climate.Parameter(10,14),new Climate.Parameter(99,100),new Climate.Parameter(10,11)
                ,new Climate.Parameter(0,1),new Climate.Parameter(30,40),new Climate.Parameter(60,10),7, ALLIUM_FIELDS_KEY);
    }

}
