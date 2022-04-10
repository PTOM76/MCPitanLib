package ml.pkom.mcpitanlib.api.register;

import ml.pkom.mcpitanlib.api.MCPitanLib;
import ml.pkom.mcpitanlib.api.base.block.BlockEntityExt;
import ml.pkom.mcpitanlib.api.base.block.BlockExt;
import ml.pkom.mcpitanlib.api.base.entity.EntityExt;
import ml.pkom.mcpitanlib.api.base.item.ItemExt;
import ml.pkom.mcpitanlib.api.base.util.IdentifierExt;
import ml.pkom.mcpitanlib.api.event.RegisteredEvent;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
Registries class contain register methods
 */
public class Registries {
    /**
     * Register item
     * @param id Item id
     * @param item Item
     * @return Registered info
     */
    public static RegisteredEvent registerItem(IdentifierExt id, ItemExt item) {
        RegisteredEvent info = new RegisteredEvent("item");
        info.setItem(Registry.register(Registry.ITEM, id, item));
        return info;
    }

    /**
     * Register item
     * @param idStr Item id (String)
     * @param item Item
     * @return Registered info
     */
    public static RegisteredEvent registerItem(String idStr, ItemExt item) {
        return registerItem(new IdentifierExt(idStr), item);
    }

    /**
     * Register block
     * @param id Block id
     * @param block Block
     * @return Registered info
     */
    public static RegisteredEvent registerBlock(IdentifierExt id, BlockExt block) {
        return registerBlock(id, block, true);
    }

    /**
     * Register block
     * @param id Block id
     * @param block Block
     * @param doItemRegistration Whether to register items as well
     * @return Registered info
     */
    public static RegisteredEvent registerBlock(IdentifierExt id, BlockExt block, boolean doItemRegistration) {
        RegisteredEvent info = new RegisteredEvent("block");
        info.setBlock(Registry.register(Registry.BLOCK, id, block));
        if (doItemRegistration) {
            info.setItem(Registry.register(Registry.ITEM, id, new BlockItem(block, block.getItemSettings())));
        }
        return info;
    }

