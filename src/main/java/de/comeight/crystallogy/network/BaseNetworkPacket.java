package de.comeight.crystallogy.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public abstract class BaseNetworkPacket implements IMessage{
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected boolean messageValid;
	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public boolean isMessageValid() {
		return messageValid;
	}

	public void setMessageIsValid(boolean messageIsValid) {
		this.messageValid = messageIsValid;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public abstract String toString();
	
	@Override
	public abstract void toBytes(ByteBuf buf);
	
	@Override
	public abstract void fromBytes(ByteBuf buf);
}
