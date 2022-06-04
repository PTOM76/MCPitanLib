package ml.pkom.mcpitanlib.api.biome;

import ml.pkom.mcpitanlib.api.util.BiomeTypeUtils;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

import javax.annotation.Nullable;

public class BiomeType {

    public BiomeTypeUtils utils;

    private Biome biome = null;
    private RegistryKey<Biome> registryKey = null;

    public BiomeType() {

    }

    public BiomeType(Biome biome) {
        setBiome(biome);
    }

    public BiomeType(RegistryKey<Biome> registryKey) {
        setRegistryKey(registryKey);
    }

    public boolean biomeExists() {
        return biome != null;
    }

    public boolean registryKeyExists() {
        return registryKey != null;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public void setRegistryKey(RegistryKey<Biome> registryKey) {
        this.registryKey = registryKey;
    }

    @Nullable
    public Biome getBiome() {
        return biome;
    }

    @Nullable
    public RegistryKey<Biome> getRegistryKey() {
        if (!registryKeyExists() && biomeExists()) {
            return BuiltinRegistries.BIOME.getKey(getBiome()).get();
        }
        return registryKey;
    }
}
