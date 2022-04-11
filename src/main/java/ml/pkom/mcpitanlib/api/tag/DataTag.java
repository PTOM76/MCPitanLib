package ml.pkom.mcpitanlib.api.tag;

import ml.pkom.mcpitanlib.api.util.IdentifierExt;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.RegistryKey;

public class DataTag {
    private final TagKey<?> tag;
    //public static final TagKey<Block> SLOPES = TagKey.of(Registry.BLOCK_KEY, Automobility.id("slopes"));

    public DataTag(TagKey<?> tag) {
        this.tag = tag;
    }

    public DataTag(RegistryKey registry, IdentifierExt identifierExt) {
        this.tag = TagKey.of(registry, identifierExt);
    }

    public TagKey<?> getTag() {
        return tag;
    }
}
