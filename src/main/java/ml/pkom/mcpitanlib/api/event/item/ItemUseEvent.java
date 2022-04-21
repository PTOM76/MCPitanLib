package ml.pkom.mcpitanlib.api.event.item;

import ml.pkom.mcpitanlib.api.entity.Player;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemUseEvent {
    World world;
    PlayerEntity player;
    Hand hand;

    public ItemUseEvent(World world, PlayerEntity player, Hand hand) {
        this.world = world;
        this.player = player;
        this.hand = hand;
    }

    public World getWorld() {
        return world;
    }

    public PlayerEntity getPlayerEntity() {
        return player;
    }

    public Player getPlayer() {
        return new Player(getPlayerEntity());
    }

    public Hand getHand() {
        return hand;
    }
}
