package ml.pkom.mcpitanlib.api.register.v1;

import com.google.gson.JsonObject;
import ml.pkom.mcpitanlib.api.MCPitanLib;
import ml.pkom.mcpitanlib.api.block.BlockExt;
import ml.pkom.mcpitanlib.api.event.MiningToolEvent;
import ml.pkom.mcpitanlib.api.event.RegisteredEvent;
import ml.pkom.mcpitanlib.api.item.ItemExt;
import ml.pkom.mcpitanlib.api.util.IdentifierExt;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import javax.annotation.Nullable;
import java.util.Map;

/*
Registries class contain register methods
usable v2
 */
@Deprecated
public class Registries {

    public static ml.pkom.mcpitanlib.api.register.v2.Registries registriesV2;

    /**
     * Register item
     * @param id Item id
     * @param item Item
     * @return Registered info
     */
    public static RegisteredEvent registerItem(Identifier id, Item item) {
        return registriesV2.registerItem(id, item);
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
        return registriesV2.registerBlock(id, block, doItemRegistration);
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
        return registriesV2.registerBlock(id, block, settings);
    }

    //private static Map<Block, MiningToolEvent> mineableToolTagsMap = new HashMap<>();

    public static Map<Block, MiningToolEvent> getMineableToolTagsMap() {
        return registriesV2.getMineableToolTagsMap();
    }

    public static boolean containsMineableToolTags(Block block) {
        return registriesV2.containsMineableToolTags(block);
    }

    /**
     * Register mineable tag
     * @param block Block
     */
    public static void registerMineable(BlockExt block) {
        registriesV2.registerMineable(block);
    }

    /**
     * Register BlockEntity from BlockEntity class (Tile)
     * @param id BlockEntity id
     * @param blockEntity BlockEntity
     * @param blocks blocks
     * @return BlockEntityType
     */
    public static BlockEntityType<?> registerBlockEntity(Identifier id, Class<? extends BlockEntity> blockEntity, Block... blocks) {
        return registriesV2.registerBlockEntity(id, blockEntity, blocks).getBlockEntityType();
    }

    /**
     * Register BlockEntity from BlockEntityType (Tile)
     * @param id BlockEntity id
     * @param blockEntityType BlockEntityType
     * @return BlockEntityType
     */
    public static BlockEntityType<?> registerBlockEntity(Identifier id, BlockEntityType<?> blockEntityType) {
        return registriesV2.registerBlockEntity(id, blockEntityType).getBlockEntityType();
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
        return registriesV2.registerEntity(id, entity, spawnGroup, width, height).getEntityType();
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
        registriesV2.registerGenerateOreInStone(id, block, size, spawnRate, maxHeight);

    }

    public static void registerGenerateOre(Block block, OreFeatureConfig.Target target, int size, int spawnRate, int maxHeight) {
        registerGenerateOre(new Identifier(MCPitanLib.MOD_ID, Registry.ITEM.getId(block.asItem()).getPath() + "_ore"), block, target, size, spawnRate, maxHeight);
    }

    public static void registerGenerateOre(Identifier id, Block block, OreFeatureConfig.Target target, int size, int spawnRate, int maxHeight) {
        registriesV2.registerGenerateOre(id, block, target, size, spawnRate, maxHeight);
    }

    public static void registerGenerateOreInStone(Biome biome, Block block, int size, int spawnRate, int maxHeight) {
        registerGenerateOreInStone(new Identifier(MCPitanLib.MOD_ID, Registry.ITEM.getId(block.asItem()).getPath() + "_ore"), biome, block, size, spawnRate, maxHeight);
    }

    public static void registerGenerateOreInStone(Identifier id, Biome biome, Block block, int size, int spawnRate, int maxHeight) {
        registriesV2.registerGenerateOreInStone(id, biome, block, size, spawnRate, maxHeight);
    }


    public static void registerRecipe(Identifier id, JsonObject recipe) {
        registriesV2.registerRecipe(id, recipe);
    }

    public static void registerRecipe(Identifier id, String recipeJsonStr) {
        registriesV2.registerRecipe(id, recipeJsonStr);
    }

    public static void registerScreen(ScreenHandlerType type, ScreenRegistry.Factory factory) {
        registriesV2.registerScreen(type, factory);
    }

    public static ScreenHandlerType registerScreenHandler(IdentifierExt id, ScreenHandlerRegistry.SimpleClientHandlerFactory factory) {
        return registriesV2.registerScreenHandler(id, factory);
    }

    public static ScreenHandlerType registerScreenHandler(IdentifierExt id, ScreenHandlerRegistry.ExtendedClientHandlerFactory factory) {
        return registriesV2.registerScreenHandler(id, factory);
    }

    public static ScreenHandlerType registerSimpleScreenHandler(IdentifierExt id, ScreenHandlerRegistry.SimpleClientHandlerFactory factory) {
        return registriesV2.registerSimpleScreenHandler(id, factory);
    }

    public static ScreenHandlerType registerExtendedScreenHandler(IdentifierExt id, ScreenHandlerRegistry.ExtendedClientHandlerFactory factory) {
        return registriesV2.registerExtendedScreenHandler(id, factory);
    }
}
