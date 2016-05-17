package de.comeight.crystallogy.network.handler.Server;

import de.comeight.crystallogy.network.NetworkPacketPlayerCapabilities;
import de.comeight.crystallogy.util.NetworkUtilitis;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageHandlerOnServerPlayerCapabilities implements IMessageHandler<NetworkPacketPlayerCapabilities, IMessage>{
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnServerPlayerCapabilities() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void processMessage(NetworkPacketPlayerCapabilities message, EntityPlayerMP sendingPlayer) {
		//DO something
	}

	@Override
	public IMessage onMessage(final NetworkPacketPlayerCapabilities message, MessageContext ctx) {
		// System.out.println("Nachricheneingang Server.");
		if (ctx.side != Side.SERVER) {
			System.err.println("NetworkPacketPlayerCapabilities received on wrong side:" + ctx.side);
			return null;
		}
		if (!message.isMessageValid()) {
			System.err.println("NetworkPacketPlayerCapabilities was invalid " + message.toString());
			return null;
		}
		final EntityPlayerMP sendingPlayer = ctx.getServerHandler().playerEntity;
		if (sendingPlayer == null) {
			System.err.println("EntityPlayerMP was null when NetworkPacketPlayerCapabilities was received");
			return null;
		}

		final WorldServer playerWorldServer = sendingPlayer.getServerForPlayer();
		playerWorldServer.addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, sendingPlayer);
			}
		});
		return null;
	}
}
