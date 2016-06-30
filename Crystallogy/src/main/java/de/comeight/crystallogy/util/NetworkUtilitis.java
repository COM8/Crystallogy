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
	/**
	 * Sends a {@link BaseNetworkPacket} to all players in the same dimension like the sendingPlayer
	 * 
	 * @param msg the {@link BaseNetworkPacket} that should get send arround
	 * @param sendingPlayer the {@link EntityPlayerMP} who sends this {@link BaseNetworkPacket}
	 */
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
	
	/**
	 * Sends a {@link BaseNetworkPacket} to everybody.
	 * Client: A client sends this {@link BaseNetworkPacket} to all other clients and to the server
	 * Server: A server sends this {@link BaseNetworkPacket} to every client
	 * 
	 * @param msg the {@link BaseNetworkPacket} that should get send arround
	 * @param remote wether the world is remote or not
	 */
	public static void sendAllAround(BaseNetworkPacket msg, boolean remote){
		if(!remote){
			CommonProxy.NETWORKWRAPPER.sendToAll(msg);
		}
		else{
			CommonProxy.NETWORKWRAPPER.sendToServer(msg);
		}
	}
	
	/**
	 * Sends a {@link BaseNetworkPacket} to the server.
	 * 
	 * @param msg the {@link BaseNetworkPacket} that should get send to the server
	 */
	public static void sendToServer(BaseNetworkPacket msg){
		CommonProxy.NETWORKWRAPPER.sendToServer(msg);
	}
	
}
