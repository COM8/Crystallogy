package de.comeight.crystallogy.tileEntitys;

import de.comeight.crystallogy.particles.LightParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

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
		if(tick == 8){
			tick = 0;
			addParticle();
		}
		else{
			tick++;
		}
	}
	
	private void addParticle(){
		LightParticle lP = new LightParticle(worldObj, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
		Minecraft.getMinecraft().effectRenderer.addEffect(lP);
	}
	
}
