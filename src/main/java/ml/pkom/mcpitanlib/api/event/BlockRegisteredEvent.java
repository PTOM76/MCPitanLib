package ml.pkom.mcpitanlib.api.event;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

import javax.annotation.Nullable;

public class BlockRegisteredEvent extends RegisteredEvent {

    public BlockRegisteredEvent() {
        super("block");
    }

    public BlockRegisteredEvent(Block block, Item item) {
        this();
        setBlock(block);
        setItem(item);
    }

    public BlockRegisteredEvent(Block block) {
        this();
        setBlock(block);
    }

    public BlockRegisteredEvent(Item item) {
        this();
        setItem(item);
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
}
