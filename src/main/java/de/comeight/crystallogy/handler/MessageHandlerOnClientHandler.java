package de.comeight.crystallogy.handler;

import net.minecraft.util.math.Vec3d;

import de.comeight.crystallogy.network.MessageToClient;
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
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageHandlerOnClientHandler implements IMessageHandler<MessageToClient, IMessage>{
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnClientHandler() {
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public IMessage onMessage(final MessageToClient message, MessageContext ctx) {
		//System.out.println("Nachricheneingang Client.");
		if (ctx.side != Side.CLIENT) {
			System.err.println("TargetEffectMessageToClient received on wrong side:" + ctx.side);
			return null;
		}
		if (!message.isMessageValid()) {
			System.err.println("TargetEffectMessageToClient was invalid" + message.toString());
			return null;
		}
		Minecraft minecraft = Minecraft.getMinecraft();
		final WorldClient worldClient = minecraft.theWorld;
		minecraft.addScheduledTask(new Runnable() {
			public void run() {
				processMessage(worldClient, message);
			}
		});

		return null;
	}
	
	private void processMessage(WorldClient worldClient, MessageToClient message) {	
		switch (message.messageType) {
			case MessageToClient.PARTICLE:
				particleMessage(worldClient, message);
				break;
				
			case MessageToClient.TILEENTITYPARTICLEUPDATERECIPE:
				tileEntityParticleMessageRecipe(worldClient, message);
				break;
				
			case MessageToClient.TILEENTITYPARTICLEUPDATE:
				tileEntityParticleMessage(worldClient, message);
				break;
				
			case MessageToClient.ITEMSTACK:
				setItemMessage(worldClient, message);
				break;
				
			default:
				System.out.println("Unknown messageType: " + message.messageType);
				break;
		}
		
	    //System.out.println("Clientside ausgeführt!");
	}
	
	private void particleMessage(WorldClient worldClient, MessageToClient message){
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
				gP.setRBGColorF(nP.getParticle().getRedColorF(), nP.getParticle().getGreenColorF(), nP.getParticle().getBlueColorF());
				Minecraft.getMinecraft().effectRenderer.addEffect(gP);
			}
		}
		else{
			System.err.println("Unknown particle Type: " + type);
		}
	}
	
	private void tileEntityParticleMessageRecipe(WorldClient worldClient, MessageToClient message){
		Vec3d pos = message.getTilePos();
		TileEntity tE = worldClient.getTileEntity(new BlockPos(pos.xCoord, pos.yCoord, pos.zCoord));
		if(tE instanceof TileEnityInfuserBlock){
			TileEnityInfuserBlock t =  (TileEnityInfuserBlock) tE;
			t.changeRecipeStatus(message.isStatus(), worldClient);
		}
		else{
			System.out.println("Wrong TileEntity!");
		}
	}
	
	private void tileEntityParticleMessage(WorldClient worldClient, MessageToClient message){
		Vec3d pos = message.getTilePos();
		TileEntity tE = worldClient.getTileEntity(new BlockPos(pos.xCoord, pos.yCoord, pos.zCoord));
		if(tE instanceof TileEnityInfuserBlock){
			TileEnityInfuserBlock t =  (TileEnityInfuserBlock) tE;
			t.changeParticleActive(message.isStatus());
		}
		else{
			System.out.println("Wrong TileEntity! " + message.toString());
		}
	}
	
	private void setItemMessage(WorldClient worldClient, MessageToClient message){
		Vec3d pos = message.getTilePos();
		TileEntity tE = worldClient.getTileEntity(new BlockPos(pos.xCoord, pos.yCoord, pos.zCoord));
		if(tE instanceof TileEnityInfuserBlock){
			TileEnityInfuserBlock t =  (TileEnityInfuserBlock) tE;
			t.setInventorySlotContents(0, message.getStack());;
		}
		else{
			System.out.println("Wrong TileEntity!");
		}
	}
}
