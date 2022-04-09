package ml.pkom.mcpitanlib.debug;

import ml.pkom.mcpitanlib.api.base.BlockExt;
import ml.pkom.mcpitanlib.api.base.BlockSettingsExt;
import ml.pkom.mcpitanlib.api.base.IdentifierExt;
import ml.pkom.mcpitanlib.api.base.ItemGroupExt;
import ml.pkom.mcpitanlib.api.register.Registries;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;

public class SampleMod {
    public static String MOD_ID = "samplemod";

    public static void init() {
        Registries.registerBlock(new IdentifierExt(MOD_ID, "sample_block"), new BlockExt(MOD_ID + "sample_block", new BlockSettingsExt(Material.STONE, MapColor.BLUE).setHardness(2f), ItemGroupExt.MISC));
    }
}
