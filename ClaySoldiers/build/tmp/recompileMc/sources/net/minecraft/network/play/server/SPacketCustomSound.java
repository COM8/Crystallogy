package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.Validate;

public class SPacketCustomSound implements Packet<INetHandlerPlayClient>
{
    private String soundName;
    private SoundCategory category;
    private int x;
    private int y = Integer.MAX_VALUE;
    private int z;
    private float volume;
    private int pitch;

    public SPacketCustomSound()
    {
    }

    public SPacketCustomSound(String soundNameIn, SoundCategory categoryIn, double p_i46948_3_, double p_i46948_5_, double p_i46948_7_, float p_i46948_9_, float p_i46948_10_)
    {
        Validate.notNull(soundNameIn, "name", new Object[0]);
        this.soundName = soundNameIn;
        this.category = categoryIn;
        this.x = (int)(p_i46948_3_ * 8.0D);
        this.y = (int)(p_i46948_5_ * 8.0D);
        this.z = (int)(p_i46948_7_ * 8.0D);
        this.volume = p_i46948_9_;
        this.pitch = (int)(p_i46948_10_ * 63.0F);
        p_i46948_10_ = MathHelper.clamp_float(p_i46948_10_, 0.0F, 255.0F);
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.soundName = buf.readStringFromBuffer(256);
        this.category = (SoundCategory)buf.readEnumValue(SoundCategory.class);
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.volume = buf.readFloat();
        this.pitch = buf.readUnsignedByte();
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeString(this.soundName);
        buf.writeEnumValue(this.category);
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeFloat(this.volume);
        buf.writeByte(this.pitch);
    }

    @SideOnly(Side.CLIENT)
    public String getSoundName()
    {
        return this.soundName;
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerPlayClient handler)
    {
        handler.handleCustomSound(this);
    }

    @SideOnly(Side.CLIENT)
    public SoundCategory getCategory()
    {
        return this.category;
    }

    @SideOnly(Side.CLIENT)
    public double getX()
    {
        return (double)((float)this.x / 8.0F);
    }

    @SideOnly(Side.CLIENT)
    public double getY()
    {
        return (double)((float)this.y / 8.0F);
    }

    @SideOnly(Side.CLIENT)
    public double getZ()
    {
        return (double)((float)this.z / 8.0F);
    }

    @SideOnly(Side.CLIENT)
    public float getVolume()
    {
        return this.volume;
    }

    @SideOnly(Side.CLIENT)
    public float getPitch()
    {
        return (float)this.pitch / 63.0F;
    }
}