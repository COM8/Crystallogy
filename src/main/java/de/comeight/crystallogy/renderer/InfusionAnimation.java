package de.comeight.crystallogy.renderer;

import java.util.ArrayList;

import de.comeight.crystallogy.particles.InfusionParticle;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class InfusionAnimation {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BlockPos center;
	private BlockPos[] surrounding;
	
	private World world;
	private int timeInMS;
	
	private boolean active;
	private InfusionParticle[] infusionParticles;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionAnimation(BlockPos center, BlockPos[] surrounding, World world, int timeInMS) {
		this.center = center;
		this.surrounding = surrounding;
		
		this.world = world;
		this.timeInMS = timeInMS;
		
		this.active = false;
		this.infusionParticles = null;
		
		setActiveInfusers();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public boolean isActive() {
		return active;
	}
	
	private void setActiveInfusers(){
		ArrayList<BlockPos> list = new ArrayList<BlockPos>();
		for (int i = 0; i < surrounding.length; i++) {
			if(surrounding[i] != null){
				TileEntity tE = world.getTileEntity(surrounding[i]);
				TileEnityInfuserBlock tEI;
				if(tE instanceof TileEnityInfuserBlock){
					tEI = (TileEnityInfuserBlock) tE;
					if(tEI.getStackInSlot(0) != null){
						list.add(surrounding[i]);
					}
				}
			}
			else{
				System.out.println("null");
			}
		}
		
		BlockPos[] a = new BlockPos[list.size()];
		for (int i = 0; i < list.size(); i++) {
			a[i] = list.get(i);
		}
		surrounding = a;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void start(){
		if(active){
			return;
		}
		
		infusionParticles = new InfusionParticle[surrounding.length];
		for (int i = 0; i < surrounding.length; i++) {
			if (center != null && surrounding[i] != null) {
				BlockPos p = surrounding[i];
				this.infusionParticles[i] = new InfusionParticle(world, 0.0, 0.0, 0.0, new Vec3d(p.getX() + 0.5, p.getY() + 0.75, p.getZ() + 0.5), new Vec3d(center.getX() + 0.5, center.getY() + 0.75, center.getZ() + 0.5), timeInMS);
				this.infusionParticles[i].setRBGColorF(Utilities.getRandFloat(0.0F, 1.0F), Utilities.getRandFloat(0.0F, 1.0F), Utilities.getRandFloat(0.0F, 1.0F));
				Minecraft.getMinecraft().effectRenderer.addEffect(infusionParticles[i]);
			}
		}
		
		active = true;
	}
	
	public void stop(){
		if(!active){
			return;
		}
		
		for (int i = 0; i < infusionParticles.length; i++) {
			if(infusionParticles[i] != null){
				infusionParticles[i].setExpired();
			}
		}
		
		active = false;
	}
	
}
