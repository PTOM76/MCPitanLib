package ml.pkom.mcpitanlib.api.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class CreativeTabs {
    public static final CreativeTab BUILDING_BLOCKS = new CreativeTab(ItemGroup.BUILDING_BLOCKS);
    public static final CreativeTab DECORATIONS = new CreativeTab(ItemGroup.DECORATIONS);
    public static final CreativeTab REDSTONE = new CreativeTab(ItemGroup.REDSTONE);
    public static final CreativeTab TRANSPORTATION = new CreativeTab(ItemGroup.TRANSPORTATION);
    public static final CreativeTab MISC = new CreativeTab(ItemGroup.MISC);
    public static final CreativeTab SEARCH = new CreativeTab(ItemGroup.SEARCH);
    public static final CreativeTab FOOD = new CreativeTab(ItemGroup.FOOD);
    public static final CreativeTab TOOLS = new CreativeTab(ItemGroup.TOOLS);
    public static final CreativeTab COMBAT = new CreativeTab(ItemGroup.COMBAT);
    public static final CreativeTab BREWING = new CreativeTab(ItemGroup.BREWING);
    public static final CreativeTab HOTBAR = new CreativeTab(ItemGroup.HOTBAR);
    public static final CreativeTab INVENTORY = new CreativeTab(ItemGroup.INVENTORY);

    public static @Nullable CreativeTab getCreativeTab(String string) {
        string = string.toUpperCase();
        switch (string) {
            case "BUILDING_BLOCKS":
                return BUILDING_BLOCKS;
            case "DECORATIONS":
                return DECORATIONS;
            case "REDSTONE":
                return REDSTONE;
            case "TRANSPORTATION":
                return TRANSPORTATION;
            case "MISC":
                return MISC;
            case "SEARCH":
                return SEARCH;
            case "FOOD":
                return FOOD;
            case "TOOLS":
                return TOOLS;
            case "COMBAT":
                return COMBAT;
            case "BREWING":
                return BREWING;
            case "HOTBAR":
                return HOTBAR;
            case "INVENTORY":
                return INVENTORY;
            default:
                return null;
        }
    }

    public static CreativeTab create(Identifier id, @Nullable ItemStack icon) {
        return CreativeTab.create(id, icon);
    }
    public static CreativeTab create(Identifier id, @Nullable BaseItemStack icon) {
        return CreativeTab.create(id, icon);
    }
}
