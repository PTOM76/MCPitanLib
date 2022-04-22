package ml.pkom.mcpitanlib.api.block;

import net.minecraft.block.MapColor;

public class BaseMaterialColor {
    //MapColor
    MapColor mapColor;

    public BaseMaterialColor(MapColor mapColor) {
        setMapColor(mapColor);
    }

    public static BaseMaterialColor of(MapColor mapColor) {
        return new BaseMaterialColor(mapColor);
    }

    public MapColor getMapColor() {
        return mapColor;
    }

    public void setMapColor(MapColor mapColor) {
        this.mapColor = mapColor;
    }
}
