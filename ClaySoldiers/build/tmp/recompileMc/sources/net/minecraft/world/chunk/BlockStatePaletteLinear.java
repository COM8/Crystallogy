package net.minecraft.world.chunk;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStatePaletteLinear implements IBlockStatePalette
{
    private final IBlockState[] field_186042_a;
    private final IBlockStatePaletteResizer field_186043_b;
    private final int field_186044_c;
    private int arraySize;

    public BlockStatePaletteLinear(int p_i47088_1_, IBlockStatePaletteResizer p_i47088_2_)
    {
        this.field_186042_a = new IBlockState[1 << p_i47088_1_];
        this.field_186044_c = p_i47088_1_;
        this.field_186043_b = p_i47088_2_;
    }

    public int func_186041_a(IBlockState state)
    {
        for (int i = 0; i < this.arraySize; ++i)
        {
            if (this.field_186042_a[i] == state)
            {
                return i;
            }
        }

        int j = this.arraySize++;

        if (j < this.field_186042_a.length)
        {
            this.field_186042_a[j] = state;

            if (j == 16)
            {
                System.out.println("");
            }

            return j;
        }
        else
        {
            return this.field_186043_b.func_186008_a(this.field_186044_c + 1, state);
        }
    }

    /**
     * Gets the block state by the palette id.
     */
    public IBlockState getBlockState(int indexKey)
    {
        return indexKey > 0 && indexKey < this.arraySize ? this.field_186042_a[indexKey] : null;
    }

    @SideOnly(Side.CLIENT)
    public void read(PacketBuffer buf)
    {
        this.arraySize = buf.readVarIntFromBuffer();

        for (int i = 0; i < this.arraySize; ++i)
        {
            this.field_186042_a[i] = (IBlockState)Block.BLOCK_STATE_IDS.getByValue(buf.readVarIntFromBuffer());
        }
    }

    public void write(PacketBuffer buf)
    {
        buf.writeVarIntToBuffer(this.arraySize);

        for (int i = 0; i < this.arraySize; ++i)
        {
            buf.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(this.field_186042_a[i]));
        }
    }

    public int func_186040_a()
    {
        int i = PacketBuffer.getVarIntSize(this.arraySize);

        for (int j = 0; j < this.arraySize; ++j)
        {
            i += PacketBuffer.getVarIntSize(Block.BLOCK_STATE_IDS.get(this.field_186042_a[j]));
        }

        return i;
    }
}