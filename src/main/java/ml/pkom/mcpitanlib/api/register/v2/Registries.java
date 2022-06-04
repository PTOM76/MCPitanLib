package ml.pkom.mcpitanlib.api.register.v2;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ml.pkom.mcpitanlib.api.MCPitanLib;
import ml.pkom.mcpitanlib.api.biome.BiomeType;
import ml.pkom.mcpitanlib.api.block.BlockExt;
import ml.pkom.mcpitanlib.api.event.*;
import ml.pkom.mcpitanlib.api.item.ItemExt;
import ml.pkom.mcpitanlib.api.tag.MineableToolTags;
import ml.pkom.mcpitanlib.api.util.IdentifierExt;
import ml.pkom.mcpitanlib.api.util.MCPitanUtils;
import ml.pkom.mcpitanlib.api.util.RecipeManageHelper;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import javax.annotation.Nullable;
import java.util.*;
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
    public static ItemRegisteredEvent registerItem(Identifier id, Item item) {
        ItemRegisteredEvent info = new ItemRegisteredEvent();
        info.setItem(Registry.register(Registry.ITEM, id, item));
        return info;
    }

    /**
     * Register item
     * @param idStr Item id (String)
     * @param item Item
     * @return Registered info
     */
    public static ItemRegisteredEvent registerItem(String idStr, Item item) {
        return registerItem(new Identifier(idStr), item);
    }

    public static ItemRegisteredEvent registerItem(ItemExt item) {
        return registerItem(item.getItemId(), item);
    }
    
    /**
     * Register block
     * @param id Block id
     * @param block Block
     * @param doItemRegistration Whether to register items as well
     * @return Registered info
     */
    public static BlockRegisteredEvent registerBlock(Identifier id, BlockExt block, boolean doItemRegistration) {
        BlockRegisteredEvent info = new BlockRegisteredEvent();
        info.setBlock(Registry.register(Registry.BLOCK, id, block));
        if (doItemRegistration) {
            info.setItem(Registry.register(Registry.ITEM, id, new BlockItem(block, block.getItemSettings())));
        }
        registerMineable(block);
        return info;
    }

    public static BlockRegisteredEvent registerBlock(Identifier id, Block block) {
        return registerBlock(id, block, null);
    }

    /**
     * Register block and register item
     * @param id Block id
     * @param block Block
     * @param settings Item Settings
     * @return Registered info
     */
    public static BlockRegisteredEvent registerBlock(Identifier id, Block block, @Nullable Item.Settings settings) {
        BlockRegisteredEvent info = new BlockRegisteredEvent();
        info.setBlock(Registry.register(Registry.BLOCK, id, block));
        if (settings != null) {
            info.setItem(Registry.register(Registry.ITEM, id, new BlockItem(block, settings)));
        } else {
            if (block instanceof BlockExt) {
                info.setItem(Registry.register(Registry.ITEM, id, new BlockItem(block, ((BlockExt) block).getItemSettings())));
            }
        }
        if (block instanceof BlockExt) {
            registerMineable((BlockExt) block);
        }
        return info;
    }

    private static Map<Block, MiningToolEvent> mineableToolTagsMap = new HashMap<>();

    public static Map<Block, MiningToolEvent> getMineableToolTagsMap() {
        return mineableToolTagsMap;
    }

    public static boolean containsMineableToolTags(Block block) {
        return getMineableToolTagsMap().containsKey(block);
    }

    /**
     * Register mineable tag
     * @param block Block
     */
    public static void registerMineable(BlockExt block) {
        if (block.getBlockSettings().getMineableToolTags() != MineableToolTags.NONE) {
            mineableToolTagsMap.put(block, new MiningToolEvent(block.getBlockSettings().getMineableToolTags(), block.getBlockSettings().getMineableLevel()));
        }
    }

    /**
     * Register BlockEntity from BlockEntity class (Tile)
     * @param id BlockEntity id
     * @param blockEntity BlockEntity
     * @param blocks blocks
     * @return BlockEntityType
     */
    public static BlockEntityRegisteredEvent registerBlockEntity(Identifier id, Class<? extends BlockEntity> blockEntity, Block... blocks) {
        BlockEntityRegisteredEvent event = new BlockEntityRegisteredEvent();
        event.setBlockEntityType(Registry.register(Registry.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.create((FabricBlockEntityTypeBuilder.Factory<? extends BlockEntity>)(Supplier<BlockEntity>) () -> {
            try {
                return blockEntity.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }, blocks).build(null)));
        return event;
    }

    /**
     * Register BlockEntity from BlockEntityType (Tile)
     * @param id BlockEntity id
     * @param blockEntityType BlockEntityType
     * @return BlockEntityType
     */
    public static BlockEntityRegisteredEvent registerBlockEntity(Identifier id, BlockEntityType<?> blockEntityType) {
        return new BlockEntityRegisteredEvent(Registry.register(Registry.BLOCK_ENTITY_TYPE, id, blockEntityType));
    }

    /*
     * Alias
     */
    public static BlockEntityRegisteredEvent registerTile(Identifier id, BlockEntityType<?> blockEntityType) {
        return registerBlockEntity(id, blockEntityType);
    }

    /**
     * Alias
     */
    public static BlockEntityRegisteredEvent registerTile(Identifier id, Class<? extends BlockEntity> blockEntity, Block... blocks) {
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
    public static EntityRegisteredEvent registerEntity(Identifier id, Entity entity, SpawnGroup spawnGroup, float width, float height) {
        EntityType entityType = Registry.register(Registry.ENTITY_TYPE, id, FabricEntityTypeBuilder.create(spawnGroup, (EntityType.EntityFactory<? extends Entity>)(Supplier<Entity>) () -> {
            try {
                return entity.getClass().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).dimensions(EntityDimensions.fixed(width, height)).build());
        return new EntityRegisteredEvent(entityType);

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
    public static BlockEntityRegisteredEvent registerBlockEntity(Class<? extends BlockEntity> blockEntity, Block... blocks) {
        blockEntityIdCount++;
        return registerBlockEntity(new Identifier(MCPitanLib.MOD_ID, String.valueOf(blockEntityIdCount)), blockEntity, blocks);
    }

    /**
     * Register item
     * id is mcpitanlib:[number]
     * @param item Item
     * @return Registered info
     */
    // IDがmcpitanlib:(数値)になるので注意
    public static ItemRegisteredEvent registerItem(Item item) {
        itemIdCount++;
        return registerItem(new Identifier(MCPitanLib.MOD_ID, String.valueOf(itemIdCount)), item);
    }

    /**
     * Register block
     * id is mcpitanlib:[number]
     * @param block Block
     * @return Registered info
     */
    // IDがmcpitanlib:(数値)になるので注意
    public static BlockRegisteredEvent registerBlock(Block block) {
        itemIdCount++;
        return registerBlock(new Identifier(MCPitanLib.MOD_ID, String.valueOf(itemIdCount)), block);
    }

    /**
     * Register generate ore
     * @param block Block
     * @param size Vein size
     * @param spawnRate Spawn rate
     * @param maxHeight max height
     */
    public static FeatureRegisteredEvent registerGenerateOreInStone(Block block, int size, int spawnRate, int maxHeight) {
        return registerGenerateOreInStone(new Identifier(MCPitanLib.MOD_ID, Registry.ITEM.getId(block.asItem()).getPath() + "_ore"), block, size, spawnRate, maxHeight);
    }

    public static FeatureRegisteredEvent registerGenerateOreInStone(Identifier id, Block block, int size, int spawnRate, int maxHeight) {
        ConfiguredFeature<?, ?> CONFIGURED_FEATURE = new ConfiguredFeature<>(Feature.ORE,
                new OreFeatureConfig(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, block.getDefaultState(), size));

        PlacedFeature PLACED_FEATURE = new PlacedFeature(RegistryEntry.of(CONFIGURED_FEATURE),
                Arrays.asList(CountPlacementModifier.of(spawnRate), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(maxHeight))));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                id, CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, id,
                PLACED_FEATURE);

        Predicate<BiomeSelectionContext> predicate = BiomeSelectors.all();
        BiomeModifications.addFeature(predicate, GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, id));

        return new FeatureRegisteredEvent(CONFIGURED_FEATURE, PLACED_FEATURE);
    }

    public static FeatureRegisteredEvent registerGenerateOre(Block block, OreFeatureConfig.Target target, int size, int spawnRate, int maxHeight) {
        return registerGenerateOre(new Identifier(MCPitanLib.MOD_ID, Registry.ITEM.getId(block.asItem()).getPath() + "_ore"), block, target, size, spawnRate, maxHeight);
    }

    public static FeatureRegisteredEvent registerGenerateOre(Identifier id, Block block, OreFeatureConfig.Target target, int size, int spawnRate, int maxHeight) {
        ConfiguredFeature<?, ?> CONFIGURED_FEATURE = new ConfiguredFeature<>(Feature.ORE,
                new OreFeatureConfig(target.target, block.getDefaultState(), size));

        PlacedFeature PLACED_FEATURE = new PlacedFeature(RegistryEntry.of(CONFIGURED_FEATURE),
                Arrays.asList(CountPlacementModifier.of(spawnRate), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(maxHeight))));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                id, CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, id,
                PLACED_FEATURE);

        Predicate<BiomeSelectionContext> predicate = BiomeSelectors.all();
        BiomeModifications.addFeature(predicate, GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, id));

        return new FeatureRegisteredEvent(CONFIGURED_FEATURE, PLACED_FEATURE);
    }

    public static void registerFeature(Identifier id, ConfiguredFeature<?, ?> feature) {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, feature);
    }

    // 参考: TreeConfiguredFeatures & Tech Rebornのゴムの木
    public static FeatureRegisteredEvent registerGenerateTree(Identifier id, List<BiomeType> biomes, Block log, Block leaves, @Nullable Block sapling, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
        TreeFeatureConfig.Builder builder = new TreeFeatureConfig.Builder(
                BlockStateProvider.of(log),
                new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight),
                BlockStateProvider.of(leaves),
                new BlobFoliagePlacer(ConstantIntProvider.create(radius),
                        ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(1, 0, 1));

        ConfiguredFeature<?, ?> CONFIGURED_FEATURE = new ConfiguredFeature<>(Feature.TREE,
                builder.build());

        List<PlacementModifier> modifierList = new ArrayList<>();
        if (sapling != null) {
            modifierList.add(PlacedFeatures.wouldSurvive(sapling));
        }
        PlacedFeature PLACED_FEATURE = new PlacedFeature(RegistryEntry.of(CONFIGURED_FEATURE), modifierList);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                id, CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, id,
                PLACED_FEATURE);

        for (BiomeType biome : biomes) {
            Predicate<BiomeSelectionContext> predicate = BiomeSelectors.includeByKey(biome.getRegistryKey());
            BiomeModifications.addFeature(predicate, GenerationStep.Feature.VEGETAL_DECORATION,
                    RegistryKey.of(Registry.PLACED_FEATURE_KEY, id));
        }
        return new FeatureRegisteredEvent(CONFIGURED_FEATURE, PLACED_FEATURE);
    }

    public static FeatureRegisteredEvent registerGenerateOreInStone(Biome biome, Block block, int size, int spawnRate, int maxHeight) {
        return registerGenerateOreInStone(new BiomeType(biome), block, size, spawnRate, maxHeight);
    }

    public static FeatureRegisteredEvent registerGenerateOreInStone(BiomeType biome, Block block, int size, int spawnRate, int maxHeight) {
        return registerGenerateOreInStone(new Identifier(MCPitanLib.MOD_ID, Registry.ITEM.getId(block.asItem()).getPath() + "_ore"), biome, block, size, spawnRate, maxHeight);
    }

    public static FeatureRegisteredEvent registerGenerateOreInStone(Identifier id, Biome biome, Block block, int size, int spawnRate, int maxHeight) {
        return registerGenerateOreInStone(id, new BiomeType(biome), block, size, spawnRate, maxHeight);
    }

    public static FeatureRegisteredEvent registerGenerateOreInStone(Identifier id, BiomeType biome, Block block, int size, int spawnRate, int maxHeight) {
        ConfiguredFeature<?, ?> CONFIGURED_FEATURE = new ConfiguredFeature(Feature.ORE,
                new OreFeatureConfig(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, block.getDefaultState(), size));

        PlacedFeature PLACED_FEATURE = new PlacedFeature(RegistryEntry.of(CONFIGURED_FEATURE),
                Arrays.asList(CountPlacementModifier.of(spawnRate), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(maxHeight))));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                id, CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, id,
                PLACED_FEATURE);

        Predicate<BiomeSelectionContext> predicate = BiomeSelectors.includeByKey(biome.getRegistryKey());
        BiomeModifications.addFeature(predicate, GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, id));
        return new FeatureRegisteredEvent(CONFIGURED_FEATURE, PLACED_FEATURE);
    }


    public static void registerRecipe(Identifier id, JsonObject recipe) {
        RecipeManageHelper.addRecipe(id, recipe);
    }

    public static void registerRecipe(Identifier id, String recipeJsonStr) {
        Gson gson = new Gson();
        JsonObject recipe = gson.fromJson(recipeJsonStr, JsonObject.class);
        registerRecipe(id, recipe);
    }

    public static void registerScreen(ScreenHandlerType type, ScreenRegistry.Factory factory) {
        if (MCPitanUtils.isClient())
            ScreenRegistry.register(type ,factory);
    }

    public static ScreenHandlerType registerScreenHandler(IdentifierExt id, ScreenHandlerRegistry.SimpleClientHandlerFactory factory) {
        return ScreenHandlerRegistry.registerSimple(id ,factory);
    }

    public static ScreenHandlerType registerScreenHandler(IdentifierExt id, ScreenHandlerRegistry.ExtendedClientHandlerFactory factory) {
        return ScreenHandlerRegistry.registerExtended(id ,factory);
    }

    public static ScreenHandlerType registerSimpleScreenHandler(IdentifierExt id, ScreenHandlerRegistry.SimpleClientHandlerFactory factory) {
        return ScreenHandlerRegistry.registerSimple(id ,factory);
    }

    public static ScreenHandlerType registerExtendedScreenHandler(IdentifierExt id, ScreenHandlerRegistry.ExtendedClientHandlerFactory factory) {
        return ScreenHandlerRegistry.registerExtended(id ,factory);
    }
}
