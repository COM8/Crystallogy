package de.comeight.crystallogy.network.handler.client;

import de.comeight.crystallogy.network.handler.NetworkMessageParticle;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageHandlerClientParticle implements IMessageHandler<NetworkMessageParticle, IMessage>  {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public IMessage onMessage(NetworkMessageParticle message, MessageContext ctx) {
        return null;
    }

    //-----------------------------------------------Events:------------------------------------------------

}