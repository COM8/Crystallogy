package de.comeight.crystallogy.particles;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.util.EnumParticleTypes;

public enum EnumCrystallogyParticleTypes {

	//-----------------------------------------------Variabeln:---------------------------------------------
	PARTICLE_A("particleA", 0),
	PARTICLE_B("particleB", 1),
	PARTICLE_C("particleC", 2),
	PARTICLE_D("particleD", 3),
	PARTICLE_E("particleE", 4),
	PARTICLE_F("particleF", 5),
	
	PARTICLE_N_COLOR("particleN_color", 6),
	
	JUMP_PARTICLE_BETWEEN_CRYSTALLS("jumpParticleBetweenCrystalls", 7),
	INFUSION_PARTICLE("infusionParticle", 8),
	LIGHT_PARTICLE("light_particle", 9);
	
	private final String particleName;
    private final int particleID;
    private static final Map<Integer, EnumCrystallogyParticleTypes> PARTICLES = Maps.<Integer, EnumCrystallogyParticleTypes>newHashMap();
    private static final String[] PARTICLE_NAMES;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	private EnumCrystallogyParticleTypes(String particleNameIn, int particleIDIn)
    {
        this.particleName = particleNameIn;
        this.particleID = particleIDIn;
    }
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public static String[] getParticleNames()
    {
        return PARTICLE_NAMES;
    }

    public String getParticleName()
    {
        return this.particleName;
    }

    public int getParticleID()
    {
        return this.particleID;
    }
	
    public static EnumCrystallogyParticleTypes getParticleFromId(int particleId)
    {
        return (EnumCrystallogyParticleTypes)PARTICLES.get(Integer.valueOf(particleId));
    }
    
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
    static
    {
        List<String> list = Lists.<String>newArrayList();

        for (EnumCrystallogyParticleTypes enumparticletypes : values())
        {
            PARTICLES.put(Integer.valueOf(enumparticletypes.getParticleID()), enumparticletypes);

            if (!enumparticletypes.getParticleName().endsWith("_"))
            {
                list.add(enumparticletypes.getParticleName());
            }
        }

        PARTICLE_NAMES = (String[])list.toArray(new String[list.size()]);
    }
    
}
