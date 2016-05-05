package de.comeight.crystallogy.blocks;

import java.util.Random;

import com.google.common.base.Predicate;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.JumpParticleBetweenCrystalls;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Crystall extends BaseBlockCutout{
	
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected RGBColor color;
	protected int chance;
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>()
    {
        public boolean apply(EnumFacing p_apply_1_)
        {
            return true;
        }
    });
	protected static final AxisAlignedBB CRYSTAL_NORTH_AABB = new AxisAlignedBB(0.3, 0.25, 0.4, 0.7, 0.9, 1.0);
    protected static final AxisAlignedBB CRYSTAL_SOUTH_AABB = new AxisAlignedBB(0.3, 0.25, 0.0, 0.7, 0.9, 0.6);
    protected static final AxisAlignedBB CRYSTAL_WEST_AABB = new AxisAlignedBB(0.4, 0.1, 0.3, 1.0, 0.75, 0.7);
    protected static final AxisAlignedBB CRYSTAL_EAST_AABB = new AxisAlignedBB(0.0, 0.25, 0.3, 0.6, 0.9, 0.7);
    protected static final AxisAlignedBB CRYSTAL_UP_AABB = new AxisAlignedBB(0.3, 0.0, 0.25, 0.7, 0.6, 0.9);
    protected static final AxisAlignedBB CRYSTAL_DOWN_AABB = new AxisAlignedBB(0.3, 0.4, 0.25, 0.7, 1.0, 0.9);
	//-----------------------------------------------Constructor:-------------------------------------------
	public Crystall(String ID) {
		super(Material.glass, ID);
		
		color = new RGBColor(1.0F, 1.0F, 1.0F);
		this.chance = 5;
		
		this.setHarvestLevel("pickaxe", 3);
		this.setLightLevel(0.3F);
		this.setHardness(5.0F);
		this.setStepSound(SoundType.GLASS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getFront(meta);
		return this.getDefaultState().withProperty(FACING, enumfacing);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}
	
	private void setDefaultStatus(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
        	EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
	
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		switch ((EnumFacing)state.getValue(FACING)){
			case NORTH:
				return CRYSTAL_NORTH_AABB;
			case SOUTH:
				return CRYSTAL_SOUTH_AABB;
			case EAST:
				return CRYSTAL_EAST_AABB;
			case WEST:
				return CRYSTAL_WEST_AABB;
			case UP:
				return CRYSTAL_UP_AABB;
			case DOWN:
				return CRYSTAL_DOWN_AABB;
			default:
				return CRYSTAL_DOWN_AABB;
		}
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		if(worldIn.isRemote){	//Client Side Welt?
			addGlitterParticleChance(worldIn, pos, chance);
			//addJumpParticleBetweenCrystallsChance(worldIn, pos, 20);
		}
	}
		
	@SideOnly(Side.CLIENT)
	@Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	protected void addJumpParticleBetweenCrystallsChance(World worldIn, BlockPos pos, int chance){
		if(Utilities.getRandInt(1, chance) == chance){
			findCrystallsAndSendJumpParticle(worldIn, pos); //Jump Particles
		}
	}
	
	protected void findCrystallsAndSendJumpParticle(World worldIn, BlockPos pos){// TODO Fix Hight dif.
		for(int i = -8; i < 8; i++){
			for(int e = -8; e < 8; e++){
				for(int j = -8; j < 8; j++){
					if(worldIn.getBlockState(new BlockPos(pos.getX() + i, pos.getY() + e, pos.getZ() + j)).getBlock() == BlockHandler.crystall_blue){
						addJumpParticleBetweenCrystalls(worldIn, pos, new Vec3d(pos.getX() + i + 0.5, pos.getY() + e + 0.2, pos.getZ() + 0.5 + j), new RGBColor(0.5F, 0.5F, 1.0F));
					}
					if(worldIn.getBlockState(new BlockPos(pos.getX() + i, pos.getY() + e, pos.getZ() + j)).getBlock() == BlockHandler.crystall_red){
						addJumpParticleBetweenCrystalls(worldIn, pos, new Vec3d(pos.getX() + i + 0.5, pos.getY() + e + 0.2, pos.getZ() + 0.5 + j), color);
					}
				}
			}
		}
	}
	
	protected void addJumpParticleBetweenCrystalls(World worldIn, BlockPos pos, Vec3d targetPos, RGBColor color){
		JumpParticleBetweenCrystalls jP = new JumpParticleBetweenCrystalls(worldIn, pos.getX() + 0.5, pos.getY() + 0.2, pos.getZ() + 0.5, 0.1, 0.1, 0.1, targetPos);
		jP.setRBGColorF(color.r, color.g, color.b);
		Minecraft.getMinecraft().effectRenderer.addEffect(jP);
	}
	
	protected void setParticleColor(RGBColor color){
		this.color = color;
	}
	
	protected void addGlitterParticleChance(World worldIn, BlockPos pos){
		addGlitterParticleChance(worldIn, pos, this.chance);
	}
	
	protected void addGlitterParticleChance(World worldIn, BlockPos pos, int chance){
		if(Utilities.getRandInt(1, chance) == chance){
			addGlitterParticle(worldIn, pos);
		}
	}
	
	protected void addGlitterParticle(World worldIn, BlockPos pos){
		ParticleB gP = new ParticleB(worldIn, pos.getX() + Utilities.getRandDouble(0.3, 0.7), pos.getY() + Utilities.getRandDouble(0.2, 0.6), pos.getZ() + Utilities.getRandDouble(0.25, 0.9), 0.2, 0.2, 0.2);
		gP.setRBGColorF(color.r, color.g, color.b);
		Minecraft.getMinecraft().effectRenderer.addEffect(gP);
	}
	
	protected void addGlitterParticleNetworkChance(World worldIn, BlockPos pos, int chance){
		if(Utilities.getRandInt(1, chance) == chance){
			addGlitterParticleNetwork(worldIn, pos);
		}
	}
	
	protected void addGlitterParticleNetwork(World worldIn, BlockPos pos){
		ParticleB gP = new ParticleB(worldIn, pos.getX() + Utilities.getRandDouble(0.25, 0.75), pos.getY() + Utilities.getRandDouble(0.25, 0.75), pos.getZ() + Utilities.getRandDouble(0.25, 0.75), 0.0, 0.0, 0.0);
		gP.setParticleMaxAge(60);
		gP.setRBGColorF(color.r, color.g, color.b);
		NetworkParticle nP = new NetworkParticle(gP, gP.name);
		nP.setSize(new Vec3d(0.0, 0.0, 0.0));
		NetworkPacketParticle pMtS = new NetworkPacketParticle(nP);
		CommonProxy.NETWORKWRAPPER.sendToServer(pMtS);
	}
    
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		for(int i = 0; i < 10; i++){
			addGlitterParticleNetwork(worldIn, pos);
		}
		
		super.onBlockClicked(worldIn, pos, playerIn);
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		for(int i = 0; i < 50; i++){
			addGlitterParticleNetwork(worldIn, pos);
		}
		super.onBlockHarvested(worldIn, pos, state, player);
	}
	
    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
    	if(entityIn instanceof EntityLivingBase){
    		entityIn.attackEntityFrom(DamageSource.generic, 0.5F);
    	}

    }
    
    @Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        setDefaultStatus(worldIn, pos, state);
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	IBlockState iblockstate = worldIn.getBlockState(pos.offset(facing.getOpposite()));
    	
    	if(iblockstate.getBlock() instanceof Crystall){
    		EnumFacing enumfacing = (EnumFacing)iblockstate.getValue(FACING);

            if (enumfacing == facing)
            {
                return this.getDefaultState().withProperty(FACING, facing.getOpposite());
            }
    	}
        
        return this.getDefaultState().withProperty(FACING, facing);
    }
    
    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
}
