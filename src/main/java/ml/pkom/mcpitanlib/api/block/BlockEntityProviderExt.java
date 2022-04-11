package ml.pkom.mcpitanlib.api.block;

import ml.pkom.mcpitanlib.api.event.TileCreateEvent;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public interface BlockEntityProviderExt extends BlockEntityProvider {
    @Nullable
    default BlockEntityExt createBlockEntity(BlockPos pos, BlockState state) {
        return createBlockEntity(new TileCreateEvent(pos, state));
    }

    @Nullable
    default BlockEntityExt createBlockEntity(BlockView blockView) {
        return createBlockEntity(new TileCreateEvent(blockView));
    }

    /**
     * @param event TileCreateEvent (contains BlockView or BlockPos, BlockState)
     * @return BlockEntity
     */
    BlockEntityExt createBlockEntity(TileCreateEvent event);
}
