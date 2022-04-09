package ml.pkom.mcpitanlib.api.base;

import net.minecraft.item.Item;

public class ItemExt extends Item {

    // アイテムID
    private String itemId;
    private ItemSettingsExt settings;

    public ItemExt(String id, ItemSettingsExt settings) {
        super(settings);
        this.itemId = id;
        this.settings = settings;
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
