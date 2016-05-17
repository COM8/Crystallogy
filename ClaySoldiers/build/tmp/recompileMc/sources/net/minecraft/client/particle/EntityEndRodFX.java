package net.minecraft.client.particle;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityEndRodFX extends EntityAnimatedFX
{
    public EntityEndRodFX(World p_i46580_1_, double p_i46580_2_, double p_i46580_4_, double p_i46580_6_, double p_i46580_8_, double p_i46580_10_, double p_i46580_12_)
    {
        super(p_i46580_1_, p_i46580_2_, p_i46580_4_, p_i46580_6_, 176, 8, -5.0E-4F);
        this.xSpeed = p_i46580_8_;
        this.ySpeed = p_i46580_10_;
        this.zSpeed = p_i46580_12_;
        this.particleScale *= 0.75F;
        this.particleMaxAge = 60 + this.rand.nextInt(12);
        this.setColorFade(15916745);
    }

    public void moveEntity(double x, double y, double z)
    {
        this.setEntityBoundingBox(this.getEntityBoundingBox().offset(x, y, z));
        this.resetPositionToBB();
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
        {
            public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
            {
                return new EntityEndRodFX(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            }
        }
}