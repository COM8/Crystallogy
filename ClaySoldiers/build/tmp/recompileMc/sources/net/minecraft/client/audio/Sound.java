package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Sound implements ISoundEventAccessor<Sound>
{
    private final ResourceLocation field_188726_a;
    private final float volume;
    private final float pitch;
    private final int weight;
    private final Sound.Type type;
    private final boolean streaming;

    public Sound(String p_i46526_1_, float volumeIn, float pitchIn, int weightIn, Sound.Type typeIn, boolean p_i46526_6_)
    {
        this.field_188726_a = new ResourceLocation(p_i46526_1_);
        this.volume = volumeIn;
        this.pitch = pitchIn;
        this.weight = weightIn;
        this.type = typeIn;
        this.streaming = p_i46526_6_;
    }

    public ResourceLocation getSoundLocation()
    {
        return this.field_188726_a;
    }

    public ResourceLocation getSoundAsOggLocation()
    {
        return new ResourceLocation(this.field_188726_a.getResourceDomain(), "sounds/" + this.field_188726_a.getResourcePath() + ".ogg");
    }

    public float getVolume()
    {
        return this.volume;
    }

    public float getPitch()
    {
        return this.pitch;
    }

    public int getWeight()
    {
        return this.weight;
    }

    public Sound cloneEntry()
    {
        return this;
    }

    public Sound.Type getType()
    {
        return this.type;
    }

    public boolean isStreaming()
    {
        return this.streaming;
    }

    @SideOnly(Side.CLIENT)
    public static enum Type
    {
        FILE("file"),
        SOUND_EVENT("event");

        private final String field_188708_c;

        private Type(String p_i46631_3_)
        {
            this.field_188708_c = p_i46631_3_;
        }

        public static Sound.Type func_188704_a(String p_188704_0_)
        {
            for (Sound.Type sound$type : values())
            {
                if (sound$type.field_188708_c.equals(p_188704_0_))
                {
                    return sound$type;
                }
            }

            return null;
        }
    }
}