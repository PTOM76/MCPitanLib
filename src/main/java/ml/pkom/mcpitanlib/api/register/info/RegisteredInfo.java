package ml.pkom.mcpitanlib.api.register.info;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class RegisteredInfo {
    private Item item;
    private Block block;

    private String type;

    public RegisteredInfo(String type) {

    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
