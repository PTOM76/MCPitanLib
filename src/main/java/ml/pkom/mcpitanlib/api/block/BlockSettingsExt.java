package ml.pkom.mcpitanlib.api.block;

import ml.pkom.mcpitanlib.api.tag.MineableToolTags;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.mixin.object.builder.AbstractBlockAccessor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;

public class BlockSettingsExt extends FabricBlockSettings {

    private MineableToolTags mineableToolTags = MineableToolTags.NONE;
    private int mineableLevel = 0;

    public BlockSettingsExt(Material material, MapColor color) {
        super(material, color);
    }

    public BlockSettingsExt(BaseMaterial material, BaseMaterialColor color) {
        super(material.getMaterial(), color.getMapColor());
    }

    public BlockSettingsExt(BaseMaterial material) {
        super(material.getMaterial(), material.getMaterial().getColor());
    }

    public BlockSettingsExt(AbstractBlock.Settings settings) {
        super(settings);
    }

    public BlockSettingsExt(FabricBlockSettings settings) {
        super(settings);
    }

    public static BlockSettingsExt copy(AbstractBlock block) {
        return new BlockSettingsExt(((AbstractBlockAccessor)block).getSettings());
    }

    public BlockSettingsExt setLuminance(int luminance) {
        return (BlockSettingsExt) super.luminance(luminance);
    }

    public BlockSettingsExt setDropTable(Identifier dropTableId) {
        return (BlockSettingsExt) super.drops(dropTableId);
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
