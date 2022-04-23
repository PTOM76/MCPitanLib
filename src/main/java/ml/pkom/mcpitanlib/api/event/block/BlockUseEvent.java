package ml.pkom.mcpitanlib.api.event.block;

import ml.pkom.mcpitanlib.api.entity.Player;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUseEvent {
    BlockState state;
    World world;
    BlockPos pos;
    PlayerEntity player;
    Hand hand;
    BlockHitResult hit;

    public BlockUseEvent(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.player = player;
        this.hand = hand;
        this.hit = hit;
    }

    public BlockState getBlockState() {
        return state;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getBlockPos() {
        return pos;
    }

    public PlayerEntity getPlayerEntity() {
        return player;
    }

    public Hand getHand() {
        return hand;
    }

    public BlockHitResult getBlockHit() {
        return hit;
    }

    public Player getPlayer() {
        return new Player(player);
    }

    public Block getBlock() {
        return getBlockState().getBlock();
    }

    public ItemStack getStackInHand() {
        return getPlayerEntity().getStackInHand(getHand());
    }

    public Item getItemInHand() {
        return getStackInHand().getItem();
    }

    public boolean isClient() {
        return getWorld().isClient();
    }
}
