package de.comeight.crystallogy.network;

import de.comeight.crystallogy.util.Const;
import de.comeight.crystallogy.util.NBTUtils;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.enums.EnumParticle;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public class ParticleContainer {
    //-----------------------------------------------Attributes:--------------------------------------------
    public EnumParticle particle;

    public Vec3d pos;
    public Vec3d startPos;
    public Vec3d targetPos;
    public Vec3d area;

    public  boolean randomMaxAge;
    public int maxAgeMin;
    public int maxAgeMax;

    public boolean randomScale;
    public float scaleMin;
    public float scaleMax;

    public boolean randomColor;
    public RGBColor color;

    public int particleCount;

    //-----------------------------------------------Constructor:-------------------------------------------
    public ParticleContainer() {
        this.particle = EnumParticle.BASE_PARTICLE;
        this.pos = new Vec3d(0, 0, 0);
        this.randomMaxAge = false;
        this.maxAgeMin = 0;
        this.randomScale = false;
        this.scaleMin = 1.0F;
        this.randomColor = false;
        this.color = RGBColor.WHITE;
        this.startPos = new Vec3d(0, 0, 0);
        this.targetPos = new Vec3d(0, 0, 0);
        this.area = new Vec3d(0, 0, 0);
        this.particleCount = 1;
    }

    public ParticleContainer(NBTTagCompound nbt) {
        fromNBT(nbt);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    public NBTTagCompound toNBT(){
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte(Const.PARTICLE, particle.getMeta());

        if(pos != null){
            NBTUtils.setVec3d(pos, nbt, Const.POSITION);
        }
        if(startPos != null){
            NBTUtils.setVec3d(startPos, nbt, Const.START_POSITION);
        }
        if(targetPos != null){
            NBTUtils.setVec3d(targetPos, nbt, Const.TARGET_POSITION);
        }
        if(area != null){
            NBTUtils.setVec3d(area, nbt, Const.AREA);
        }

        nbt.setBoolean(Const.RANDOM_MAX_AGE, randomMaxAge);
        nbt.setInteger(Const.MAX_AGE_MIN, maxAgeMin);
        if(randomMaxAge){
            nbt.setInteger(Const.MAX_AGE_MAX, maxAgeMax);
        }

        nbt.setBoolean(Const.RANDOM_SCALE, randomScale);
        nbt.setFloat(Const.SCALE_MIN, scaleMin);
        if(randomScale){
            nbt.setFloat(Const.SCALE_MAX, scaleMax);
        }

        nbt.setBoolean(Const.RANDOM_COLOR, randomColor);
        if(!randomColor){
            NBTUtils.setRGBColor(color, nbt, Const.COLOR);
        }

        nbt.setInteger(Const.PARTICLE_COUNT, particleCount);

        return nbt;
    }

    private void fromNBT(NBTTagCompound nbt){
        particle = EnumParticle.fromMeta(nbt.getByte(Const.PARTICLE));

        pos = NBTUtils.getVec3d(nbt, Const.POSITION);
        startPos = NBTUtils.getVec3d(nbt, Const.START_POSITION);
        targetPos = NBTUtils.getVec3d(nbt, Const.TARGET_POSITION);
        area = NBTUtils.getVec3d(nbt, Const.AREA);

        randomMaxAge = nbt.getBoolean(Const.RANDOM_MAX_AGE);
        maxAgeMin = nbt.getInteger(Const.MAX_AGE_MIN);
        maxAgeMax = nbt.getInteger(Const.MAX_AGE_MAX);

        randomScale = nbt.getBoolean(Const.RANDOM_SCALE);
        scaleMin = nbt.getFloat(Const.SCALE_MIN);
        scaleMax = nbt.getFloat(Const.SCALE_MAX);

        randomColor = nbt.getBoolean(Const.RANDOM_COLOR);
        color = NBTUtils.getRGBColor(nbt, Const.COLOR);

        particleCount = nbt.getInteger(Const.PARTICLE_COUNT);
    }

    //-----------------------------------------------Events:------------------------------------------------

}