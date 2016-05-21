package de.comeight.crystallogy.network.handler.Server;

import de.comeight.crystallogy.network.NetworkPacketUpdateInventory;
import de.comeight.crystallogy.util.NetworkUtilitis;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageHandlerOnServerUpdateInventory implements IMessageHandler<NetworkPacketUpdateInventory, IMessage> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnServerUpdateInventory() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void processMessage(NetworkPacketUpdateInventory message, EntityPlayerMP sendingPlayer) {
		NetworkUtilitis.sendToAllPlayers(message, sendingPlayer);
	}
	
	@Override
	public IMessage onMessage(final NetworkPacketUpdateInventory message, MessageContext ctx) {
		// System.out.println("Nachricheneingang Server.");
		if (ctx.side != Side.SERVER) {
			System.err.println("NetworkPacketUpdateInventory received on wrong side:" + ctx.side);
			return null;
		}
		if (!message.isMessageValid()) {
			System.err.println("NetworkPacketUpdateInventory was invalid " + message.toString());
			return null;
		}
		final EntityPlayerMP sendingPlayer = ctx.getServerHandler().playerEntity;
		if (sendingPlayer == null) {
			System.err.println("EntityPlayerMP was null when NetworkPacketUpdateInventory was received");
			return null;
		}

		final WorldServer playerWorldServer = sendingPlayer.getServerWorld();
		playerWorldServer.addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, sendingPlayer);
			}
		});
		return null;
	}
	
}
