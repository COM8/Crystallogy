package de.comeight.crystallogy.network.handler.Client;

import de.comeight.crystallogy.network.NetworkPacketInfusionRecipeStatus;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleA;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.particles.ParticleC;
import de.comeight.crystallogy.particles.ParticleD;
import de.comeight.crystallogy.particles.ParticleE;
import de.comeight.crystallogy.particles.ParticleF;
import de.comeight.crystallogy.particles.ParticleNColor;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageHandlerOnClientParticle implements IMessageHandler<NetworkPacketParticle, IMessage> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnClientParticle() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public IMessage onMessage(final NetworkPacketParticle message, MessageContext ctx) {
		// System.out.println("Nachricheneingang Client.");
		if (ctx.side != Side.CLIENT) {
			System.err.println("NetworkPacketParticle received on wrong side: " + ctx.side);
			return null;
		}
		if (!message.isMessageValid()) {
			System.err.println("NetworkPacketParticle was invalid" + message.toString());
			return null;
		}
		Minecraft minecraft = Minecraft.getMinecraft();
		final WorldClient worldClient = minecraft.theWorld;
		minecraft.addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, worldClient);
			}
		});
		return null;
	}
	
	private void processMessage(NetworkPacketParticle message, WorldClient worldClient) {
		NetworkParticle nP = message.getNetworkParticle();
		Vec3d pos = nP.getParticle().getPos();
		String type = nP.getType();
		
		if(type.equals(ParticleA.NAME)){
			Vec3d size = nP.getSize();
			for(int i = 0; i < nP.getNumberOfParticle(); i++){
				ParticleA gP = new ParticleA(worldClient, pos.xCoord + Utilities.getRandDouble(-size.xCoord, size.xCoord), pos.yCoord + Utilities.getRandDouble(0.0, size.yCoord), pos.zCoord + Utilities.getRandDouble(-size.zCoord, size.zCoord), 0.2, 0.2, 0.2);
				gP.setParticleMaxAge(Utilities.getRandInt((int)(nP.getParticle().getParticleMaxAge()*0.75), nP.getParticle().getParticleMaxAge()));
				gP.setRBGColorF(nP.getParticle().getRedColorF(), nP.getParticle().getGreenColorF(), nP.getParticle().getBlueColorF());
				Minecraft.getMinecraft().effectRenderer.addEffect(gP);
			}
		}
		else if(type.equals(ParticleB.NAME)){
			Vec3d size = nP.getSize();
			for(int i = 0; i < nP.getNumberOfParticle(); i++){
				ParticleB gP = new ParticleB(worldClient, pos.xCoord + Utilities.getRandDouble(-size.xCoord, size.xCoord), pos.yCoord + Utilities.getRandDouble(0.0, size.yCoord), pos.zCoord + Utilities.getRandDouble(-size.zCoord, size.zCoord), 0.2, 0.2, 0.2);
				gP.setParticleMaxAge(Utilities.getRandInt((int)(nP.getParticle().getParticleMaxAge()*0.75), nP.getParticle().getParticleMaxAge()));
				gP.setRBGColorF(nP.getParticle().getRedColorF(), nP.getParticle().getGreenColorF(), nP.getParticle().getBlueColorF());
				Minecraft.getMinecraft().effectRenderer.addEffect(gP);
			}
		}
		else if(type.equals(ParticleC.NAME)){
			Vec3d size = nP.getSize();
			for(int i = 0; i < nP.getNumberOfParticle(); i++){
				ParticleC gP = new ParticleC(worldClient, pos.xCoord + Utilities.getRandDouble(-size.xCoord, size.xCoord), pos.yCoord + Utilities.getRandDouble(0.0, size.yCoord), pos.zCoord + Utilities.getRandDouble(-size.zCoord, size.zCoord), 0.2, 0.2, 0.2);
				gP.setParticleMaxAge(Utilities.getRandInt((int)(nP.getParticle().getParticleMaxAge()*0.75), nP.getParticle().getParticleMaxAge()));
				gP.setRBGColorF(nP.getParticle().getRedColorF(), nP.getParticle().getGreenColorF(), nP.getParticle().getBlueColorF());
				Minecraft.getMinecraft().effectRenderer.addEffect(gP);
			}
		}
		else if(type.equals(ParticleD.NAME)){
			Vec3d size = nP.getSize();
			for(int i = 0; i < nP.getNumberOfParticle(); i++){
				ParticleC gP = new ParticleC(worldClient, pos.xCoord + Utilities.getRandDouble(-size.xCoord, size.xCoord), pos.yCoord + Utilities.getRandDouble(0.0, size.yCoord), pos.zCoord + Utilities.getRandDouble(-size.zCoord, size.zCoord), 0.2, 0.2, 0.2);
				gP.setParticleMaxAge(Utilities.getRandInt((int)(nP.getParticle().getParticleMaxAge()*0.75), nP.getParticle().getParticleMaxAge()));
				gP.setRBGColorF(nP.getParticle().getRedColorF(), nP.getParticle().getGreenColorF(), nP.getParticle().getBlueColorF());
				Minecraft.getMinecraft().effectRenderer.addEffect(gP);
			}
		}
		else if(type.equals(ParticleE.NAME)){
			Vec3d size = nP.getSize();
			for(int i = 0; i < nP.getNumberOfParticle(); i++){
				ParticleC gP = new ParticleC(worldClient, pos.xCoord + Utilities.getRandDouble(-size.xCoord, size.xCoord), pos.yCoord + Utilities.getRandDouble(0.0, size.yCoord), pos.zCoord + Utilities.getRandDouble(-size.zCoord, size.zCoord), 0.2, 0.2, 0.2);
				gP.setParticleMaxAge(Utilities.getRandInt((int)(nP.getParticle().getParticleMaxAge()*0.75), nP.getParticle().getParticleMaxAge()));
				gP.setRBGColorF(nP.getParticle().getRedColorF(), nP.getParticle().getGreenColorF(), nP.getParticle().getBlueColorF());
				Minecraft.getMinecraft().effectRenderer.addEffect(gP);
			}
		}
		else if(type.equals(ParticleF.NAME)){
			Vec3d size = nP.getSize();
			for(int i = 0; i < nP.getNumberOfParticle(); i++){
				ParticleC gP = new ParticleC(worldClient, pos.xCoord + Utilities.getRandDouble(-size.xCoord, size.xCoord), pos.yCoord + Utilities.getRandDouble(0.0, size.yCoord), pos.zCoord + Utilities.getRandDouble(-size.zCoord, size.zCoord), 0.2, 0.2, 0.2);
				gP.setParticleMaxAge(Utilities.getRandInt((int)(nP.getParticle().getParticleMaxAge()*0.75), nP.getParticle().getParticleMaxAge()));
				gP.setRBGColorF(nP.getParticle().getRedColorF(), nP.getParticle().getGreenColorF(), nP.getParticle().getBlueColorF());
				Minecraft.getMinecraft().effectRenderer.addEffect(gP);
			}
		}
		else if(type.equals(ParticleNColor.NAME)){
			Vec3d size = nP.getSize();
			for(int i = 0; i < nP.getNumberOfParticle(); i++){
				ParticleNColor gP = new ParticleNColor(worldClient, pos.xCoord + Utilities.getRandDouble(-size.xCoord, size.xCoord), pos.yCoord + Utilities.getRandDouble(0.0, size.yCoord), pos.zCoord + Utilities.getRandDouble(-size.zCoord, size.zCoord), 0.2, 0.2, 0.2);
				gP.setParticleMaxAge(Utilities.getRandInt((int)(nP.getParticle().getParticleMaxAge()*0.75), nP.getParticle().getParticleMaxAge()));
				gP.setRBGColorF(Utilities.getRandFloat(0.0F, 1.0F), Utilities.getRandFloat(0.0F, 1.0F), Utilities.getRandFloat(0.0F, 1.0F));
				Minecraft.getMinecraft().effectRenderer.addEffect(gP);
			}
		}
		else{
			System.err.println("Unknown particle Type: " + type);
		}
	}
	
}
