package ml.pkom.mcpitanlib.api.event;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;

public abstract class RegisteredEvent {
    private Item item;
    private Block block;

    private ConfiguredFeature<?, ?> configuredFeature;

    private PlacedFeature placedFeature;

    private String type;

    public RegisteredEvent(String type) {

    }

    public boolean hasItem() {
        return this.item != null;
    }

    public boolean hasBlock() {
        return this.block != null;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public boolean hasConfiguredFeature() {
        return this.configuredFeature != null;
    }

    public boolean hasPlacedFeature() {
        return this.placedFeature != null;
    }

    public void setConfiguredFeature(ConfiguredFeature<?, ?> configuredFeature) {
        this.configuredFeature = configuredFeature;
    }

    public ConfiguredFeature<?, ?> getConfiguredFeature() {
        return configuredFeature;
    }

    public void setPlacedFeature(PlacedFeature placedFeature) {
        this.placedFeature = placedFeature;
    }

    public PlacedFeature getPlacedFeature() {
        return placedFeature;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
