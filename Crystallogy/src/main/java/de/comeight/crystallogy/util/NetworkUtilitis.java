package de.comeight.crystallogy.util;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.network.BaseNetworkPacket;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class NetworkUtilitis {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void sendToAllPlayers(BaseNetworkPacket msg, EntityPlayerMP sendingPlayer){
		if(sendingPlayer == null){
			return;
		}

		int dimension = sendingPlayer.dimension;
		MinecraftServer minecraftServer = sendingPlayer.mcServer;
		for (EntityPlayerMP player : minecraftServer.getPlayerList().getPlayerList()) {
			if (dimension == player.dimension) {
				CommonProxy.NETWORKWRAPPER.sendTo(msg, player);
			}
		}
	}
	
	public static void sendAllAround(BaseNetworkPacket msg, boolean remote){
		if(!remote){
			CommonProxy.NETWORKWRAPPER.sendToAll(msg);
		}
		else{
			CommonProxy.NETWORKWRAPPER.sendToServer(msg);
		}
	}
	
	public static void sendToServer(BaseNetworkPacket msg){
		CommonProxy.NETWORKWRAPPER.sendToServer(msg);
	}
	
}
