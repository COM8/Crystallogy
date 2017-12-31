package de.comeight.crystallogy.client.particles;

import de.comeight.crystallogy.util.Util;
import de.comeight.crystallogy.util.enums.EnumParticle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class BaseParticleExtended extends BaseParticle {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    protected BaseParticleExtended(ResourceLocation rL, String id, World worldIn, Vec3d pos, EnumParticle particle) {
        super(rL, id, worldIn, pos, particle);

        this.particleMaxAge = 30 + Util.RANDOM.nextInt(30);
        this.animationSpeed = particleMaxAge / 32;
        this.particleScale = 0.5F + Util.RANDOM.nextFloat();
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public void onUpdate() {
        super.onUpdate();
        move(motionX, motionY, motionZ);
    }

    //-----------------------------------------------Events:------------------------------------------------

}