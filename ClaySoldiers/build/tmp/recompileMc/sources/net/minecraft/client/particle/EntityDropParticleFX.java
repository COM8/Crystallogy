package net.minecraft.client.particle;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityDropParticleFX extends EntityFX
{
    /** the material type for dropped items/blocks */
    private Material materialType;
    /** The height of the current bob */
    private int bobTimer;

    protected EntityDropParticleFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, Material p_i1203_8_)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
        this.xSpeed = this.ySpeed = this.zSpeed = 0.0D;

        if (p_i1203_8_ == Material.water)
        {
            this.particleRed = 0.0F;
            this.particleGreen = 0.0F;
            this.particleBlue = 1.0F;
        }
        else
        {
            this.particleRed = 1.0F;
            this.particleGreen = 0.0F;
            this.particleBlue = 0.0F;
        }

        this.setParticleTextureIndex(113);
        this.setSize(0.01F, 0.01F);
        this.particleGravity = 0.06F;
        this.materialType = p_i1203_8_;
        this.bobTimer = 40;
        this.particleMaxAge = (int)(64.0D / (Math.random() * 0.8D + 0.2D));
        this.xSpeed = this.ySpeed = this.zSpeed = 0.0D;
    }

    public int getBrightnessForRender(float p_189214_1_)
    {
        return this.materialType == Material.water ? super.getBrightnessForRender(p_189214_1_) : 257;
    }

    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.materialType == Material.water)
        {
            this.particleRed = 0.2F;
            this.particleGreen = 0.3F;
            this.particleBlue = 1.0F;
        }
        else
        {
            this.particleRed = 1.0F;
            this.particleGreen = 16.0F / (float)(40 - this.bobTimer + 16);
            this.particleBlue = 4.0F / (float)(40 - this.bobTimer + 8);
        }

        this.ySpeed -= (double)this.particleGravity;

        if (this.bobTimer-- > 0)
        {
            this.xSpeed *= 0.02D;
            this.ySpeed *= 0.02D;
            this.zSpeed *= 0.02D;
            this.setParticleTextureIndex(113);
        }
        else
        {
            this.setParticleTextureIndex(112);
        }

        this.moveEntity(this.xSpeed, this.ySpeed, this.zSpeed);
        this.xSpeed *= 0.9800000190734863D;
        this.ySpeed *= 0.9800000190734863D;
        this.zSpeed *= 0.9800000190734863D;

        if (this.particleMaxAge-- <= 0)
        {
            this.setExpired();
        }

        if (this.isCollided)
        {
            if (this.materialType == Material.water)
            {
                this.setExpired();
                this.worldObj.spawnParticle(EnumParticleTypes.WATER_SPLASH, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
            }
            else
            {
                this.setParticleTextureIndex(114);
            }

            this.xSpeed *= 0.699999988079071D;
            this.zSpeed *= 0.699999988079071D;
        }

        BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
        IBlockState iblockstate = this.worldObj.getBlockState(blockpos);
        Material material = iblockstate.getMaterial();

        if (material.isLiquid() || material.isSolid())
        {
            double d0 = 0.0D;

            if (iblockstate.getBlock() instanceof BlockLiquid)
            {
                d0 = (double)BlockLiquid.getLiquidHeightPercent(((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue());
            }

            double d1 = (double)(MathHelper.floor_double(this.posY) + 1) - d0;

            if (this.posY < d1)
            {
                this.setExpired();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static class LavaFactory implements IParticleFactory
        {
            public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
            {
                return new EntityDropParticleFX(worldIn, xCoordIn, yCoordIn, zCoordIn, Material.lava);
            }
        }

    @SideOnly(Side.CLIENT)
    public static class WaterFactory implements IParticleFactory
        {
            public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
            {
                return new EntityDropParticleFX(worldIn, xCoordIn, yCoordIn, zCoordIn, Material.water);
            }
        }
}