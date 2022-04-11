package ml.pkom.mcpitanlib.api.tag;

import ml.pkom.mcpitanlib.api.util.IdentifierExt;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public class DataTagRegistry {
    public static DataTag block(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.BLOCK_KEY, id));
    }

    public static DataTag item(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.ITEM_KEY, id));
    }

    public static DataTag entity(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.ENTITY_TYPE_KEY, id));
    }

    public static DataTag tile(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.BLOCK_ENTITY_TYPE_KEY, id));
    }

    public static DataTag biome(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.BIOME_KEY, id));
    }

    public static DataTag fluid(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.FLUID_KEY, id));
    }

    public static DataTag activity(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.ACTIVITY_KEY, id));
    }

    public static DataTag attribute(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.ATTRIBUTE_KEY, id));
    }

    public static DataTag biomeSource(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.BIOME_SOURCE_KEY, id));
    }

    public static DataTag chunkGenerator(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.CHUNK_GENERATOR_KEY, id));
    }

    public static DataTag chunk_status(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.CHUNK_STATUS_KEY, id));
    }

    public static DataTag carver(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.CARVER_KEY, id));
    }

    public static DataTag dimension(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.DIMENSION_KEY, id));
    }
    public static DataTag feature(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.FEATURE_KEY, id));
    }

    public static DataTag world(IdentifierExt id) {
        return new DataTag(TagKey.of(Registry.WORLD_KEY, id));
    }
}
