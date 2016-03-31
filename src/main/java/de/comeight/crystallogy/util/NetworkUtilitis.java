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
		int dimension = sendingPlayer.dimension;
		MinecraftServer minecraftServer = sendingPlayer.mcServer;
		for (EntityPlayerMP player : minecraftServer.getPlayerList().getPlayerList()) {
			if (dimension == player.dimension) {
				CommonProxy.NETWORKWRAPPER.sendTo(msg, player);
			}
		}
	}
	
}
