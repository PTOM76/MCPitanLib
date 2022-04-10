package ml.pkom.mcpitanlib.api.base.block;

import ml.pkom.mcpitanlib.api.event.TileCreateEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class BlockEntityExt extends BlockEntity {

    public BlockEntityExt(BlockEntityType<?> type) {
        super(type, null, null);
    }

    public BlockEntityExt(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public BlockEntityExt(BlockEntityType<?> type, TileCreateEvent event) {
        super(type, event.getBlockPos(), event.getBlockState());
    }
}
