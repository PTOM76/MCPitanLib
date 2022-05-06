package ml.pkom.mcpitanlib.api.event;

import net.minecraft.block.entity.BlockEntityType;

public class BlockEntityRegisteredEvent extends AbstractRegisteredEvent {

    private BlockEntityType<?> blockEntityType;

    public BlockEntityRegisteredEvent() {
        super("blockentity");
    }

    public BlockEntityRegisteredEvent(BlockEntityType<?> blockEntityType) {
        this();
        setBlockEntityType(blockEntityType);
    }

    public boolean hasBlockEntityType() {
        return blockEntityType != null;
    }

    public BlockEntityType<?> getBlockEntityType() {
        return blockEntityType;
    }

    public void setBlockEntityType(BlockEntityType<?> blockEntity) {
        this.blockEntityType = blockEntity;
    }
}
