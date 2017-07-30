package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.Crystallogy;
import de.comeight.crystallogy.util.Logger;
import de.comeight.crystallogy.util.enums.EnumCrystalColor;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Crystallogy.MOD_ID)
public class ModelManager {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final ModelManager INSTANCE = new ModelManager();

    private final StateMapperBase propertyStringMapper = new StateMapperBase() {
        @Override
        protected ModelResourceLocation getModelResourceLocation(final IBlockState state) {
            return new ModelResourceLocation("minecraft:air");
        }
    };

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerBlockModels() {
        registerBlockItemModel(BlockHandler.CRYSTAL_ORE_RED.getDefaultState());
        registerBlockItemModel(BlockHandler.CRYSTAL_ORE_BLUE.getDefaultState());
        registerBlockItemModel(BlockHandler.CRYSTAL_ORE_GREEN.getDefaultState());
        registerBlockItemModel(BlockHandler.CRYSTAL_ORE_YELLOW.getDefaultState());
        registerBlockItemModel(BlockHandler.CRYSTAL_ORE_WHITE.getDefaultState());
        registerBlockItemModel(BlockHandler.ENTITY_JAR.getDefaultState());
        registerBlockItemModel(BlockHandler.PLAYER_JAR.getDefaultState());

        Logger.info("All block renderer got registered.");
    }

    private void registerBlockItemModel(final IBlockState state) {
        final Block block = state.getBlock();
        final Item item = Item.getItemFromBlock(block);

        if (item != Items.AIR) {
            registerItemModel(item, new ModelResourceLocation(block.getRegistryName(), propertyStringMapper.getPropertyString(state.getProperties())));
        }
    }

    private void registerItemModels() {
        for (int i = 0; i < 5; i++) {
            registerItemModelForMeta(ItemHandler.CRYSTAL_SHARD, i, ItemHandler.CRYSTAL_SHARD.getRegistryName().toString() + '_' + EnumCrystalColor.fromMeta(i));
            registerItemModelForMeta(ItemHandler.CRYSTAL_DUST, i, ItemHandler.CRYSTAL_DUST.getRegistryName().toString() + '_' + EnumCrystalColor.fromMeta(i));
            registerItemModelForMeta(ItemHandler.CRYSTAL_HAMMER_HEAD, i, ItemHandler.CRYSTAL_HAMMER_HEAD.getRegistryName().toString() + '_' + EnumCrystalColor.fromMeta(i));
            registerItemModelForMeta(ItemHandler.CRYSTAL_PICKAXE_HEAD, i, ItemHandler.CRYSTAL_PICKAXE_HEAD.getRegistryName().toString() + '_' + EnumCrystalColor.fromMeta(i));
            registerItemModelForMeta(ItemHandler.CRYSTAL_SWORD_BLADE, i, ItemHandler.CRYSTAL_SWORD_BLADE.getRegistryName().toString() + '_' + EnumCrystalColor.fromMeta(i));
            registerItemModelForMeta(ItemHandler.CRYSTAL_SHOVEL_HEAD, i, ItemHandler.CRYSTAL_SHOVEL_HEAD.getRegistryName().toString() + '_' + EnumCrystalColor.fromMeta(i));
        }

        registerItemModel(ItemHandler.CRYSTAL_PICKAXE_RED);
        registerItemModel(ItemHandler.CRYSTAL_PICKAXE_BLUE);
        registerItemModel(ItemHandler.CRYSTAL_PICKAXE_GREEN);
        registerItemModel(ItemHandler.CRYSTAL_PICKAXE_YELLOW);
        registerItemModel(ItemHandler.CRYSTAL_PICKAXE_WHITE);

        registerItemModel(ItemHandler.CRYSTAL_HAMMER_RED);
        registerItemModel(ItemHandler.CRYSTAL_HAMMER_BLUE);
        registerItemModel(ItemHandler.CRYSTAL_HAMMER_GREEN);
        registerItemModel(ItemHandler.CRYSTAL_HAMMER_YELLOW);
        registerItemModel(ItemHandler.CRYSTAL_HAMMER_WHITE);

        registerItemModel(ItemHandler.CRYSTAL_SWORD_RED);
        registerItemModel(ItemHandler.CRYSTAL_SWORD_BLUE);
        registerItemModel(ItemHandler.CRYSTAL_SWORD_GREEN);
        registerItemModel(ItemHandler.CRYSTAL_SWORD_YELLOW);
        registerItemModel(ItemHandler.CRYSTAL_SWORD_WHITE);

        registerItemModel(ItemHandler.CRYSTAL_SHOVEL_RED);
        registerItemModel(ItemHandler.CRYSTAL_SHOVEL_BLUE);
        registerItemModel(ItemHandler.CRYSTAL_SHOVEL_GREEN);
        registerItemModel(ItemHandler.CRYSTAL_SHOVEL_YELLOW);
        registerItemModel(ItemHandler.CRYSTAL_SHOVEL_WHITE);

        registerItemModel(ItemHandler.BOOK_OF_KNOWLEDGE);

        Logger.info("All item renderer got registered.");
    }

    private void registerItemModel(final Item item) {
        registerItemModel(item, item.getRegistryName().toString());
    }

    private void registerItemModel(final Item item, final String modelLocation) {
        ModelResourceLocation location = new ModelResourceLocation(modelLocation, "inventory");
        registerItemModel(item, location);
    }

    private void registerItemModel(final Item item, final ModelResourceLocation location) {
        ModelBakery.registerItemVariants(item, location);
        ModelLoader.setCustomMeshDefinition(item, stack -> location);
    }

    private void registerItemModelForMeta(final Item item, final int metadata, final String variant) {
        ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(variant, "inventory"));
    }

    //-----------------------------------------------Events:------------------------------------------------
    @SubscribeEvent
    public static void registerAllModels(final ModelRegistryEvent event) {
        INSTANCE.registerBlockModels();
        INSTANCE.registerItemModels();
    }
}