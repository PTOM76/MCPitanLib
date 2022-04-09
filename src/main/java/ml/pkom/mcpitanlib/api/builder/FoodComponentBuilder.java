package ml.pkom.mcpitanlib.api.builder;

import net.minecraft.item.FoodComponent;

public class FoodComponentBuilder extends FoodComponent.Builder {
    public FoodComponentBuilder() {

    }

    public FoodComponent getFoodComponent() {
        return super.build();
    }
}
