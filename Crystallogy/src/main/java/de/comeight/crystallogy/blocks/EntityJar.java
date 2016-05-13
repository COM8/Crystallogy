package de.comeight.crystallogy.blocks;

import java.util.List;

import de.comeight.crystallogy.blocks.materials.CustomMaterials;
import de.comeight.crystallogy.items.threatDusts.DamDust;
import de.comeight.crystallogy.items.threatDusts.DrowDust;
import de.comeight.crystallogy.items.threatDusts.FireDust;
import de.comeight.crystallogy.items.threatDusts.HungDust;
import de.comeight.crystallogy.items.threatDusts.PoisDust;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar.EnumThreats;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityJar extends BaseBlockTileEntity {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "entityJar";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityJar() {
		super(CustomMaterials.glass, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.685, 0.75);
	}
	
	private ItemStack getItemStackWithData(World world, BlockPos pos){
		TileEntity tE = world.getTileEntity(pos);
		ItemStack stack = new ItemStack(this);
		if(tE instanceof TileEntityPlayerJar){
			TileEntityPlayerJar jar = (TileEntityPlayerJar) tE;
			NBTTagCompound compound = new NBTTagCompound();
			
			if(jar.hasPlayer()){
				jar.writeCustomDataToNBT(compound);
				stack.setTagCompound(compound);
			}
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
	
	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}
	
	@Override
	public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
		if (worldIn.isRemote) { // Client Side Welt?
			return 0;
		} 
		else {
			TileEntity tE = worldIn.getTileEntity(pos);
			if(tE instanceof TileEntityPlayerJar){
				TileEntityPlayerJar jar = (TileEntityPlayerJar) tE;
				if (tE != null) {
					if(jar.hasPlayer()){
						return 2;
					}
				}
			}
			return 0;
		}
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		TileEntity tE = worldIn.getTileEntity(pos);
		if(tE instanceof TileEntityPlayerJar && stack.hasTagCompound()){
			TileEntityPlayerJar jar = (TileEntityPlayerJar) tE;
			jar.readCustomDataToNBT(stack.getTagCompound());
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {		
		TileEntity tE = worldIn.getTileEntity(pos);
		if(tE instanceof TileEntityPlayerJar){
			TileEntityPlayerJar jar = (TileEntityPlayerJar) tE;
			if(jar.hasPlayer()){
				if(playerIn.isSneaking()){
					//Releas Player
					jar.removePlayer(worldIn, new Vec3d(pos), true); //TODO Fix no sound playing
					worldIn.notifyNeighborsOfStateChange(pos, this);
				}
				else{
					ItemStack stack = null;
					if((stack = playerIn.getHeldItemMainhand())!= null){
						//Use threat dust
						if(!worldIn.isRemote){
							testForThreatDust(stack, jar, pos, playerIn);
						}
					}
				}
			}
		}
		return true;
	}
	
	private void testForThreatDust(ItemStack stack, TileEntityPlayerJar jar, BlockPos pos, EntityPlayer playerIn){
		boolean success = false;
		
		if(stack.getItem() instanceof DamDust){
			jar.addThreat(EnumThreats.DAMAGE);
			success = true;
		}
		else if(stack.getItem() instanceof DrowDust){
			jar.addThreat(EnumThreats.DROWN);
			success = true;
		}
		else if(stack.getItem() instanceof PoisDust){
			jar.addThreat(EnumThreats.POISON);
			success = true;
		}
		else if(stack.getItem() instanceof FireDust){
			jar.addThreat(EnumThreats.FIRE);
			success = true;
		}
		else if(stack.getItem() instanceof HungDust){
			jar.addThreat(EnumThreats.HUNGER);
			success = true;
		}
		if(success){
			ItemStack playerIStack = playerIn.getHeldItemMainhand();
			if (playerIStack != null){
	            playerIStack.stackSize--;
	        }
		}
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		super.onBlockClicked(worldIn, pos, playerIn);
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityPlayerJar();
	}
	
}
