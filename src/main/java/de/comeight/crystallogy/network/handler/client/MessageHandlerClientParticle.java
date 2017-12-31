package de.comeight.crystallogy.network.handler.client;

import de.comeight.crystallogy.client.particles.BaseParticle;
import de.comeight.crystallogy.client.particles.CrystalParticle;
import de.comeight.crystallogy.client.particles.SquareParticle;
import de.comeight.crystallogy.network.NetworkMessageParticle;
import de.comeight.crystallogy.network.ParticleContainer;
import de.comeight.crystallogy.util.Logger;
import de.comeight.crystallogy.util.RGBColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageHandlerClientParticle implements IMessageHandler<NetworkMessageParticle, IMessage>  {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public IMessage onMessage(NetworkMessageParticle message, MessageContext ctx) {
        if (ctx.side != Side.CLIENT) {
            Logger.error("NetworkMessageParticle received on wrong side:" + ctx.side);
            return null;
        }
        if (!message.isMessageValid()) {
            Logger.error("NetworkMessageParticle was invalid " + message.toString());
            return null;
        }

        final ParticleContainer pC = new ParticleContainer(message.getParticleNbtTagCompound());
        Minecraft.getMinecraft().addScheduledTask(() -> processMessage(pC));

        return null;
    }

    @SideOnly(Side.CLIENT)
    private void processMessage(ParticleContainer pC){
        WorldClient world = Minecraft.getMinecraft().world;
        BaseParticle p = loadParticle(pC, world);
        if(p == null){
            Logger.error("Invalid NetworkMessageParticle! Unable to load particle: " + pC.particle);
            return;
        }

        BaseParticle pNew;
        for (int i = 0; i < pC.particleCount; i++) {
             pNew = p.clone();
            if(pC.randomMaxAge && pC.maxAgeMax > pC.maxAgeMin){
                pNew.setMaxAge(pC.maxAgeMin + world.rand.nextInt(pC.maxAgeMax - pC.maxAgeMin));
            }
            else {
                pNew.setMaxAge(pC.maxAgeMin);
            }
            if(pC.randomScale && pC.scaleMax > pC.scaleMin){
                pNew.multipleParticleScaleBy(pC.scaleMin + world.rand.nextFloat() * (pC.scaleMax - pC.scaleMin));
            }
            else {
                pNew.multipleParticleScaleBy(pC.scaleMin);
            }
            if(pC.randomColor || pC.color == null){
                pNew.setRGBColor(RGBColor.random(world.rand));
            }
            else {
                pNew.setRGBColor(pC.color);
            }

            if(pC.area.x != 0 || pC.area.y != 0 || pC.area.z != 0){
                double x = pC.pos.x + world.rand.nextFloat() * pC.area.x;
                double y = pC.pos.y + world.rand.nextFloat() * pC.area.y;
                double z = pC.pos.z + world.rand.nextFloat() * pC.area.z;
                pNew.setPosition(x, y, z);
            }

            Minecraft.getMinecraft().effectRenderer.addEffect(pNew);
        }
    }

    @SideOnly(Side.CLIENT)
    private BaseParticle loadParticle(ParticleContainer pC, World world){
        switch (pC.particle){
            case SQUARE_PARTICLE:
                return new SquareParticle(world, pC.pos);
            case CRYSTAL_PARTICLE:
                return new CrystalParticle(world, pC.pos);
            default:
                return null;
        }
    }

    //-----------------------------------------------Events:------------------------------------------------

}