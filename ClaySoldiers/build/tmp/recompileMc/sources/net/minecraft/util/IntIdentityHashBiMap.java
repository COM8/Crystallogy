package net.minecraft.util;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class IntIdentityHashBiMap<K> implements IObjectIntIterable<K>, Iterable<K>
{
    private static final Object field_186817_a = null;
    private K[] objectArray;
    private int[] intKeys;
    private K[] intToObjects;
    private int field_186821_e;
    private int mapSize;

    public IntIdentityHashBiMap(int initialCapacity)
    {
        initialCapacity = (int)((float)initialCapacity / 0.8F);
        this.objectArray = (K[])(new Object[initialCapacity]);
        this.intKeys = new int[initialCapacity];
        this.intToObjects = (K[])(new Object[initialCapacity]);
    }

    public int getId(K p_186815_1_)
    {
        return this.func_186805_c(this.func_186816_b(p_186815_1_, this.hashObject(p_186815_1_)));
    }

    public K get(int idIn)
    {
        return (K)(idIn >= 0 && idIn < this.intToObjects.length ? this.intToObjects[idIn] : null);
    }

    private int func_186805_c(int p_186805_1_)
    {
        return p_186805_1_ == -1 ? -1 : this.intKeys[p_186805_1_];
    }

    /**
     * Adds the given object while expanding this map
     */
    public int add(K objectIn)
    {
        int i = this.func_186809_c();
        this.put(objectIn, i);
        return i;
    }

    private int func_186809_c()
    {
        while (this.field_186821_e < this.intToObjects.length && this.intToObjects[this.field_186821_e] != null)
        {
            ++this.field_186821_e;
        }

        return this.field_186821_e++;
    }

    /**
     * Rehashes the map to the new capacity
     */
    private void rehash(int capacity)
    {
        K[] ak = this.objectArray;
        int[] aint = this.intKeys;
        this.objectArray = (K[])(new Object[capacity]);
        this.intKeys = new int[capacity];
        this.intToObjects = (K[])(new Object[capacity]);
        this.field_186821_e = 0;
        this.mapSize = 0;

        for (int i = 0; i < ak.length; ++i)
        {
            if (ak[i] != null)
            {
                this.put(ak[i], aint[i]);
            }
        }
    }

    /**
     * Puts the provided object value with the integer key.
     */
    public void put(K objectIn, int intKey)
    {
        int i = Math.max(intKey, ++this.mapSize);

        if ((float)i >= (float)this.objectArray.length * 0.8F)
        {
            int j;

            for (j = this.objectArray.length << 1; j < intKey; j <<= 1)
            {
                ;
            }

            this.rehash(j);
        }

        int k = this.func_186806_e(this.hashObject(objectIn));
        this.objectArray[k] = objectIn;
        this.intKeys[k] = intKey;
        this.intToObjects[intKey] = objectIn;
    }

    private int hashObject(K obectIn)
    {
        return (MathHelper.getHash(System.identityHashCode(obectIn)) & Integer.MAX_VALUE) % this.objectArray.length;
    }

    private int func_186816_b(K objectIn, int p_186816_2_)
    {
        for (int i = p_186816_2_; i < this.objectArray.length; ++i)
        {
            if (this.objectArray[i] == objectIn)
            {
                return i;
            }

            if (this.objectArray[i] == field_186817_a)
            {
                return -1;
            }
        }

        for (int j = 0; j < p_186816_2_; ++j)
        {
            if (this.objectArray[j] == objectIn)
            {
                return j;
            }

            if (this.objectArray[j] == field_186817_a)
            {
                return -1;
            }
        }

        return -1;
    }

    private int func_186806_e(int p_186806_1_)
    {
        StringBuilder stringbuilder = new StringBuilder("");

        for (int i = p_186806_1_; i < this.objectArray.length; ++i)
        {
            if (this.objectArray[i] == field_186817_a)
            {
                return i;
            }

            stringbuilder.append(i).append(' ');
        }

        for (int j = 0; j < p_186806_1_; ++j)
        {
            if (this.objectArray[j] == field_186817_a)
            {
                return j;
            }

            stringbuilder.append(j).append(' ');
        }

        throw new RuntimeException("Overflowed :(");
    }

    public Iterator<K> iterator()
    {
        return Iterators.filter(Iterators.forArray(this.intToObjects), Predicates.notNull());
    }

    public void func_186812_a()
    {
        Arrays.fill(this.objectArray, (Object)null);
        Arrays.fill(this.intToObjects, (Object)null);
        this.field_186821_e = 0;
        this.mapSize = 0;
    }

    public int size()
    {
        return this.mapSize + 1;
    }
}