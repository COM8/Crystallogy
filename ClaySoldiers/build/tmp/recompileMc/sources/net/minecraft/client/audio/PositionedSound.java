package net.minecraft.client.audio;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class PositionedSound implements ISound
{
    protected Sound field_184367_a;
    private SoundEventAccessor field_184369_l;
    protected SoundCategory category;
    protected ResourceLocation positionedSoundLocation;
    protected float volume;
    protected float pitch;
    protected float xPosF;
    protected float yPosF;
    protected float zPosF;
    protected boolean repeat;
    /** The number of ticks between repeating the sound */
    protected int repeatDelay;
    protected ISound.AttenuationType attenuationType;

    protected PositionedSound(SoundEvent soundIn, SoundCategory categoryIn)
    {
        this(soundIn.getSoundName(), categoryIn);
    }

    protected PositionedSound(ResourceLocation soundId, SoundCategory categoryIn)
    {
        this.volume = 1.0F;
        this.pitch = 1.0F;
        this.attenuationType = ISound.AttenuationType.LINEAR;
        this.positionedSoundLocation = soundId;
        this.category = categoryIn;
    }

    public ResourceLocation getSoundLocation()
    {
        return this.positionedSoundLocation;
    }

    public SoundEventAccessor func_184366_a(SoundHandler p_184366_1_)
    {
        this.field_184369_l = p_184366_1_.func_184398_a(this.positionedSoundLocation);

        if (this.field_184369_l == null)
        {
            this.field_184367_a = SoundHandler.missing_sound;
        }
        else
        {
            this.field_184367_a = this.field_184369_l.cloneEntry();
        }

        return this.field_184369_l;
    }

    public Sound getSound()
    {
        return this.field_184367_a;
    }

    public SoundCategory getCategory()
    {
        return this.category;
    }

    public boolean canRepeat()
    {
        return this.repeat;
    }

    public int getRepeatDelay()
    {
        return this.repeatDelay;
    }

    public float getVolume()
    {
        return this.volume * this.field_184367_a.getVolume();
    }

    public float getPitch()
    {
        return this.pitch * this.field_184367_a.getPitch();
    }

    public float getXPosF()
    {
        return this.xPosF;
    }

    public float getYPosF()
    {
        return this.yPosF;
    }

    public float getZPosF()
    {
        return this.zPosF;
    }

    public ISound.AttenuationType getAttenuationType()
    {
        return this.attenuationType;
    }
}