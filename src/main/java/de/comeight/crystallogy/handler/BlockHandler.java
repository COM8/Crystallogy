package de.comeight.crystallogy.handler;

import com.google.common.base.Preconditions;
import de.comeight.crystallogy.Crystallogy;
import de.comeight.crystallogy.blocks.EntityJar;
import de.comeight.crystallogy.blocks.PlayerJar;
import de.comeight.crystallogy.blocks.crystals.*;
import de.comeight.crystallogy.util.Logger;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Set;

@GameRegistry.ObjectHolder(Crystallogy.MOD_ID)
public class BlockHandler {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final CrystalOreRed CRYSTAL_ORE_RED = new CrystalOreRed();
    public static final CrystalOreBlue CRYSTAL_ORE_BLUE = new CrystalOreBlue();
    public static final CrystalOreGreen CRYSTAL_ORE_GREEN = new CrystalOreGreen();
    public static final CrystalOreYellow CRYSTAL_ORE_YELLOW = new CrystalOreYellow();
    public static final CrystalOreWhite CRYSTAL_ORE_WHITE = new CrystalOreWhite();
    public static final EntityJar ENTITY_JAR = new EntityJar();
    public static final PlayerJar PLAYER_JAR = new PlayerJar();

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerOreDictionaryEntries() {
        Logger.info("All ore dictionary entries got added.");
    }

    private static void registerTileEntities() {
        //registerTileEntity(TileEntitySurvivalCommandBlock.class, "survival_command_block");

        Logger.info("All tileEntities got registered.");
    }

    private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
        GameRegistry.registerTileEntity(tileEntityClass, Crystallogy.MOD_ID + ":" + name);
    }

    //-----------------------------------------------Events:------------------------------------------------
    @Mod.EventBusSubscriber(modid = Crystallogy.MOD_ID)
    public static class RegistrationHandler {
        public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

        /**
         * Register this mod's {@link Block}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> registry = event.getRegistry();
            final Block[] blocks = {
                    CRYSTAL_ORE_RED,
                    CRYSTAL_ORE_BLUE,
                    CRYSTAL_ORE_GREEN,
                    CRYSTAL_ORE_YELLOW,
                    CRYSTAL_ORE_WHITE,
                    ENTITY_JAR,
                    PLAYER_JAR
            };

            registry.registerAll(blocks);
            Logger.info("All blocks got registered.");
        }

        /**
         * Register this mod's {@link ItemBlock}s.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> registry = event.getRegistry();
            final ItemBlock[] items = {
                    new ItemBlock(CRYSTAL_ORE_RED),
                    new ItemBlock(CRYSTAL_ORE_BLUE),
                    new ItemBlock(CRYSTAL_ORE_GREEN),
                    new ItemBlock(CRYSTAL_ORE_YELLOW),
                    new ItemBlock(CRYSTAL_ORE_WHITE),
                    new ItemBlock(ENTITY_JAR),
                    new ItemBlock(PLAYER_JAR),
            };

            for (final ItemBlock item : items) {
                final Block block = item.getBlock();
                final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
                registry.register(item.setRegistryName(registryName));
                ITEM_BLOCKS.add(item);
            }
            Logger.info("All itemBlocks got registered.");

            registerTileEntities();
        }
    }

    //-----------------------------------------------Pre-Init:----------------------------------------------
    public void preInit(FMLPreInitializationEvent e) {
    }

    //-----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {
        registerOreDictionaryEntries();
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    }
}