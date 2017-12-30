package de.comeight.crystallogy.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public abstract class BaseNetworkMessage implements IMessage {
    //-----------------------------------------------Attributes:--------------------------------------------
    protected boolean messageValid;

    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseNetworkMessage(){
        this.messageValid = false;
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------
    public boolean isMessageValid() {
        return messageValid;
    }

    public void setMessageIsValid(boolean messageIsValid) {
        this.messageValid = messageIsValid;
    }

    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public abstract String toString();

    @Override
    public abstract void toBytes(ByteBuf buf);

    @Override
    public abstract void fromBytes(ByteBuf buf);

    //-----------------------------------------------Events:------------------------------------------------

}