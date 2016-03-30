package de.comeight.crystallogy.network;

import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

public class MessageToClient extends MessageToServer{
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageToClient(NetworkParticle nP) {
		super(nP);
	}
	
	public MessageToClient(){
		super();
	}
	
	public MessageToClient(int messageType, Vec3d tilePos, boolean status) {
		super(messageType, tilePos , status);
	}
	public MessageToClient(int messageType, Vec3d tilePos, ItemStack stack) {
		super(messageType, tilePos, stack);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString()
	{
		switch (messageType) {
			case PARTICLE:
				return "ParticleMessageToClient[NetworkParticle=" + nP.toString() + "]";
				
			case TILEENTITYPARTICLEUPDATERECIPE:
				return "TileEntityUpdateMessageToClient[Pos;status =" + tilePos.toString() + ";" + status + "]";
				
			case TILEENTITYPARTICLEUPDATE:
				return "TileEntityUpdateMessageToClient[Pos;status =" + tilePos.toString() + ";" + status + "]";
				
			case ITEMSTACK:
				return "RemoveItemToClient[Pos=" + tilePos.toString() + "]";
				
			default:
				return "Unknown messageType in toString";
		}
	}
}
