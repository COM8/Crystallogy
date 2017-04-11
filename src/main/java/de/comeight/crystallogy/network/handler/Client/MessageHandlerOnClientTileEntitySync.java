package de.comeight.crystallogy.network.handler.Client;

import de.comeight.crystallogy.handler.ConfigHandler;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.tileEntitys.BaseTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageHandlerOnClientTileEntitySync implements IMessageHandler<NetworkPacketTileEntitySync, IMessage> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnClientTileEntitySync() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public IMessage onMessage(final NetworkPacketTileEntitySync message, MessageContext ctx) {
		// System.out.println("Nachricheneingang Client.");
		if (ctx.side != Side.CLIENT) {
			System.err.println("NetworkPacketTileEntitySync received on wrong side: " + ctx.side);
			return null;
		}
		if (!message.isMessageValid()) {
			System.err.println("NetworkPacketTileEntitySync was invalid" + message.toString());
			return null;
		}
		final Minecraft minecraft = Minecraft.getMinecraft();
		minecraft.addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, minecraft);
			}
		});
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	private void processMessage(NetworkPacketTileEntitySync message, Minecraft minecraft) {
		WorldClient worldClient = minecraft.theWorld;
		BlockPos pos = message.getPos();
		TileEntity tE = worldClient.getTileEntity(pos);
		if(tE != null && tE instanceof BaseTileEntity){
			BaseTileEntity bTE = (BaseTileEntity) tE;
			bTE.onCustomDataPacket(message);
		}
		else if(ConfigHandler.enableDubugMessagesInLog){
			System.out.println("No / Wrong TileEntity in NetworkPacketTileEntitySync!");
		}
	}
	
}
