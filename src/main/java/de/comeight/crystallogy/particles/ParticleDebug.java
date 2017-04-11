package de.comeight.crystallogy.particles;

import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleDebug extends BaseParticle {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation RL_PARTICLE_DEBUG = new ResourceLocation("crystallogy:particles/c/c");

	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleDebug(World worldIn, Vec3d pos) {
		super(ParticleInformation.ID_PARTICLE_DEBUG, RL_PARTICLE_DEBUG, worldIn, pos);
		
		this.countParticleTextures = 32;
	}

	public ParticleDebug() {
		super(ParticleInformation.ID_PARTICLE_DEBUG, RL_PARTICLE_DEBUG);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp) {
		return new ParticleDebug(worldIn, pos);
	}
	
	@Override
	public void renderParticle(VertexBuffer worldRendererIn, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		super.renderParticle(worldRendererIn, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
		float textureXMin = (float)this.particleTextureIndexX / 16.0F;
        float textureXMax = textureXMin + 0.0624375F;
        float textureYMin = (float)this.particleTextureIndexY / 16.0F;
        float textureYMax = textureYMin + 0.0624375F;
        float particleScale = 0.1F * this.particleScale;

        if (this.particleTexture != null)
        {
            textureXMin = this.particleTexture.getMinU();
            textureXMax = this.particleTexture.getMaxU();
            textureYMin = this.particleTexture.getMinV();
            textureYMax = this.particleTexture.getMaxV();
        }

        float renderPosX = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
        float renderPosY = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
        float renderPosZ = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
        int brightness = this.getBrightnessForRender(partialTicks);
        int brightnessHex = brightness >> 16 & 65535;
        int brightnessFinal = brightness;
        
        Vec3d[] avec3d = new Vec3d[] {	new Vec3d(	(double)(-rotationX * particleScale - rotationXY * particleScale),
        											(double)(-rotationZ * particleScale),
        											(double)(-rotationYZ * particleScale - rotationXZ * particleScale)),
        								new Vec3d(	(double)(-rotationX * particleScale + rotationXY * particleScale), 
        											(double)(rotationZ * particleScale), 
        											(double)(-rotationYZ * particleScale + rotationXZ * particleScale)), 
        								new Vec3d(	(double)(rotationX * particleScale + rotationXY * particleScale), 
        											(double)(rotationZ * particleScale), 
        											(double)(rotationYZ * particleScale + rotationXZ * particleScale)), 
        								new Vec3d(	(double)(rotationX * particleScale - rotationXY * particleScale), 
        											(double)(-rotationZ * particleScale), 
        											(double)(rotationYZ * particleScale - rotationXZ * particleScale))};

        if (this.particleAngle != 0.0F)
        {
            float f8 = this.particleAngle + (this.particleAngle - this.prevParticleAngle) * partialTicks;
            float f9 = MathHelper.cos(f8 * 0.5F);
            float f10 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.xCoord;
            float f11 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.yCoord;
            float f12 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.zCoord;
            Vec3d vec3d = new Vec3d((double)f10, (double)f11, (double)f12);

            for (int l = 0; l < 4; ++l)
            {
                avec3d[l] = vec3d.scale(2.0D * avec3d[l].dotProduct(vec3d)).add(avec3d[l].scale((double)(f9 * f9) - vec3d.dotProduct(vec3d))).add(vec3d.crossProduct(avec3d[l]).scale((double)(2.0F * f9)));
            }
        }
  
        
        
        worldRendererIn.pos((double)renderPosX + avec3d[0].xCoord, (double)renderPosY + avec3d[0].yCoord, (double)renderPosZ + avec3d[0].zCoord).tex((double)textureXMax, (double)textureYMax).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(brightnessHex, brightnessFinal).endVertex();
        worldRendererIn.pos((double)renderPosX + avec3d[1].xCoord, (double)renderPosY + avec3d[1].yCoord, (double)renderPosZ + avec3d[1].zCoord).tex((double)textureXMax, (double)textureYMin).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(brightnessHex, brightnessFinal).endVertex();
        worldRendererIn.pos((double)renderPosX + avec3d[2].xCoord, (double)renderPosY + avec3d[2].yCoord, (double)renderPosZ + avec3d[2].zCoord).tex((double)textureXMin, (double)textureYMin).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(brightnessHex, brightnessFinal).endVertex();
        worldRendererIn.pos((double)renderPosX + avec3d[3].xCoord, (double)renderPosY + avec3d[3].yCoord, (double)renderPosZ + avec3d[3].zCoord).tex((double)textureXMin, (double)textureYMax).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(brightnessHex, brightnessFinal).endVertex();
	}

}
