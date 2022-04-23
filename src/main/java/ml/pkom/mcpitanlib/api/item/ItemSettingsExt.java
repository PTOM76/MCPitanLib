package ml.pkom.mcpitanlib.api.item;

import ml.pkom.mcpitanlib.api.builder.FoodComponentBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import org.jetbrains.annotations.Nullable;

public class ItemSettingsExt extends Item.Settings {
    public ItemSettingsExt() {

    }

    public ItemSettingsExt setMaxCount(int maxCount) {
        return (ItemSettingsExt) this.maxCount(maxCount);
    }

    public ItemSettingsExt setMaxDamage(int maxDamage) {
        return (ItemSettingsExt) this.maxDamage(maxDamage);
    }

    public ItemSettingsExt setItemGroup(@Nullable ItemGroup creativeTab) {
        return (ItemSettingsExt) this.group(creativeTab);
    }

    public ItemSettingsExt setItemGroup(@Nullable CreativeTab creativeTab) {
        return setCreativeTab(creativeTab);
    }

    public ItemSettingsExt setCreativeTab(@Nullable CreativeTab creativeTab) {
        return setItemGroup(creativeTab.getCreativeTab());
    }

    public ItemSettingsExt makeNormalFood(int hunger, float saturationModifier) {
        return makeFood(hunger, saturationModifier, false, false);
    }

    public ItemSettingsExt makeFood(int hunger, float saturationModifier, boolean isSnack, boolean isMeat) {
        FoodComponentBuilder builder = new FoodComponentBuilder();
        builder.hunger(hunger);
        if (isSnack) {
            builder.snack();
        }
        if (isMeat) {
            builder.meat();
        }

        builder.saturationModifier(saturationModifier);
        return (ItemSettingsExt) super.food(builder.build());
    }
}
