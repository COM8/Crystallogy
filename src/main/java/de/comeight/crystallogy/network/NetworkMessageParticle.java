package de.comeight.crystallogy.network;

import de.comeight.crystallogy.util.Logger;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class NetworkMessageParticle extends BaseNetworkMessage {
    //-----------------------------------------------Attributes:--------------------------------------------
    protected NBTTagCompound particleNbtTagCompound;

    //-----------------------------------------------Constructor:-------------------------------------------
    public NetworkMessageParticle(ParticleContainer pC){
        this.particleNbtTagCompound = pC.toNBT();
        this.messageValid = true;
    }

    public NetworkMessageParticle(){
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------
    public NBTTagCompound getParticleNbtTagCompound() {
        return particleNbtTagCompound;
    }

    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public String toString() {
        String s = "null";
        if(particleNbtTagCompound != null){
            s = particleNbtTagCompound.toString();
        }
        return "NetworkMessageParticle[" + s + "]";
    }

    @Override
    public void toBytes(ByteBuf buf) {
        if(messageValid && particleNbtTagCompound != null){
            ByteBufUtils.writeTag(buf, particleNbtTagCompound);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            particleNbtTagCompound = ByteBufUtils.readTag(buf);
            messageValid = true;
        }
        catch (Exception e){
            Logger.error("Unable to load NetworkMessageParticle! => " + e.getMessage());
        }
    }

    //-----------------------------------------------Events:------------------------------------------------

}