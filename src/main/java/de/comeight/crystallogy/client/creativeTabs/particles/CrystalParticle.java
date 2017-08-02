package de.comeight.crystallogy.client.creativeTabs.particles;

import de.comeight.crystallogy.util.Util;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class CrystalParticle extends BaseParticleExtended {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final ResourceLocation RL_CRYSTAL_PARTICLE = new ResourceLocation("crystallogy:particles/j/j");
    public static final String ID_CRYSTAL_PARTICLE = "crystallogy:crystalParticle";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalParticle(World worldIn, Vec3d pos) {
        super(RL_CRYSTAL_PARTICLE, ID_CRYSTAL_PARTICLE, worldIn, pos);

        this.particleMaxAge = 60 + Util.RANDOM.nextInt(30);
        this.animationSpeed = particleMaxAge / 32;
        this.motionX = -0.005 + Util.RANDOM.nextDouble() * 0.01;
        this.motionY = -0.005 + Util.RANDOM.nextDouble() * 0.01;
        this.motionZ = -0.005 + Util.RANDOM.nextDouble() * 0.01;
    }

    public CrystalParticle() {
        this(null, new Vec3d(0.0, 0.0,0.0));
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}