package ml.pkom.mcpitanlib.api.base;

import net.minecraft.item.ItemGroup;
public abstract class ItemGroupExt extends ItemGroup {

    private String id = null;
    private int index = 0;

    public ItemGroupExt(int index, String id) {
        super(index, id);
        this.id = id;
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }
}
