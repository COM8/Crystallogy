package de.comeight.crystallogy.util;

import de.comeight.crystallogy.handler.NetworkHandler;
import de.comeight.crystallogy.network.BaseNetworkMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class NetworkUtils {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    /**
     * Sends a {@link BaseNetworkMessage} to all players in the same dimension like the sendingPlayer
     *
     * @param msg the {@link BaseNetworkMessage} that should get send arround
     * @param sendingPlayer the {@link EntityPlayerMP} who sends this {@link BaseNetworkMessage}
     */
    public static void sendToAllPlayers(BaseNetworkMessage msg, EntityPlayerMP sendingPlayer){
        if(sendingPlayer == null){
            return;
        }

        int dimension = sendingPlayer.dimension;
        MinecraftServer minecraftServer = sendingPlayer.mcServer;
        for (EntityPlayerMP player : minecraftServer.getPlayerList().getPlayers()) {
            if (dimension == player.dimension) {
                NetworkHandler.networkWrapper.sendTo(msg, player);
            }
        }
    }

    /**
     * Sends a {@link BaseNetworkMessage} to everybody.
     * Client: A client sends this {@link BaseNetworkMessage} to all other clients and to the server
     * Server: A server sends this {@link BaseNetworkMessage} to every client
     *
     * @param msg the {@link BaseNetworkMessage} that should get send arround
     * @param remote wether the world is remote or not
     */
    public static void sendAllAround(BaseNetworkMessage msg, boolean remote){
        if(!remote){
            NetworkHandler.networkWrapper.sendToAll(msg);
        }
        else{
            NetworkHandler.networkWrapper.sendToServer(msg);
        }
    }

    /**
     * Sends a {@link BaseNetworkMessage} to the server.
     *
     * @param msg the {@link BaseNetworkMessage} that should get send to the server
     */
    public static void sendToServer(BaseNetworkMessage msg){
        NetworkHandler.networkWrapper.sendToServer(msg);
    }

    //-----------------------------------------------Events:------------------------------------------------

}