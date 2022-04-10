package ml.pkom.mcpitanlib.api.base.block;

import ml.pkom.mcpitanlib.api.base.tag.MineableToolTags;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;

public class BlockSettingsExt extends FabricBlockSettings {

    private MineableToolTags mineableToolTags = MineableToolTags.NONE;
    private int mineableLevel = 0;

    public BlockSettingsExt(Material material, MapColor color) {
        super(material, color);
    }

    public BlockSettingsExt(AbstractBlock.Settings settings) {
        super(settings);
    }

    public BlockSettingsExt setHardness(float hardness) {
        return (BlockSettingsExt) super.hardness(hardness);
    }

    public BlockSettingsExt setResistance(float resistance) {
        return (BlockSettingsExt) super.resistance(resistance);
    }

    public BlockSettingsExt setStrength(float strength) {
        return (BlockSettingsExt) super.strength(strength);
    }

    public BlockSettingsExt setStrength(float hardness, float resistance) {
        return (BlockSettingsExt) super.strength(hardness, resistance);
    }

    public BlockSettingsExt changeRequiresTool() {
        return (BlockSettingsExt) super.requiresTool();
    }

    public BlockSettingsExt breakByTool(MineableToolTags toolTags) {
        breakByTool(toolTags, 0);
        return this;
    }

    public BlockSettingsExt breakByTool(MineableToolTags toolTags, int level) {
        this.mineableToolTags = toolTags;
        this.mineableLevel = level;
        return this;
    }

    public int getMineableLevel() {
        return mineableLevel;
    }

    public MineableToolTags getMineableToolTags() {
        return mineableToolTags;
    }
}
