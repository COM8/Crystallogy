package de.comeight.crystallogy.network.handler.Client;

import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.BaseParticle;
import de.comeight.crystallogy.particles.ParticleHandler;
import de.comeight.crystallogy.util.Log;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageHandlerOnClientParticle implements IMessageHandler<NetworkPacketParticle, IMessage> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageHandlerOnClientParticle() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public IMessage onMessage(final NetworkPacketParticle message, MessageContext ctx) {
		// System.out.println("Nachricheneingang Client.");
		if (ctx.side != Side.CLIENT) {
			System.err.println("NetworkPacketParticle received on wrong side: " + ctx.side);
			return null;
		}
		if (!message.isMessageValid()) {
			System.err.println("NetworkPacketParticle was invalid" + message.toString());
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
	private void processMessage(NetworkPacketParticle message, Minecraft minecraft) {
		WorldClient worldClient = minecraft.theWorld;
		NetworkParticle nP = message.getNetworkParticle();
		Vec3d pos = nP.getTransporterParticle().pos;
		Vec3d size = nP.getSize();
		BaseParticle p = ParticleHandler.findParticle(nP.getType());
		if(p == null){
			Log.warn("Unknown Particle type detected: " + nP.getType());
			return;
		}
		
		for(int i = 0; i < nP.getNumberOfParticle(); i++){
			BaseParticle bP = p.clone(worldClient, new Vec3d(pos.xCoord + Utilities.getRandDouble(-size.xCoord, size.xCoord), pos.yCoord + Utilities.getRandDouble(0.0, size.yCoord), pos.zCoord + Utilities.getRandDouble(-size.zCoord, size.zCoord)), nP.getTransporterParticle());
			
			if(nP.getTransporterParticle().randomColor){
				bP.setRBGColorF(Utilities.getRandFloat(0.0F, 1.0F), Utilities.getRandFloat(0.0F, 1.0F), Utilities.getRandFloat(0.0F, 1.0F));
			}
			else{
				bP.setRBGColorF(nP.getTransporterParticle().color.r, nP.getTransporterParticle().color.g, nP.getTransporterParticle().color.b);
			}
			
			int minMaxAge = (int) ((double)nP.getTransporterParticle().maxAge * 0.8);
			int maxMaxAge = (int) ((double)nP.getTransporterParticle().maxAge * 1.2);
			bP.setMaxAge(Utilities.getRandInt(minMaxAge, maxMaxAge));
			bP.multipleParticleScaleBy(nP.getTransporterParticle().scale);
			
			Minecraft.getMinecraft().effectRenderer.addEffect(bP);
		}
	}
	
}
