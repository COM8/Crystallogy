package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.network.MessageToClient;
import de.comeight.crystallogy.network.MessageToServer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageHandlerOnServerHandler implements IMessageHandler<MessageToServer, IMessage>{
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnServerHandler() {
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	void processMessage(MessageToServer message, EntityPlayerMP sendingPlayer) {
		//An alle Spieler senden
		int dimension = sendingPlayer.dimension;
	    MinecraftServer minecraftServer = sendingPlayer.mcServer;
	    for (EntityPlayerMP player : minecraftServer.getPlayerList().getPlayerList()) {
	    	MessageToClient msg;
	    	//System.out.println(player.getName() + ", " + message.messageType);
	    	switch (message.messageType) {
	    		case MessageToServer.PARTICLE:
	    			msg = new MessageToClient(message.getNetworkParticle());
					break;
	    		
	    		case MessageToServer.TILEENTITYPARTICLEUPDATERECIPE:
					msg = new MessageToClient(MessageToClient.TILEENTITYPARTICLEUPDATERECIPE, message.getTilePos(), message.isStatus());
					break;
	    		
	    		case MessageToServer.TILEENTITYPARTICLEUPDATE:
					msg = new MessageToClient(MessageToClient.TILEENTITYPARTICLEUPDATE, message.getTilePos(), message.isStatus());
					break;
					
	    		case MessageToServer.ITEMSTACK:
	    			msg = new MessageToClient(MessageToClient.ITEMSTACK, message.getTilePos(), message.getStack());
					break;
				
	    		default:
					System.out.println("Unknown messageType: " + message.messageType);
					return;
			}
	      if (dimension == player.dimension) {
	    	  CommonProxy.NETWORKWRAPPER.sendTo(msg, player);
	      }
	    }
	    
	    //Spawn Particle
	    //...But only on Client Side
	    //System.out.println("Serverside ausgeführt.");
	}
	
	@Override
	public IMessage onMessage(final MessageToServer message, MessageContext ctx) {
		//System.out.println("Nachricheneingang Server.");
		if(ctx.side != Side.SERVER){
			System.err.println("ParticleMessageToServer received on wrong side:" + ctx.side);
		    return null;
		}
		if(!message.isMessageValid()){
			System.err.println("ParticleMessageToServer was invalid " + message.toString());
		    return null;
		}
		final EntityPlayerMP sendingPlayer = ctx.getServerHandler().playerEntity;
	    if (sendingPlayer == null) {
	      System.err.println("EntityPlayerMP was null when ParticleMessageToServer was received");
	      return null;
	    }
	    
	    final WorldServer playerWorldServer = sendingPlayer.getServerForPlayer();
	    playerWorldServer.addScheduledTask(new Runnable() {
	      public void run() {
	        processMessage(message, sendingPlayer);
	      }
	    });

	    return null;
	}

}
