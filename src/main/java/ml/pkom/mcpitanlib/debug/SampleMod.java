package ml.pkom.mcpitanlib.debug;

import ml.pkom.mcpitanlib.api.base.block.BlockExt;
import ml.pkom.mcpitanlib.api.base.block.BlockSettingsExt;
import ml.pkom.mcpitanlib.api.base.tag.MineableToolTags;
import ml.pkom.mcpitanlib.api.base.util.IdentifierExt;
import ml.pkom.mcpitanlib.api.base.item.ItemGroupExt;
import ml.pkom.mcpitanlib.api.register.Registries;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;

public class SampleMod {
    public static String MOD_ID = "samplemod";

    public static void init() {
        Registries.registerBlock(new IdentifierExt(MOD_ID, "sample_block"), new BlockExt(MOD_ID + "sample_block", new BlockSettingsExt(Material.STONE, MapColor.BLUE).breakByTool(MineableToolTags.AXE, 0).setHardness(2f), ItemGroupExt.MISC));
    }
}
