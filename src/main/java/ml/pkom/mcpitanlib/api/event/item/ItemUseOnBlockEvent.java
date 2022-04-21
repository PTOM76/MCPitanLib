package ml.pkom.mcpitanlib.api.event.item;

import ml.pkom.mcpitanlib.api.entity.Player;
import ml.pkom.mcpitanlib.mixin.ItemUsageContextMixin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
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

    public BlockPos getBlockPos() {
        return this.getHit().getBlockPos();
    }

    public Direction getSide() {
        return this.getHit().getSide();
    }

    public Vec3d getHitPos() {
        return this.getHit().getPos();
    }

    public boolean hitsInsideBlock() {
        return this.getHit().isInsideBlock();
    }

    public ItemStack getStack() {
        return stack;
    }

    public World getWorld() {
        return world;
    }

    public Direction getPlayerFacing() {
        return getPlayerEntity() == null ? Direction.NORTH : getPlayerEntity().getHorizontalFacing();
    }

    public boolean shouldCancelInteraction() {
        return getPlayerEntity() != null && getPlayerEntity().shouldCancelInteraction();
    }

    public float getPlayerYaw() {
        return getPlayerEntity() == null ? 0.0f : getPlayerEntity().getYaw();
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
