package ml.pkom.mcpitanlib.api.register;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ml.pkom.mcpitanlib.api.MCPitanLib;
import ml.pkom.mcpitanlib.api.block.BlockExt;
import ml.pkom.mcpitanlib.api.item.ItemExt;
import ml.pkom.mcpitanlib.api.tag.MineableToolTags;
import ml.pkom.mcpitanlib.api.util.RecipeManageHelper;
import ml.pkom.mcpitanlib.api.event.MiningToolEvent;
import ml.pkom.mcpitanlib.api.event.RegisteredEvent;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
    public static RegisteredEvent registerItem(Identifier id, Item item) {
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
    public static RegisteredEvent registerItem(String idStr, Item item) {
        return registerItem(new Identifier(idStr), item);
    }

    public static RegisteredEvent registerItem(ItemExt item) {
        return registerItem(item.getItemId(), item);
    }
    
    /**
     * Register block
     * @param id Block id
     * @param block Block
     * @param doItemRegistration Whether to register items as well
     * @return Registered info
     */
    public static RegisteredEvent registerBlock(Identifier id, BlockExt block, boolean doItemRegistration) {
        RegisteredEvent info = new RegisteredEvent("block");
        info.setBlock(Registry.register(Registry.BLOCK, id, block));
        if (doItemRegistration) {
            info.setItem(Registry.register(Registry.ITEM, id, new BlockItem(block, block.getItemSettings())));
        }
        registerMineable(block);
        return info;
    }

    public static RegisteredEvent registerBlock(Identifier id, Block block) {
        return registerBlock(id, block, null);
    }

    /**
     * Register block and register item
     * @param id Block id
     * @param block Block
     * @param settings Item Settings
     * @return Registered info
     */
    public static RegisteredEvent registerBlock(Identifier id, Block block, @Nullable Item.Settings settings) {
        RegisteredEvent info = new RegisteredEvent("block");
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
    public static BlockEntityType<?> registerBlockEntity(Identifier id, Class<? extends BlockEntity> blockEntity, Block... blocks) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.create((FabricBlockEntityTypeBuilder.Factory<? extends BlockEntity>)(Supplier<BlockEntity>) () -> {
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
     * Register BlockEntity from BlockEntityType (Tile)
     * @param id BlockEntity id
     * @param blockEntityType BlockEntityType
     * @return BlockEntityType
     */
    public static BlockEntityType<?> registerBlockEntity(Identifier id, BlockEntityType<?> blockEntityType) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, id, blockEntityType);
    }

    /*
     * Alias
     */
    public static BlockEntityType<?> registerTile(Identifier id, BlockEntityType<?> blockEntityType) {
        return registerBlockEntity(id, blockEntityType);
    }

    /**
     * Alias
     */
    public static BlockEntityType<?> registerTile(Identifier id, Class<? extends BlockEntity> blockEntity, Block... blocks) {
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
    public static EntityType<?> registerEntity(Identifier id, Entity entity, SpawnGroup spawnGroup, float width, float height) {
        EntityType entityType = Registry.register(Registry.ENTITY_TYPE, id, FabricEntityTypeBuilder.create(spawnGroup, (EntityType.EntityFactory<? extends Entity>)(Supplier<Entity>) () -> {
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
    public static BlockEntityType registerBlockEntity(Class<? extends BlockEntity> blockEntity, Block... blocks) {
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
    public static RegisteredEvent registerItem(Item item) {
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
    public static RegisteredEvent registerBlock(Block block) {
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
    public static void registerGenerateOreInStone(Block block, int size, int spawnRate, int maxHeight) {
        registerGenerateOreInStone(new Identifier(MCPitanLib.MOD_ID, Registry.ITEM.getId(block.asItem()).getPath() + "_ore"), block, size, spawnRate, maxHeight);

    }

    public static void registerGenerateOreInStone(Identifier id, Block block, int size, int spawnRate, int maxHeight) {
        ConfiguredFeature<?, ?> CONFIGURED_FEATURE = new ConfiguredFeature(Feature.ORE,
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
    }

    public static void registerGenerateOre(Block block, OreFeatureConfig.Target target, int size, int spawnRate, int maxHeight) {
        registerGenerateOre(new Identifier(MCPitanLib.MOD_ID, Registry.ITEM.getId(block.asItem()).getPath() + "_ore"), block, target, size, spawnRate, maxHeight);
    }

    public static void registerGenerateOre(Identifier id, Block block, OreFeatureConfig.Target target, int size, int spawnRate, int maxHeight) {
        ConfiguredFeature<?, ?> CONFIGURED_FEATURE = new ConfiguredFeature(Feature.ORE,
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
    }

    public static void registerGenerateOreInStone(Biome biome, Block block, int size, int spawnRate, int maxHeight) {
        registerGenerateOreInStone(new Identifier(MCPitanLib.MOD_ID, Registry.ITEM.getId(block.asItem()).getPath() + "_ore"), biome, block, size, spawnRate, maxHeight);
    }

    public static void registerGenerateOreInStone(Identifier id, Biome biome, Block block, int size, int spawnRate, int maxHeight) {
        ConfiguredFeature<?, ?> CONFIGURED_FEATURE = new ConfiguredFeature(Feature.ORE,
                new OreFeatureConfig(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, block.getDefaultState(), size));

        PlacedFeature PLACED_FEATURE = new PlacedFeature(RegistryEntry.of(CONFIGURED_FEATURE),
                Arrays.asList(CountPlacementModifier.of(spawnRate), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(maxHeight))));

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                id, CONFIGURED_FEATURE);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, id,
                PLACED_FEATURE);

        Predicate<BiomeSelectionContext> predicate = BiomeSelectors.includeByKey(BuiltinRegistries.BIOME.getKey(biome).get());
        BiomeModifications.addFeature(predicate, GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY, id));

    }


    public static void registerRecipe(Identifier id, JsonObject recipe) {
        RecipeManageHelper.addRecipe(id, recipe);
    }

    public static void registerRecipe(Identifier id, String recipeJsonStr) {
        Gson gson = new Gson();
        JsonObject recipe = gson.fromJson(recipeJsonStr, JsonObject.class);
        registerRecipe(id, recipe);
    }
}
