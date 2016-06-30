package de.comeight.crystallogy.tileEntitys;

import de.comeight.crystallogy.particles.ParticleLight;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCrystallLight extends TileEntity implements ITickable{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private int tick;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityCrystallLight() {
		this.tick = 0;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void update() {
		if(!worldObj.isRemote){
			return;
		}
		if(tick == 8){
			tick = 0;
			addParticle();
		}
		else{
			tick++;
		}
	}
	
	@SideOnly(Side.CLIENT)
	private void addParticle(){
		ParticleLight pL = new ParticleLight(worldObj, new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
		Minecraft.getMinecraft().effectRenderer.addEffect(pL);
	}
	
}
