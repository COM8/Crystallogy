package net.minecraft.entity.boss.dragon.phase;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.math.Vec3d;

public class PhaseHover extends PhaseBase
{
    private Vec3d field_188680_b;

    public PhaseHover(EntityDragon dragonIn)
    {
        super(dragonIn);
    }

    /**
     * Gives the phase a chance to update its status.
     * Called by dragon's onLivingUpdate. Only used when !worldObj.isRemote.
     */
    public void doLocalUpdate()
    {
        if (this.field_188680_b == null)
        {
            this.field_188680_b = new Vec3d(this.dragon.posX, this.dragon.posY, this.dragon.posZ);
        }
    }

    public boolean getIsStationary()
    {
        return true;
    }

    /**
     * Called when this phase is set to active
     */
    public void initPhase()
    {
        this.field_188680_b = null;
    }

    /**
     * Returns the maximum amount dragon may rise or fall during this phase
     */
    public float getMaxRiseOrFall()
    {
        return 1.0F;
    }

    /**
     * Returns the location the dragon is flying toward
     */
    public Vec3d getTargetLocation()
    {
        return this.field_188680_b;
    }

    public PhaseList<PhaseHover> getPhaseList()
    {
        return PhaseList.HOVER;
    }
}