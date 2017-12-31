package de.comeight.crystallogy.network.handler.server;

import de.comeight.crystallogy.network.NetworkMessageParticle;
import de.comeight.crystallogy.util.Logger;
import de.comeight.crystallogy.util.NetworkUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageHandlerServerParticle implements IMessageHandler<NetworkMessageParticle, IMessage> {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public IMessage onMessage(NetworkMessageParticle message, MessageContext ctx) {
        if (ctx.side != Side.SERVER) {
            Logger.error("NetworkMessageParticle received on wrong side:" + ctx.side);
            return null;
        }
        if (!message.isMessageValid()) {
            Logger.error("NetworkMessageParticle was invalid " + message.toString());
            return null;
        }
        final EntityPlayerMP sendingPlayer = ctx.getServerHandler().player;
        if (sendingPlayer == null) {
            Logger.error("EntityPlayerMP was null when NetworkMessageParticle got received!");
            return null;
        }

        final WorldServer playerWorldServer = sendingPlayer.getServerWorld();
        playerWorldServer.addScheduledTask(() -> NetworkUtils.sendToAllPlayers(message, sendingPlayer));
        return null;
    }

    //-----------------------------------------------Events:------------------------------------------------

}