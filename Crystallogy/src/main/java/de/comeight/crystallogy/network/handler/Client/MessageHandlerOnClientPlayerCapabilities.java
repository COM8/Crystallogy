package de.comeight.crystallogy.network.handler.Client;

import de.comeight.crystallogy.network.NetworkPacketPlayerCapabilities;
import de.comeight.crystallogy.util.EnumPlayerCapabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageHandlerOnClientPlayerCapabilities implements IMessageHandler<NetworkPacketPlayerCapabilities, IMessage> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnClientPlayerCapabilities() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public IMessage onMessage(final NetworkPacketPlayerCapabilities message, MessageContext ctx) {
		// System.out.println("Nachricheneingang Client.");
		if (ctx.side != Side.CLIENT) {
			System.err.println("NetworkPacketPlayerCapabilities received on wrong side: " + ctx.side);
			return null;
		}
		if (!message.isMessageValid()) {
			System.err.println("NetworkPacketPlayerCapabilities was invalid" + message.toString());
			return null;
		}
		Minecraft minecraft = Minecraft.getMinecraft();
		final WorldClient worldClient = minecraft.theWorld;
		minecraft.addScheduledTask(new Runnable() {
			public void run() {
				processMessage(message, worldClient);
			}
		});
		return null;
	}
	
	private void processMessage(NetworkPacketPlayerCapabilities message, WorldClient worldClient) {
		EntityPlayer player = worldClient.getPlayerEntityByUUID(message.getUuid());
		if(player != null){
			EnumPlayerCapabilities.getById(message.getEnumId()).setCapabilityForPlayer(player, message.isEnabled());
		}
	}
	
}
