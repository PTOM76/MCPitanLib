package ml.pkom.mcpitanlib.api.util;

import ml.pkom.mcpitanlib.api.biome.BiomeType;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class BiomeTypeUtils {
    public static List<BiomeType> biomesToBiomeTypes(List<Biome> biomes) {
        List<BiomeType> list = new ArrayList<>();
        for (Biome biome : biomes) {
            list.add(new BiomeType(biome));
        }
        return list;
    }

    public static List<BiomeType> registryKeysToBiomeTypes(List<RegistryKey<Biome>> biomes) {
        List<BiomeType> list = new ArrayList<>();
        for (RegistryKey<Biome> biome : biomes) {
            list.add(new BiomeType(biome));
        }
        return list;
    }
}
