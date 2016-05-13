/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.claysoldiers.util;

import de.sanandrew.mods.claysoldiers.block.BlockClayNexus;
import de.sanandrew.mods.claysoldiers.tileentity.TileEntityClayNexus;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public final class RegistryBlocks
{
    public static Block clayNexus;

    public static void initialize() {
        clayNexus = new BlockClayNexus();

        clayNexus.setCreativeTab(ClaySoldiersMod.clayTab);
        clayNexus.setUnlocalizedName(ClaySoldiersMod.MOD_ID + ":nexus");
        clayNexus.setRegistryName(ClaySoldiersMod.MOD_ID + ":nexus");
        GameRegistry.registerTileEntity(TileEntityClayNexus.class, ClaySoldiersMod.MOD_ID + ":nexus_te");

        //SAPUtils.registerBlocks(clayNexus);
    }
}
