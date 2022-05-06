package ml.pkom.mcpitanlib.api.event;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

import javax.annotation.Nullable;

public abstract class AbstractRegisteredEvent extends RegisteredEvent {

    public AbstractRegisteredEvent(String type) {
        super(type.toLowerCase());
    }

    @Override
    public boolean hasBlock() {
        return false;
    }

    @Override
    public boolean hasItem() {
        return false;
    }

    @Override
    public boolean hasConfiguredFeature() {
        return false;
    }

    @Override
    public boolean hasPlacedFeature() {
        return false;
    }

    @Nullable
    @Override
    public ConfiguredFeature<?, ?> getConfiguredFeature() {
        return null;
    }

    @Nullable
    @Override
    public PlacedFeature getPlacedFeature() {
        return null;
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
