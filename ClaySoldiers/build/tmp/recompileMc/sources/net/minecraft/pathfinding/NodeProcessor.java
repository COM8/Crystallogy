package net.minecraft.pathfinding;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;

public abstract class NodeProcessor
{
    protected IBlockAccess blockaccess;
    protected EntityLiving field_186326_b;
    protected final IntHashMap<PathPoint> pointMap = new IntHashMap();
    protected int entitySizeX;
    protected int entitySizeY;
    protected int entitySizeZ;
    protected boolean canEnterDoors;
    protected boolean canBreakDoors;
    protected boolean canSwim;

    public void func_186315_a(IBlockAccess sourceIn, EntityLiving mob)
    {
        this.blockaccess = sourceIn;
        this.field_186326_b = mob;
        this.pointMap.clearMap();
        this.entitySizeX = MathHelper.floor_float(mob.width + 1.0F);
        this.entitySizeY = MathHelper.floor_float(mob.height + 1.0F);
        this.entitySizeZ = MathHelper.floor_float(mob.width + 1.0F);
    }

    /**
     * This method is called when all nodes have been processed and PathEntity is created.
     * {@link net.minecraft.world.pathfinder.WalkNodeProcessor WalkNodeProcessor} uses this to change its field {@link
     * net.minecraft.world.pathfinder.WalkNodeProcessor#avoidsWater avoidsWater}
     */
    public void postProcess()
    {
    }

    /**
     * Returns a mapped point or creates and adds one
     */
    protected PathPoint openPoint(int x, int y, int z)
    {
        int i = PathPoint.makeHash(x, y, z);
        PathPoint pathpoint = (PathPoint)this.pointMap.lookup(i);

        if (pathpoint == null)
        {
            pathpoint = new PathPoint(x, y, z);
            this.pointMap.addKey(i, pathpoint);
        }

        return pathpoint;
    }

    public abstract PathPoint func_186318_b();

    public abstract PathPoint func_186325_a(double p_186325_1_, double p_186325_3_, double p_186325_5_);

    public abstract int func_186320_a(PathPoint[] p_186320_1_, PathPoint p_186320_2_, PathPoint p_186320_3_, float p_186320_4_);

    public abstract PathNodeType func_186319_a(IBlockAccess p_186319_1_, int p_186319_2_, int p_186319_3_, int p_186319_4_, EntityLiving p_186319_5_, int p_186319_6_, int p_186319_7_, int p_186319_8_, boolean p_186319_9_, boolean p_186319_10_);

    public void setCanEnterDoors(boolean canEnterDoorsIn)
    {
        this.canEnterDoors = canEnterDoorsIn;
    }

    public void setCanBreakDoors(boolean canBreakDoorsIn)
    {
        this.canBreakDoors = canBreakDoorsIn;
    }

    public void setCanSwim(boolean canSwimIn)
    {
        this.canSwim = canSwimIn;
    }

    public boolean getCanEnterDoors()
    {
        return this.canEnterDoors;
    }

    public boolean getCanBreakDoors()
    {
        return this.canBreakDoors;
    }

    public boolean getCanSwim()
    {
        return this.canSwim;
    }
}