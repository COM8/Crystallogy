package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CooldownTracker
{
    private final Map<Item, CooldownTracker.Cooldown> cooldowns = Maps.<Item, CooldownTracker.Cooldown>newHashMap();
    private int field_185148_b;

    public boolean hasCooldown(Item itemIn)
    {
        return this.getCooldown(itemIn, 0.0F) > 0.0F;
    }

    public float getCooldown(Item itemIn, float p_185143_2_)
    {
        CooldownTracker.Cooldown cooldowntracker$cooldown = (CooldownTracker.Cooldown)this.cooldowns.get(itemIn);

        if (cooldowntracker$cooldown != null)
        {
            float f = (float)(cooldowntracker$cooldown.field_185138_b - cooldowntracker$cooldown.field_185137_a);
            float f1 = (float)cooldowntracker$cooldown.field_185138_b - ((float)this.field_185148_b + p_185143_2_);
            return MathHelper.clamp_float(f1 / f, 0.0F, 1.0F);
        }
        else
        {
            return 0.0F;
        }
    }

    public void tick()
    {
        ++this.field_185148_b;

        if (!this.cooldowns.isEmpty())
        {
            Iterator<Entry<Item, CooldownTracker.Cooldown>> iterator = this.cooldowns.entrySet().iterator();

            while (iterator.hasNext())
            {
                Entry<Item, CooldownTracker.Cooldown> entry = (Entry)iterator.next();

                if (((CooldownTracker.Cooldown)entry.getValue()).field_185138_b <= this.field_185148_b)
                {
                    iterator.remove();
                    this.notifyOnRemove((Item)entry.getKey());
                }
            }
        }
    }

    public void setCooldown(Item itemIn, int ticksIn)
    {
        this.cooldowns.put(itemIn, new CooldownTracker.Cooldown(this.field_185148_b, this.field_185148_b + ticksIn));
        this.notifyOnSet(itemIn, ticksIn);
    }

    @SideOnly(Side.CLIENT)
    public void removeCooldown(Item itemIn)
    {
        this.cooldowns.remove(itemIn);
        this.notifyOnRemove(itemIn);
    }

    protected void notifyOnSet(Item itemIn, int ticksIn)
    {
    }

    protected void notifyOnRemove(Item itemIn)
    {
    }

    class Cooldown
    {
        final int field_185137_a;
        final int field_185138_b;

        private Cooldown(int p_i47037_2_, int p_i47037_3_)
        {
            this.field_185137_a = p_i47037_2_;
            this.field_185138_b = p_i47037_3_;
        }
    }
}