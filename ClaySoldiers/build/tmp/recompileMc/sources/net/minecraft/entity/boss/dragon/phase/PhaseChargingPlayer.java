package net.minecraft.entity.boss.dragon.phase;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhaseChargingPlayer extends PhaseBase
{
    private static final Logger LOGGER = LogManager.getLogger();
    private Vec3d field_188670_c;
    private int field_188671_d = 0;

    public PhaseChargingPlayer(EntityDragon dragonIn)
    {
        super(dragonIn);
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
        if (this.field_188670_c == null)
        {
            LOGGER.warn("Aborting charge player as no target was set.");
            this.dragon.getPhaseManager().setPhase(PhaseList.HOLDING_PATTERN);
        }
        else if (this.field_188671_d > 0 && this.field_188671_d++ >= 10)
        {
            this.dragon.getPhaseManager().setPhase(PhaseList.HOLDING_PATTERN);
        }
        else
        {
            double d0 = this.field_188670_c.squareDistanceTo(this.dragon.posX, this.dragon.posY, this.dragon.posZ);

            if (d0 < 100.0D || d0 > 22500.0D || this.dragon.isCollidedHorizontally || this.dragon.isCollidedVertically)
            {
                ++this.field_188671_d;
            }
        }
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.field_188670_c = null;
        this.field_188671_d = 0;
    }

    public void func_188668_a(Vec3d p_188668_1_)
    {
        this.field_188670_c = p_188668_1_;
    }

    /**
     * Returns the maximum amount dragon may rise or fall during this phase
     */
    public float getMaxRiseOrFall()
    {
        return 3.0F;
    }

    /**
     * Returns the location the dragon is flying toward
     */
    public Vec3d getTargetLocation()
    {
        return this.field_188670_c;
    }

    public PhaseList<PhaseChargingPlayer> getPhaseList()
    {
        return PhaseList.CHARGING_PLAYER;
    }
}