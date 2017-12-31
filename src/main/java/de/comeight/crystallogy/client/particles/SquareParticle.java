package de.comeight.crystallogy.client.particles;

import de.comeight.crystallogy.Crystallogy;
import de.comeight.crystallogy.util.Util;
import de.comeight.crystallogy.util.enums.EnumParticle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SquareParticle extends BaseParticleExtended {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final ResourceLocation RL_SQUARE_PARTICLE = new ResourceLocation(Crystallogy.MOD_ID + ":particles/square/square");
    public static final String ID_SQUARE_PARTICLE = Crystallogy.MOD_ID + ":squareParticle";

    //-----------------------------------------------Constructor:-------------------------------------------
    public SquareParticle(World worldIn, Vec3d pos) {
        super(RL_SQUARE_PARTICLE, ID_SQUARE_PARTICLE, worldIn, pos, EnumParticle.SQUARE_PARTICLE);
        this.particleTexturesCount = 1;

        this.motionX = -0.005 + Util.RANDOM.nextDouble() * 0.01;
        this.motionY = -0.005 + Util.RANDOM.nextDouble() * 0.01;
        this.motionZ = -0.005 + Util.RANDOM.nextDouble() * 0.01;

        this.particleScale = Util.RANDOM.nextFloat() * 0.2F;
        this.particleMaxAge = 60 + Util.RANDOM.nextInt(100);
        particleGravity = 1.0F;
        setRBGColorF(0.5F + Util.RANDOM.nextFloat() * 0.5F, Util.RANDOM.nextFloat() * 0.2F, 0.0F);
        setRBGColorF(Util.RANDOM.nextFloat(), Util.RANDOM.nextFloat(), Util.RANDOM.nextFloat());

    }

    public SquareParticle() {
        this(null, new Vec3d(0.0, 0.0,0.0));
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public void onUpdate() {
        super.onUpdate();
        this.particleScale *= 0.99F;
        if(particleScale < 0.05) {
            this.setExpired();
        }
        if(motionY > -0.02) {
            motionY -= 0.001 * particleScale * 200;
        }
    }

    @Override
    public BaseParticle clone() {
        return new SquareParticle(world, new Vec3d(posX, posY, posZ));
    }

    //-----------------------------------------------Events:------------------------------------------------

}