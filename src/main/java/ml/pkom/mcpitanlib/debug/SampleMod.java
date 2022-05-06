package ml.pkom.mcpitanlib.debug;

import ml.pkom.mcpitanlib.api.block.BlockExt;
import ml.pkom.mcpitanlib.api.block.BlockSettingsExt;
import ml.pkom.mcpitanlib.api.tag.MineableToolTags;
import ml.pkom.mcpitanlib.api.util.IdentifierExt;
import ml.pkom.mcpitanlib.api.item.ItemGroupExt;
import ml.pkom.mcpitanlib.api.register.v2.Registries;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;

public class SampleMod {
    public static String MOD_ID = "samplemod";

    public static void init() {
        Registries.registerBlock(new IdentifierExt(MOD_ID, "sample_block"), new BlockExt(MOD_ID + "sample_block", new BlockSettingsExt(Material.STONE, MapColor.BLUE).breakByTool(MineableToolTags.AXE, 0).setHardness(2f), ItemGroupExt.MISC));
    }
}