    /**
     * Register BlockEntity (Tile)
     * @param id BlockEntity id
     * @param blockEntity BlockEntity
     * @param blocks blocks
     * @return BlockEntityType
     */
    public static BlockEntityType<?> registerBlockEntity(IdentifierExt id, Class<? extends BlockEntityExt> blockEntity, BlockExt... blocks) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.create((FabricBlockEntityTypeBuilder.Factory<? extends BlockEntityExt>)(Supplier<BlockEntityExt>) () -> {
            try {
                return blockEntity.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }, blocks).build(null));
    }

    /**
     * Alias
     */
    public static BlockEntityType<?> registerTile(IdentifierExt id, Class<? extends BlockEntityExt> blockEntity, BlockExt... blocks) {
        return registerBlockEntity(id, blockEntity, blocks);
    }

    /**
     * Register Entity
     * @param id Entity id
     * @param entity Entity
     * @param spawnGroup Spawn group
     * @param width Entity width
     * @param height Entity height
     * @return EntityType
     */
    public static EntityType<?> registerEntity(IdentifierExt id, EntityExt entity, SpawnGroup spawnGroup, float width, float height) {
        EntityType entityType = Registry.register(Registry.ENTITY_TYPE, id, FabricEntityTypeBuilder.create(spawnGroup, (EntityType.EntityFactory<? extends EntityExt>)(Supplier<EntityExt>) () -> {
            try {
                return entity.getClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).dimensions(EntityDimensions.fixed(width, height)).build());
        return entityType;

    }

    // 自動で作られたIDを使って登録する
    public static long itemIdCount = 0;
    public static long blockEntityIdCount = 0;

    /**
     * Register BlockEntity
     * id is mcpitanlib:[number]
     * @param blockEntity BlockEntity
     * @param blocks blocks
     * @return BlockEntityType
     */
    public static BlockEntityType registerBlockEntity(Class<? extends BlockEntityExt> blockEntity, BlockExt... blocks) {
        blockEntityIdCount++;
        return registerBlockEntity(new IdentifierExt(MCPitanLib.MOD_ID, String.valueOf(blockEntityIdCount)), blockEntity, blocks);
    }

    /**
     * Register item
     * id is mcpitanlib:[number]
     * @param item Item
     * @return Registered info
     */
    // IDがmcpitanlib:(数値)になるので注意
    public static RegisteredEvent registerItem(ItemExt item) {
        itemIdCount++;
        return registerItem(new IdentifierExt(MCPitanLib.MOD_ID, String.valueOf(itemIdCount)), item);
    }

    /**
     * Register block
     * id is mcpitanlib:[number]
     * @param block Block
     * @return Registered info
     */
    // IDがmcpitanlib:(数値)になるので注意
    public static RegisteredEvent registerBlock(BlockExt block) {
        itemIdCount++;
        return registerBlock(new IdentifierExt(MCPitanLib.MOD_ID, String.valueOf(itemIdCount)), block);
    }

    /**
     * Register generate ore
     * @param block Block
     * @param size Vein size
     * @param spawnRate Spawn rate
     * @param maxHeight max height
     */
    public static void registerGenerateOreInStone(BlockExt block, int size, int spawnRate, int maxHeight) {
        registerGenerateOreInStone(new IdentifierExt(MCPitanLib.MOD_ID, Registry.ITEM.getId(block.asItem()).getPath() + "_ore"), block, size, spawnRate, maxHeight);

    }

    public static void registerGenerateOreInStone(IdentifierExt identifier, BlockExt block, int size, int spawnRate, int maxHeight) {
        ConfiguredFeature<?, ?> CONFIGURED_FEATURE = new ConfiguredFeature(Feature.ORE,
                new OreFeatureConfig(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, block.getDefaultState(), size));

        PlacedFeature PLACED_FEATURE = new PlacedFeature(RegistryEntry.of(CONFIGURED_FEATURE),
                Arrays.asList(CountPlacementModifier.of(spawnRate), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(maxHeight))));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                identifier, CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, identifier,
                PLACED_FEATURE);

        Predicate<BiomeSelectionContext> predicate = BiomeSelectors.all();
        BiomeModifications.addFeature(predicate, GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, identifier));
    }

    public static void registerGenerateOre(BlockExt block, OreFeatureConfig.Target target, int size, int spawnRate, int maxHeight) {
        registerGenerateOre(new IdentifierExt(MCPitanLib.MOD_ID, Registry.ITEM.getId(block.asItem()).getPath() + "_ore"), block, target, size, spawnRate, maxHeight);
    }

    public static void registerGenerateOre(IdentifierExt identifier, BlockExt block, OreFeatureConfig.Target target, int size, int spawnRate, int maxHeight) {
        ConfiguredFeature<?, ?> CONFIGURED_FEATURE = new ConfiguredFeature(Feature.ORE,
                new OreFeatureConfig(target.target, block.getDefaultState(), size));

        PlacedFeature PLACED_FEATURE = new PlacedFeature(RegistryEntry.of(CONFIGURED_FEATURE),
                Arrays.asList(CountPlacementModifier.of(spawnRate), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(maxHeight))));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                identifier, CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, identifier,
                PLACED_FEATURE);

        Predicate<BiomeSelectionContext> predicate = BiomeSelectors.all();
        BiomeModifications.addFeature(predicate, GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, identifier));
    }

    public static void registerGenerateOreInStone(Biome biome, BlockExt block, int size, int spawnRate, int maxHeight) {
        registerGenerateOreInStone(new IdentifierExt(MCPitanLib.MOD_ID, Registry.ITEM.getId(block.asItem()).getPath() + "_ore"), biome, block, size, spawnRate, maxHeight);
    }

    public static void registerGenerateOreInStone(IdentifierExt identifier, Biome biome, BlockExt block, int size, int spawnRate, int maxHeight) {
        ConfiguredFeature<?, ?> CONFIGURED_FEATURE = new ConfiguredFeature(Feature.ORE,
                new OreFeatureConfig(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, block.getDefaultState(), size));

        PlacedFeature PLACED_FEATURE = new PlacedFeature(RegistryEntry.of(CONFIGURED_FEATURE),
                Arrays.asList(CountPlacementModifier.of(spawnRate), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(maxHeight))));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                identifier, CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, identifier,
                PLACED_FEATURE);

        Predicate<BiomeSelectionContext> predicate = BiomeSelectors.includeByKey(BuiltinRegistries.BIOME.getKey(biome).get());
        BiomeModifications.addFeature(predicate, GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, identifier));

    }
}
