package net.minecraft.client.audio;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MusicTicker implements ITickable
{
    private final Random rand = new Random();
    private final Minecraft mc;
    private ISound currentMusic;
    private int timeUntilNextMusic = 100;

    public MusicTicker(Minecraft mcIn)
    {
        this.mc = mcIn;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    public void update()
    {
        MusicTicker.MusicType musicticker$musictype = this.mc.getAmbientMusicType();

        if (this.currentMusic != null)
        {
            if (!musicticker$musictype.getMusicLocation().getSoundName().equals(this.currentMusic.getSoundLocation()))
            {
                this.mc.getSoundHandler().stopSound(this.currentMusic);
                this.timeUntilNextMusic = MathHelper.getRandomIntegerInRange(this.rand, 0, musicticker$musictype.getMinDelay() / 2);
            }

            if (!this.mc.getSoundHandler().isSoundPlaying(this.currentMusic))
            {
                this.currentMusic = null;
                this.timeUntilNextMusic = Math.min(MathHelper.getRandomIntegerInRange(this.rand, musicticker$musictype.getMinDelay(), musicticker$musictype.getMaxDelay()), this.timeUntilNextMusic);
            }
        }

        this.timeUntilNextMusic = Math.min(this.timeUntilNextMusic, musicticker$musictype.getMaxDelay());

        if (this.currentMusic == null && this.timeUntilNextMusic-- <= 0)
        {
            this.playMusic(musicticker$musictype);
        }
    }

    /**
     * Plays a music track for the maximum allowable period of time
     *  
     * @param requestedMusicType The type of music being requested
     */
    public void playMusic(MusicTicker.MusicType requestedMusicType)
    {
        this.currentMusic = PositionedSoundRecord.getMusicRecord(requestedMusicType.getMusicLocation());
        this.mc.getSoundHandler().playSound(this.currentMusic);
        this.timeUntilNextMusic = Integer.MAX_VALUE;
    }

    /**
     * Stops playing the current music track
     */
    public void stopMusic()
    {
        if (this.currentMusic != null)
        {
            this.mc.getSoundHandler().stopSound(this.currentMusic);
            this.currentMusic = null;
            this.timeUntilNextMusic = 0;
        }
    }

    @SideOnly(Side.CLIENT)
    public static enum MusicType
    {
        MENU(SoundEvents.music_menu, 20, 600),
        GAME(SoundEvents.music_game, 12000, 24000),
        CREATIVE(SoundEvents.music_creative, 1200, 3600),
        CREDITS(SoundEvents.music_credits, Integer.MAX_VALUE, Integer.MAX_VALUE),
        NETHER(SoundEvents.music_nether, 1200, 3600),
        END_BOSS(SoundEvents.music_dragon, 0, 0),
        END(SoundEvents.music_end, 6000, 24000);

        private final SoundEvent musicLocation;
        private final int minDelay;
        private final int maxDelay;

        private MusicType(SoundEvent musicLocationIn, int minDelayIn, int maxDelayIn)
        {
            this.musicLocation = musicLocationIn;
            this.minDelay = minDelayIn;
            this.maxDelay = maxDelayIn;
        }

        /**
         * Gets the {@link SoundEvent} containing the current music track's location
         */
        public SoundEvent getMusicLocation()
        {
            return this.musicLocation;
        }

        /**
         * Returns the minimum delay between playing music of this type.
         */
        public int getMinDelay()
        {
            return this.minDelay;
        }

        /**
         * Returns the maximum delay between playing music of this type.
         */
        public int getMaxDelay()
        {
            return this.maxDelay;
        }
    }
}