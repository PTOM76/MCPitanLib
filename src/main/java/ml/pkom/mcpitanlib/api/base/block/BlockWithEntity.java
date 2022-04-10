package ml.pkom.mcpitanlib.api.base.block;

import ml.pkom.mcpitanlib.api.event.TileCreateEvent;
import ml.pkom.mcpitanlib.api.base.item.ItemSettingsExt;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWithEntity extends BlockExt implements BlockEntityProviderExt {
    public BlockWithEntity(String id, BlockSettingsExt settings, ItemGroup itemGroup) {
        super(id, settings, itemGroup);
    }

    public BlockWithEntity(String id, BlockSettingsExt settings, ItemSettingsExt itemSettings) {
        super(id, settings, itemSettings);
    }

    public BlockWithEntity(String id, BlockSettingsExt settings) {
        super(id, settings);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public BlockEntityExt createBlockEntity(TileCreateEvent event) {
        return null;
    }

    @SuppressWarnings("deprecation")
    public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
        super.onSyncedBlockEvent(state, world, pos, type, data);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity != null && blockEntity.onSyncedBlockEvent(type, data);
    }

    @SuppressWarnings("deprecation")
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
    }

    public static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> checkType(BlockEntityType<A> givenType, BlockEntityType<E> expectedType, BlockEntityTicker<? super E> ticker) {
        return expectedType == givenType ? (BlockEntityTicker<A>) ticker : null;
    }
}
