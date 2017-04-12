package de.comeight.crystallogy.network.handler.Server;

import de.comeight.crystallogy.handler.ConfigHandler;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.tileEntitys.BaseTileEntity;
import de.comeight.crystallogy.util.NetworkUtilitis;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageHandlerOnServerTileEntitySync implements IMessageHandler<NetworkPacketTileEntitySync, IMessage>{
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnServerTileEntitySync() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void processMessage(NetworkPacketTileEntitySync message, EntityPlayerMP sendingPlayer) {
		NetworkUtilitis.sendToAllPlayers(message, sendingPlayer);
	}

	@Override
	public IMessage onMessage(final NetworkPacketTileEntitySync message, MessageContext ctx) {
		// System.out.println("Nachricheneingang Server.");
		if (ctx.side != Side.SERVER) {
			System.err.println("NetworkPacketTileEntitySync received on wrong side:" + ctx.side);
			return null;
		}
		if (!message.isMessageValid()) {
			System.err.println("NetworkPacketTileEntitySync was invalid " + message.toString());
			return null;
		}
		final EntityPlayerMP sendingPlayer = ctx.getServerHandler().playerEntity;
		if (sendingPlayer == null) {
			System.err.println("EntityPlayerMP was null when NetworkPacketTileEntitySync was received");
			return null;
		}
		
		final WorldServer worldServer = sendingPlayer.getServerWorld();
		worldServer.addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, worldServer);
				sendToClients(message, sendingPlayer);
			}
		});
		return null;
	}
	
	private void processMessage(NetworkPacketTileEntitySync message, WorldServer worldServer) {
		BlockPos pos = message.getPos();
		TileEntity tE = worldServer.getTileEntity(pos);
		if(tE != null && tE instanceof BaseTileEntity){
			BaseTileEntity bTE = (BaseTileEntity) tE;
			bTE.onCustomDataPacket(message);
		}
		else if(ConfigHandler.enableDebugMessagesInLog){
			System.out.println("No / Wrong TileEntity in NetworkPacketTileEntitySync!");
		}
	}
	
	private void sendToClients(NetworkPacketTileEntitySync message, EntityPlayerMP sendingPlayer){
		NetworkUtilitis.sendToAllPlayers(message, sendingPlayer);
	}
}
