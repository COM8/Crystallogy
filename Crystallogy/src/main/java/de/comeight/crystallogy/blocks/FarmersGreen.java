package de.comeight.crystallogy.blocks;

import java.util.List;

import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.tileEntitys.TileEntityFarmersGreen;
import de.comeight.crystallogy.util.ToolTipBuilder;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FarmersGreen extends BaseBlockTileEntity {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "farmersGreen";
	
	//0=Closed-Empty, 1= Closed-Full, 2=Open-Empty, 3=Open-Full
	public static final PropertyInteger STATUS = PropertyInteger.create("status", 0, 3);
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public FarmersGreen() {
		super(Material.GLASS, ID);
		
		this.setHardness(0.0F);
		this.setSoundType(SoundType.GLASS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(STATUS, 0));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, java.util.List<ItemStack> list) {
		NBTTagCompound compound = new NBTTagCompound();
		
		compound.setInteger("growthLeft", 12000);
		ItemStack i1 = new  ItemStack(itemIn, 1, 0);
		i1.setTagCompound(compound);
	
		ItemStack i2 = new  ItemStack(itemIn, 1, 1);
		
		list.add(i1);
		list.add(i2);
	}
	
	public static void setBlockState(int status, World worldIn, BlockPos pos){
		TileEntity tE = worldIn.getTileEntity(pos);
		worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(STATUS, status), 3);
		
		if(tE != null){
			tE.validate();
			worldIn.setTileEntity(pos, tE);
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(STATUS, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(STATUS);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.71, 0.75);
	}
	
	private ItemStack getItemStackWithData(World world, BlockPos pos){
		TileEntity tE = world.getTileEntity(pos);
		ItemStack stack = new ItemStack(this);
		if(tE instanceof TileEntityFarmersGreen){
			TileEntityFarmersGreen farmersGreenTE = (TileEntityFarmersGreen) tE;
			NBTTagCompound compound = new NBTTagCompound();
			
			compound.setInteger("growthLeft", farmersGreenTE.growthLeft);
			stack.setTagCompound(compound);
		}
		return stack;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return getItemStackWithData(world, pos);
	}
	
	@Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> ret = new java.util.ArrayList<ItemStack>();
		ret.add(getItemStackWithData((World) world, pos));
        return ret;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityFarmersGreen();
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(stack.hasTagCompound()){
			NBTTagCompound tag = stack.getTagCompound();
			if(GuiScreen.isShiftKeyDown()){
				tooltip.add(TextFormatting.DARK_GREEN + "Growth" + TextFormatting.RESET + " for: " + tag.getInteger("growthLeft") + " ticks");
			}
			else{
				ToolTipBuilder.addShiftForMoreDetails(tooltip);
			}
		}
		
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
	@Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {STATUS});
    }
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if(stack.getMetadata() == 1){
			worldIn.setBlockState(pos, state.withProperty(STATUS, 0), 1);
		}
		else{
			worldIn.setBlockState(pos, state.withProperty(STATUS, 1), 1);
			TileEntity tE = worldIn.getTileEntity(pos);
			if(tE instanceof TileEntityFarmersGreen){
				TileEntityFarmersGreen tEFG = (TileEntityFarmersGreen) tE;
				if(stack.hasTagCompound() && stack.getTagCompound().getInteger("growthLeft") > 0){
					tEFG.growthLeft = stack.getTagCompound().getInteger("growthLeft");
				}
				else{
					worldIn.setBlockState(pos, state.withProperty(STATUS, 0), 3);
				}
			}
		}
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		if(state.getValue(STATUS) == 1 || state.getValue(STATUS) == 3){
			return 0;
		}
		else{
			return 1;
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(playerIn.isSneaking()){
			switch (worldIn.getBlockState(pos).getValue(STATUS)) {
				case 0:
					setBlockState(2, worldIn, pos);
					break;
				case 2:
					setBlockState(0, worldIn, pos);
					break;
				case 1:
					setBlockState(3, worldIn, pos);
					break;
				case 3:
					setBlockState(1, worldIn, pos);
					break;
				default:
					return false;
			}
		}
		else{
			TileEntity tE = worldIn.getTileEntity(pos);
			if(tE instanceof TileEntityFarmersGreen){
				TileEntityFarmersGreen tEFG = (TileEntityFarmersGreen) tE;
				
				if(heldItem != null && heldItem.getItem() == ItemHandler.fertilizerPotato){
					int prevState = worldIn.getBlockState(pos).getValue(STATUS);
					tEFG.refuel();
					if(prevState == 0){
						setBlockState(1, worldIn, pos);
					}
					else if(prevState == 2){
						setBlockState(3, worldIn, pos);
					}
					if(!playerIn.capabilities.isCreativeMode){
						heldItem.stackSize--;
						if(heldItem.stackSize < 1){
							heldItem.stackSize = 0;
						}
					}
				}
				if(!worldIn.isRemote){
					playerIn.addChatComponentMessage(new TextComponentString(TextFormatting.DARK_GREEN + "Growth" + TextFormatting.RESET + " for: " + tEFG.growthLeft + " ticks"));
				}
			}
		}
		return true;
	}
	
}
