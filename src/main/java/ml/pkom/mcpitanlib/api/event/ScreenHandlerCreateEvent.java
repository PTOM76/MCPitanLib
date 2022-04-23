package ml.pkom.mcpitanlib.api.event;

import ml.pkom.mcpitanlib.api.entity.Player;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ScreenHandlerCreateEvent {
    private BlockState state;
    private World world;
    private BlockPos pos;
    private int syncId;
    private PlayerInventory playerInventory;
    private Player player;

    public ScreenHandlerCreateEvent(BlockState state, World world, BlockPos pos, int syncId, PlayerInventory playerInventory, Player player) {
        setState(state);
        setWorld(world);
        setPos(pos);
        setSPP(syncId, playerInventory, player);
    }

    public ScreenHandlerCreateEvent(BlockState state, World world, BlockPos pos, int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        setState(state);
        setWorld(world);
        setPos(pos);
        setSPP(syncId, playerInventory, new Player(player));
    }

    public ScreenHandlerCreateEvent(int syncId, PlayerInventory playerInventory, Player player) {
        setSPP(syncId, playerInventory, player);
    }

    public ScreenHandlerCreateEvent(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        setSPP(syncId, playerInventory, new Player(player));
    }

    private void setSPP(int syncId, PlayerInventory playerInventory, Player player) {
        setSyncId(syncId);
        setPlayerInventory(playerInventory);
        setPlayer(player);
    }

    public BlockState getState() {
        return state;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public int getSyncId() {
        return syncId;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public void setState(BlockState state) {
        this.state = state;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public void setSyncId(int syncId) {
        this.syncId = syncId;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPlayerInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }
}
