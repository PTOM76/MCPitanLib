package ml.pkom.mcpitanlib.api.item;

import ml.pkom.mcpitanlib.api.event.block.ActionResultType;
import ml.pkom.mcpitanlib.api.event.item.ItemUseEvent;
import ml.pkom.mcpitanlib.api.event.item.ItemUseOnBlockEvent;
import ml.pkom.mcpitanlib.api.util.IdentifierExt;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemExt extends Item {

    // アイテムID
    private String itemId;
    private ItemSettingsExt settings;

    public ItemExt(String id, ItemSettingsExt settings) {
        super(settings);
        this.itemId = id;
        this.settings = settings;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return onRightClick(new ItemUseEvent(world, user, hand)).getTypedActionResult(user.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return onRightClickOnBlock(ItemUseOnBlockEvent.of(context)).getActionResult();
    }

    /**
     * item right click event
     * @param event ItemUseEvent
     * @return ActionResultType
     */
    public ActionResultType onRightClick(ItemUseEvent event) {
        return superOnRightClick(event);
    }

    /**
     * item right click event on block
     * @param event ItemUseOnBlockEvent
     * @return ActionResultType
     */
    public ActionResultType onRightClickOnBlock(ItemUseOnBlockEvent event) {
        return superOnRightClickOnBlock(event);
    }

    /**
     * super.use
     */
    public ActionResultType superOnRightClick(ItemUseEvent event) {
        return ActionResultType.of(super.use(event.getWorld(), event.getPlayerEntity(), event.getHand()).getResult());
    }

    /**
     * super.useOnBlock
     */
    public ActionResultType superOnRightClickOnBlock(ItemUseOnBlockEvent event) {
        return ActionResultType.of(super.useOnBlock(event.toIUC()));
    }

    public ItemExt getItemExt() {
        return this;
    }

    // Id関連
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public IdentifierExt getItemIdAsIdentifierExt() {
        return new IdentifierExt(getItemId());
    }

    public IdentifierExt getItemIdAsIdentifierExt(String MOD_ID) {
        return new IdentifierExt(MOD_ID, getItemId());
    }

    // Settings
    public ItemSettingsExt getSettingsExt() {
        return settings;
    }

    public void setSettingsExt(ItemSettingsExt settings) {
        this.settings = settings;
    }
}
