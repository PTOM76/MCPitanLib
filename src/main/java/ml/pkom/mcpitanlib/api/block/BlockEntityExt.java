package ml.pkom.mcpitanlib.api.block;

import ml.pkom.mcpitanlib.api.nbt.NbtTag;
import ml.pkom.mcpitanlib.api.event.TileCreateEvent;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public abstract class BlockEntityExt extends BlockEntity {

    public BlockEntityExt(BlockEntityType<?> type) {
        super(type, null, null);
    }

    public BlockEntityExt(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public BlockEntityExt(BlockEntityType<?> type, TileCreateEvent event) {
        super(type, event.getBlockPos(), event.getBlockState());
    }

    // 互換性用 (NbtTag型をOverrideすること)
    public void writeNbt(NbtTag nbt) {
        super.writeNbt(nbt);
    }

    public void readNbt(NbtTag nbt) {
        super.readNbt(nbt);
    }

    // 1.18
    @Override
    public void writeNbt(NbtCompound nbt) {
        this.writeNbt(NbtTag.from(nbt));
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        this.readNbt(NbtTag.from(nbt));
    }

    // 1.14
    public NbtCompound toTag(NbtCompound nbt) {
        this.writeNbt(NbtTag.from(nbt));
        return nbt;
    }

    public NbtCompound fromTag(NbtCompound nbt) {
        this.readNbt(NbtTag.from(nbt));
        return nbt;
    }
}
