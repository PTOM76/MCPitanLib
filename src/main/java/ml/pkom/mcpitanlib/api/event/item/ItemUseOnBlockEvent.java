package ml.pkom.mcpitanlib.api.event.item;

import ml.pkom.mcpitanlib.api.entity.Player;
import ml.pkom.mcpitanlib.mixin.ItemUsageContextMixin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ItemUseOnBlockEvent {
    PlayerEntity player;
    Hand hand;
    BlockHitResult hit;
    ItemStack stack;
    World world;

    public ItemUseOnBlockEvent(PlayerEntity player, Hand hand, BlockHitResult hit) {
        this(player.world, player, hand, player.getStackInHand(hand), hit);
    }

    public ItemUseOnBlockEvent(World world, @Nullable PlayerEntity player, Hand hand, ItemStack stack, BlockHitResult hit) {
        this.player = player;
        this.hand = hand;
        this.hit = hit;
        this.stack = stack;
        this.world = world;
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

    public BlockHitResult getHit() {
        return hit;
    }

    public ItemStack getStack() {
        return stack;
    }

    public World getWorld() {
        return world;
    }

    public static ItemUseOnBlockEvent of(Object obj) {
        if (obj instanceof ItemUsageContext) {
            ItemUsageContext context = (ItemUsageContext) obj;
            ItemUsageContextMixin contextAccessor = (ItemUsageContextMixin) obj;
            return new ItemUseOnBlockEvent(context.getPlayer(), context.getHand(), contextAccessor.getHit());
        }

        return null;
    }

    public ItemUsageContext toIUC() {
        return new ItemUsageContext(getPlayerEntity(), getHand(), getHit());
    }
}
