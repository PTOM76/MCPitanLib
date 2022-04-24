package ml.pkom.mcpitanlib.api.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

public class CreativeTab {
    private ItemGroup creativeTab;

    public CreativeTab(ItemGroup itemGroup) {
        this.creativeTab = itemGroup;
    }

    public static CreativeTab of(ItemGroup itemGroup) {
        return new CreativeTab(itemGroup);
    }

    public @Nullable ItemGroup getCreativeTab() {
        return creativeTab;
    }

    public void setCreativeTab(@Nullable ItemGroup creativeTab) {
        this.creativeTab = creativeTab;
    }

    public void addItemStacks(DefaultedList<ItemStack> list) {
        getCreativeTab().appendStacks(list);
    }

    public void addItemStack(ItemStack itemStack) {
        DefaultedList<ItemStack> list = DefaultedList.ofSize(1, ItemStack.EMPTY);
        list.add(itemStack);
        addItemStacks(list);
    }

    public void disableScrollbar() {
        getCreativeTab().setNoScrollbar();
    }

    public void setName(String name) {
        getCreativeTab().setName(name);
    }

    public void setTexture(String texture) {
        getCreativeTab().setTexture(texture);
    }

    public String getName() {
        return getCreativeTab().getName();
    }

    public String getTexture() {
        return getCreativeTab().getTexture();
    }

    public int getColumn() {
        return getCreativeTab().getColumn();
    }

    public int getIndex() {
        return getCreativeTab().getIndex();
    }

    public ItemStack getIcon() {
        return getCreativeTab().getIcon();
    }

    public static @Nullable CreativeTab create(Identifier id, @Nullable ItemStack icon) {
        ItemGroup itemGroup = FabricItemGroupBuilder.build(
                id,
                () -> icon);
        return new CreativeTab(itemGroup);
    }

    public static @Nullable CreativeTab create(Identifier id, @Nullable BaseItemStack icon) {
        return create(id, icon.getItemStack());
    }
}
