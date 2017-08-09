package de.comeight.crystallogy.client.particles;

import de.comeight.crystallogy.util.Logger;
import de.comeight.crystallogy.util.RGBColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class BaseParticle extends Particle {
    //-----------------------------------------------Attributes:--------------------------------------------
    private boolean canDie;
    public final ResourceLocation RL;
    public final String ID;
    protected int particleTexturesCount;
    protected double tIndex;
    protected double animationSpeed;

    //-----------------------------------------------Constructor:-------------------------------------------
    protected BaseParticle(ResourceLocation rL, String id, World worldIn, Vec3d pos) {
        super(worldIn, pos.x, pos.y, pos.z);

        this.canDie = true;
        this.RL = rL;
        this.ID = id;
        this.particleTexturesCount = 32;
        this.tIndex = 0;
        this.animationSpeed = 1.0;

        if(worldIn != null) {
            setTexture();
        }
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------
    @Override
    public int getFXLayer() {
        return 1;
    }

    private void setTexture(){
        if(RL != null){
            particleTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(RL.toString());
        }
        else{
            Logger.error("Unable to find the TextureAtlasSprite for: " + ID + "!");
            particleTexture = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
        }
    }

    public void setParticleAge(int particleAge) {
        this.particleAge = particleAge;
    }

    public void setCanDie(boolean canDie){
        this.canDie = canDie;
    }

    public void setRGBColor(RGBColor color) {
        setRBGColorF(color.R, color.G, color.B);
    }

    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public void onUpdate() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if(canDie){
            if(this.particleMaxAge <= this.particleAge){
                this.setExpired();
            }
            this.particleAge++;
        }
    }

    //-----------------------------------------------Events:------------------------------------------------

}