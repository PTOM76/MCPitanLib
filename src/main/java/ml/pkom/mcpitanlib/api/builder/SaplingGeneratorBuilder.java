package ml.pkom.mcpitanlib.api.builder;

import ml.pkom.mcpitanlib.api.event.FeatureRegisteredEvent;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class SaplingGeneratorBuilder {
    public static SaplingGenerator create(ConfiguredFeature<?, ?> feature) {
        return new SaplingGenerator() {
            @Nullable
            @Override
            protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
                return getEntry(feature);
            }
        };
    }

    public static SaplingGenerator create(FeatureRegisteredEvent event) {
        return create(event.getConfiguredFeature());
    }

    private static RegistryEntry<ConfiguredFeature<?, ?>> getEntry(ConfiguredFeature<?, ?> value) {
        return BuiltinRegistries.CONFIGURED_FEATURE.getEntry(BuiltinRegistries.CONFIGURED_FEATURE.getKey(value).orElseThrow()).orElseThrow();
    }
}
