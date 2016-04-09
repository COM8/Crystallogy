package de.comeight.crystallogy.blocks;

import java.util.Random;

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
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class Crystall extends BaseBlockCutout{
	
	//-----------------------------------------------Variabeln:---------------------------------------------
	//public final static String ID = "crystall";
	protected RGBColor color;
	protected int chance;
	//-----------------------------------------------Constructor:-------------------------------------------
	public Crystall(String ID) {
		super(Material.glass, ID);
		
		color = new RGBColor(1.0F, 1.0F, 1.0F);
		this.chance = 5;
		
		this.setHarvestLevel("pickaxe", 3);
		this.setLightLevel(0.3F);
		this.setHardness(5.0F);
		this.setStepSound(SoundType.GLASS);
		
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String getHarvestTool(IBlockState state) {
		return "pickaxe";
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.3, 0.0, 0.25, 0.7, 0.6, 0.9);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		if(worldIn.isRemote){	//Client Side Welt?
			addGlitterParticleChance(worldIn, pos, chance);
			addJumpParticleBetweenCrystallsChance(worldIn, pos, 20);
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
}
