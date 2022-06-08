package ml.pkom.mcpitanlib.api.block;

import ml.pkom.mcpitanlib.api.event.ScreenHandlerCreateEvent;
import ml.pkom.mcpitanlib.api.event.block.ActionResultType;
import ml.pkom.mcpitanlib.api.event.block.BlockUseEvent;
import ml.pkom.mcpitanlib.api.item.CreativeTab;
import ml.pkom.mcpitanlib.api.item.ItemSettingsExt;
import ml.pkom.mcpitanlib.api.text.TextUtil;
import ml.pkom.mcpitanlib.api.util.IdentifierExt;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlockExt extends Block {

    // アイテムID
    private String blockId;

    private ItemSettingsExt itemSettings = new ItemSettingsExt();
    private BlockSettingsExt blockSettings;

    public BlockExt(String id, BlockSettingsExt settings, ItemGroup itemGroup) {
        this(id, settings);
        setGroupTab(itemGroup);
    }

    public BlockExt(String id, BlockSettingsExt settings, CreativeTab creativeTab) {
        this(id, settings);
        setCreativeTab(creativeTab);
    }

    public BlockExt(String id, BlockSettingsExt settings, ItemSettingsExt itemSettings) {
        this(id, settings);
        setItemSettings(itemSettings);
    }

    public BlockExt(String id, BlockSettingsExt settings) {
        super(settings);
        this.blockId = id;
        this.blockSettings = settings;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return onRightClick(new BlockUseEvent(state, world, pos, player, hand, hit)).getActionResult();
    }

    /**
     * block right click event
     * @param event ActionResultType
     * @return BlockUseEvent
     */
    public ActionResultType onRightClick(BlockUseEvent event) {
        return superOnRightClick(event);
    }

    /**
     * super.onUse
     */
    public ActionResultType superOnRightClick(BlockUseEvent event) {
        return ActionResultType.of(super.onUse(event.getBlockState(), event.getWorld(), event.getBlockPos(), event.getPlayerEntity(), event.getHand(), event.getBlockHit()));
    }

    public BlockSettingsExt getBlockSettings() {
        return blockSettings;
    }

    public BlockExt getBlockExt() {
        return this;
    }

    // Id関連
    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public IdentifierExt getblockIdAsIdentifierExt() {
        return new IdentifierExt(getBlockId());
    }

    public IdentifierExt getblockIdAsIdentifierExt(String MOD_ID) {
        return new IdentifierExt(MOD_ID, getBlockId());
    }

    // アイテムブロック関連
    public void setGroupTab(ItemGroup groupTab) {
        itemSettings.setItemGroup(groupTab);
    }

    public void setGroupTab(CreativeTab groupTab) {
        itemSettings.setItemGroup(groupTab);
    }

    public void setCreativeTab(CreativeTab groupTab) {
        setGroupTab(groupTab);
    }


    public void setItemSettings(ItemSettingsExt itemSettings) {
        this.itemSettings = itemSettings;
    }

    public ItemSettingsExt getItemSettings() {
        return itemSettings;
    }

    @Nullable
    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                createScreenHandler(new ScreenHandlerCreateEvent(state, world, pos, syncId, inventory, player)), getTitle()
        );
    }

    @Nullable
    public ScreenHandler createScreenHandler(ScreenHandlerCreateEvent event) {
        return null;
    }

    @Nullable
    public Text getTitle() {
        return TextUtil.literal("");
    }
}
