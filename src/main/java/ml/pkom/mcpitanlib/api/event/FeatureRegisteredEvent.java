package ml.pkom.mcpitanlib.api.event;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

import javax.annotation.Nullable;

public class FeatureRegisteredEvent extends RegisteredEvent {

    public FeatureRegisteredEvent() {
        super("feature");
    }

    public FeatureRegisteredEvent(ConfiguredFeature<?, ?> configuredFeature, PlacedFeature placedFeature) {
        this();
        setConfiguredFeature(configuredFeature);
        setPlacedFeature(placedFeature);
    }

    public FeatureRegisteredEvent(ConfiguredFeature<?, ?> configuredFeature) {
        this();
        setConfiguredFeature(configuredFeature);
    }

    public FeatureRegisteredEvent(PlacedFeature placedFeature) {
        this();
        setPlacedFeature(placedFeature);
    }

    @Override
    public boolean hasBlock() {
        return false;
    }

    @Override
    public boolean hasItem() {
        return false;
    }

    @Nullable
    @Override
    public Block getBlock() {
        return null;
    }

    @Nullable
    @Override
    public Item getItem() {
        return null;
    }
}
