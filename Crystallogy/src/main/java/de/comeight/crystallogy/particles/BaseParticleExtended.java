package de.comeight.crystallogy.particles;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.world.World;

public class BaseParticleExtended extends BaseParticle {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String NAME = "baseParticleExtended";
	
	protected int stage;
	protected int tick;

	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseParticleExtended(String name, World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
		super(name, world, x, y, z, velocityX, velocityY, velocityZ);
		this.stage = Utilities.getRandInt(0, 4);
		this.tick = Utilities.getRandInt(5, 10);
		this.particleMaxAge = Utilities.getRandInt(20, 50);
		this.particleScale = Utilities.getRandFloat(0.5F, 1.5F);
	}

	public BaseParticleExtended() {
		super();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void onUpdate() {
		super.onUpdate();
		nextPrticleDirection();
		
		switch (stage) {
		case 0:
			moveEntity(this.motionX / 3, 0, 0);
			break;
		case 1:
			moveEntity(0, this.motionY / 5, 0);
			break;
		case 2:
			moveEntity(0, 0, this.motionZ / 3);
			break;
		case 3:
			moveEntity(-this.motionX / 3 , 0, 0);
			break;
		case 4:
			moveEntity(0, 0, -this.motionZ / 3);
			break;
		default:
			break;
		}
		
	}

	protected void nextPrticleDirection(){
		tick--;
		if(tick < 0){
			this.tick = Utilities.getRandInt(5, 10);
			this.stage = Utilities.getRandInt(0, 4);
			if(stage > 4){
				stage = 0;
			}
		}
	}
	
}
