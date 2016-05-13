/*******************************************************************************************************************
 * Authors:   SanAndreasP
 * Copyright: SanAndreasP, SilverChiren and CliffracerX
 * License:   Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International
 *                http://creativecommons.org/licenses/by-nc-sa/4.0/
 *******************************************************************************************************************/
package de.sanandrew.mods.claysoldiers.block;

import de.sanandrew.mods.claysoldiers.tileentity.TileEntityClayNexus;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockClayNexus
        extends Block
{
    public BlockClayNexus() {
        super(Material.rock);
    }
    
    @Override
    public MapColor getMapColor(IBlockState state) {
    	return MapColor.obsidianColor;
    }
    
    @Override
    public boolean isFullBlock(IBlockState state) {
    	return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
    	return EnumBlockRenderType.MODEL;
    }
    
    @Override
    public boolean isBlockNormalCube(IBlockState state) {
    	return false;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState worldIn, World pos, BlockPos state) {
    	return NULL_AABB;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
    	return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
    	TileEntityClayNexus teNexus = (TileEntityClayNexus) worldIn.getTileEntity(pos);
        if( !worldIn.isRemote ) {
            ItemStack playerItem = playerIn.getHeldItemMainhand();
            if( playerItem != null ) {
                if( teNexus.isItemValidForSlot(TileEntityClayNexus.SOLDIER_SLOT, playerItem) ) {
                    teNexus.setInventorySlotContents(TileEntityClayNexus.SOLDIER_SLOT, playerItem);
                    teNexus.markDirty();
                    worldIn.markBlockForUpdate(x, y, z);
                    return true;
                } else if( teNexus.isItemValidForSlot(TileEntityClayNexus.THROWABLE_SLOT, playerItem) ) {
                    teNexus.setInventorySlotContents(TileEntityClayNexus.THROWABLE_SLOT, playerItem);
                    teNexus.markDirty();
                    worldIn.markBlockForUpdate(x, y, z);
                    return true;
                }
            }

            if( playerIn.isSneaking() ) {
                teNexus.repair();
                teNexus.markDirty();
                worldIn.markBlockForUpdate(pos);
                return true;
            }

            teNexus.isActive = !teNexus.isActive;
            worldIn.setBlockMetadataWithNotify(x, y, z, teNexus.isActive ? 1 : 0, 2);
            teNexus.markDirty();
            worldIn.markBlockForUpdate(x, y, z);
        }

        return true;
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) == 1 ? 6 : 0;
    }
    
    @Override
    public int getLightValue(IBlockState state) {
    	return 0;
    }
    
    @Override
    public boolean hasTileEntity() {
    	return true;
    }
    
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
    	return new TileEntityClayNexus();
    }

    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
    	if(side == EnumFacing.DOWN || side == EnumFacing.UP){
    		return false;
    	}
    	return true;
    }
}
