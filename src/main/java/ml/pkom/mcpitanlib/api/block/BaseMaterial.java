package ml.pkom.mcpitanlib.api.block;

import net.minecraft.block.Material;

public class BaseMaterial {
    private Material material;

    public BaseMaterial(Material material) {
        setMaterial(material);
    }

    public static BaseMaterial of(Material material) {
        return new BaseMaterial(material);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
