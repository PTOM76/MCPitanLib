package ml.pkom.mcpitanlib.api.gui;

import ml.pkom.mcpitanlib.api.entity.Player;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;

import java.util.ArrayList;
import java.util.List;

public class GuiScreenHandler extends ScreenHandler {

    public Inventory inventory;

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public ScreenHandlerType<?> type = null;
    public boolean canUse = true;

    public static ScreenHandlerType<?> getStaticScreenHandlerType() {
        GuiScreenHandler gui = new GuiScreenHandler(-1, null);
        return gui.getScreenHandlerType();
    }

    public ScreenHandlerType<?> getScreenHandlerType() {
        return type;
    }

    public GuiScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(27));
    }

    public GuiScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(getStaticScreenHandlerType(), syncId);
        this.inventory = inventory;
    }

    @Deprecated
    @Override
    public boolean canUse(PlayerEntity player) {
        return canUse(new Player(player));
    }

    public boolean canUse(Player player) {
        return this.canUse;
    }

    @Override
    protected Slot addSlot(Slot slot) {
        return super.addSlot(slot);
    }

    protected Slot addNormalSlot(Inventory inventory, int index, int x, int y) {
        Slot slot = new Slot(inventory, index, x, y);
        return this.addSlot(slot);
    }

    public static final int DEFAULT_SLOT_SIZE = 18;

    /**
     * Add player main inventory slots
     * @param inventory target player inventory
     * @param x start x
     * @param y start y
     */
    protected List<Slot> addPlayerMainInventorySlots(PlayerInventory inventory, int x, int y) {
        return this.addSlots(inventory, 9, x, y, DEFAULT_SLOT_SIZE, 9, 3);
    }

    /**
     * Add player hotbar slots
     * @param inventory target player inventory
     * @param x start x
     * @param y start y
     */
    protected List<Slot> addPlayerHotbarSlots(PlayerInventory inventory, int x, int y) {
        return this.addSlotsX(inventory, 0, x, y, DEFAULT_SLOT_SIZE, 9);
    }

    /**
     * 一括でスロットを設置する
     * @param inventory target inventory
     * @param firstIndex fisrt index
     * @param firstX first x
     * @param firstY first y
     * @param size a slot size (if this is -1, set 18 to this)
     * @param maxAmountX x line slot max amount
     * @param maxAmountY y line slot max amount
     * @return Slot list
     */
    protected List<Slot> addSlots(Inventory inventory, int firstIndex, int firstX, int firstY, int size, int maxAmountX, int maxAmountY) {
        if (size < 0) size = DEFAULT_SLOT_SIZE;
        List<Slot> slots = new ArrayList<>();
        for (int y = 0; y < maxAmountY; ++y) {
            List<Slot> xSlots = this.addSlotsX(inventory, firstIndex + (y * maxAmountX), firstX, firstY + (y * size), size, maxAmountX);
            slots.addAll(xSlots);
        }
        return slots;
    }

    /**
     * 一括で横にスロットを設置する
     * @param inventory target inventory
     * @param firstIndex first index
     * @param firstX first x
     * @param y y
     * @param size a slot size (if this is -1, set 18 to this)
     * @param amount slot amount
     * @return Slot list
     */
    protected List<Slot> addSlotsX(Inventory inventory, int firstIndex, int firstX, int y, int size, int amount) {
        if (size < 0) size = DEFAULT_SLOT_SIZE;
        List<Slot> slots = new ArrayList<>();
        for (int x = 0; x < amount; ++x) {
            Slot slot = this.addNormalSlot(inventory, firstIndex + x, firstX + (x * size), y);
            slots.add(slot);
        }
        return slots;
    }

    /**
     * 一括で縦にスロットを設置する
     * @param inventory target inventory
     * @param firstIndex first index
     * @param x x
     * @param firstY first y
     * @param size a slot size (if this is -1, set 18 to this)
     * @param amount slot amount
     * @return Slot list
     */
    protected List<Slot> addSlotsY(Inventory inventory, int firstIndex, int x, int firstY, int size, int amount) {
        if (size < 0) size = DEFAULT_SLOT_SIZE;
        List<Slot> slots = new ArrayList<>();
        for (int y = 0; y < amount; ++y) {
            Slot slot = this.addNormalSlot(inventory, firstIndex + x, x, firstY + (y * size));
            slots.add(slot);
        }
        return slots;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (index < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
}
