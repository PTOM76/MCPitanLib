package ml.pkom.mcpitanlib.api.base;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

public class BlockExt extends Block {

    // アイテムID
    private String blockId;

    private ItemSettingsExt itemSettings = new ItemSettingsExt();

    public BlockExt(String id, BlockSettingsExt settings, ItemGroup itemGroup) {
        super(settings);
        this.blockId = id;
        setGroupTab(itemGroup);
    }

    public BlockExt(String id, BlockSettingsExt settings, ItemSettingsExt itemSettings) {
        super(settings);
        this.blockId = id;
        setItemSettings(itemSettings);
    }

    public BlockExt(String id, BlockSettingsExt settings) {
        super(settings);
        this.blockId = id;
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

    public void setItemSettings(ItemSettingsExt itemSettings) {
        this.itemSettings = itemSettings;
    }

    public ItemSettingsExt getItemSettings() {
        return itemSettings;
    }
}
