package ml.pkom.mcpitanlib.api.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaseItemStack {
    private ItemStack itemStack;

    public static BaseItemStack of(ItemStack itemStack) {
        return new BaseItemStack(itemStack);
    }

    public BaseItemStack(Item item, int count) {
        itemStack = new ItemStack(item, count);
    }

    public BaseItemStack(Item item) {
        this(item, 1);
    }

    public BaseItemStack(ItemStack itemStack) {
        setItemStack(itemStack);
    }

    public int getCount() {
        return itemStack.getCount();
    }

    public void setCount(int count) {
        itemStack.setCount(count);
    }

    public Item getItem() {
        return itemStack.getItem();
    }

    public int getDamage() {
        return itemStack.getDamage();
    }

    public void setDamage(int damage) {
        itemStack.setDamage(damage);
    }

    public int getMaxDamage() {
        return itemStack.getMaxDamage();
    }

    public ItemStack origin() {
        return getItemStack();
    }

    public void decrement(int amount) {
        getItemStack().decrement(amount);
    }

    public void increment(int amount) {
        setCount(getCount() + amount);
    }

    public boolean isEmpty() {
        return getItemStack().isEmpty();
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
