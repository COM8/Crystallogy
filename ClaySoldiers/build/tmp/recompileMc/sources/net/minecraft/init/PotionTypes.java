package net.minecraft.init;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;

public class PotionTypes
{
    private static final Set<PotionType> CACHE;
    public static final PotionType empty;
    public static final PotionType water;
    public static final PotionType mundane;
    public static final PotionType thick;
    public static final PotionType awkward;
    public static final PotionType night_vision;
    public static final PotionType long_night_vision;
    public static final PotionType invisibility;
    public static final PotionType long_invisibility;
    public static final PotionType leaping;
    public static final PotionType long_leaping;
    public static final PotionType strong_leaping;
    public static final PotionType fire_resistance;
    public static final PotionType long_fire_resistance;
    public static final PotionType swiftness;
    public static final PotionType long_swiftness;
    public static final PotionType strong_swiftness;
    public static final PotionType slowness;
    public static final PotionType long_slowness;
    public static final PotionType water_breathing;
    public static final PotionType long_water_breathing;
    public static final PotionType healing;
    public static final PotionType strong_healing;
    public static final PotionType harming;
    public static final PotionType strong_harming;
    public static final PotionType poison;
    public static final PotionType long_poison;
    public static final PotionType strong_poison;
    public static final PotionType regeneration;
    public static final PotionType long_regeneration;
    public static final PotionType strong_regeneration;
    public static final PotionType strength;
    public static final PotionType long_strength;
    public static final PotionType strong_strength;
    public static final PotionType weakness;
    public static final PotionType long_weakness;

    private static PotionType getRegisteredPotionType(String id)
    {
        PotionType potiontype = (PotionType)PotionType.potionTypeRegistry.getObject(new ResourceLocation(id));

        if (!CACHE.add(potiontype))
        {
            throw new IllegalStateException("Invalid Potion requested: " + id);
        }
        else
        {
            return potiontype;
        }
    }

    static
    {
        if (!Bootstrap.isRegistered())
        {
            throw new RuntimeException("Accessed Potions before Bootstrap!");
        }
        else
        {
            CACHE = Sets.<PotionType>newHashSet();
            empty = getRegisteredPotionType("empty");
            water = getRegisteredPotionType("water");
            mundane = getRegisteredPotionType("mundane");
            thick = getRegisteredPotionType("thick");
            awkward = getRegisteredPotionType("awkward");
            night_vision = getRegisteredPotionType("night_vision");
            long_night_vision = getRegisteredPotionType("long_night_vision");
            invisibility = getRegisteredPotionType("invisibility");
            long_invisibility = getRegisteredPotionType("long_invisibility");
            leaping = getRegisteredPotionType("leaping");
            long_leaping = getRegisteredPotionType("long_leaping");
            strong_leaping = getRegisteredPotionType("strong_leaping");
            fire_resistance = getRegisteredPotionType("fire_resistance");
            long_fire_resistance = getRegisteredPotionType("long_fire_resistance");
            swiftness = getRegisteredPotionType("swiftness");
            long_swiftness = getRegisteredPotionType("long_swiftness");
            strong_swiftness = getRegisteredPotionType("strong_swiftness");
            slowness = getRegisteredPotionType("slowness");
            long_slowness = getRegisteredPotionType("long_slowness");
            water_breathing = getRegisteredPotionType("water_breathing");
            long_water_breathing = getRegisteredPotionType("long_water_breathing");
            healing = getRegisteredPotionType("healing");
            strong_healing = getRegisteredPotionType("strong_healing");
            harming = getRegisteredPotionType("harming");
            strong_harming = getRegisteredPotionType("strong_harming");
            poison = getRegisteredPotionType("poison");
            long_poison = getRegisteredPotionType("long_poison");
            strong_poison = getRegisteredPotionType("strong_poison");
            regeneration = getRegisteredPotionType("regeneration");
            long_regeneration = getRegisteredPotionType("long_regeneration");
            strong_regeneration = getRegisteredPotionType("strong_regeneration");
            strength = getRegisteredPotionType("strength");
            long_strength = getRegisteredPotionType("long_strength");
            strong_strength = getRegisteredPotionType("strong_strength");
            weakness = getRegisteredPotionType("weakness");
            long_weakness = getRegisteredPotionType("long_weakness");
            CACHE.clear();
        }
    }
}