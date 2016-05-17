package net.minecraft.world.chunk;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.BitArray;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStateContainer implements IBlockStatePaletteResizer
{
    private static final IBlockStatePalette REGISTRY_BASED_PALETTE = new BlockStatePaletteRegistry();
    protected static final IBlockState AIR_BLOCK_STATE = Blocks.air.getDefaultState();
    protected BitArray bits;
    protected IBlockStatePalette field_186022_c;
    private int field_186024_e = 0;

    public BlockStateContainer()
    {
        this.func_186012_b(4);
    }

    private static int getIndex(int x, int y, int z)
    {
        return y << 8 | z << 4 | x;
    }

    private void func_186012_b(int p_186012_1_)
    {
        if (p_186012_1_ != this.field_186024_e)
        {
            this.field_186024_e = p_186012_1_;

            if (this.field_186024_e <= 4)
            {
                this.field_186024_e = 4;
                this.field_186022_c = new BlockStatePaletteLinear(this.field_186024_e, this);
            }
            else if (this.field_186024_e <= 8)
            {
                this.field_186022_c = new BlockStatePaletteHashMap(this.field_186024_e, this);
            }
            else
            {
                this.field_186022_c = REGISTRY_BASED_PALETTE;
                this.field_186024_e = MathHelper.calculateLogBaseTwoDeBruijn(Block.BLOCK_STATE_IDS.size());
            }

            this.field_186022_c.func_186041_a(AIR_BLOCK_STATE);
            this.bits = new BitArray(this.field_186024_e, 4096);
        }
    }

    public int func_186008_a(int p_186008_1_, IBlockState p_186008_2_)
    {
        BitArray bitarray = this.bits;
        IBlockStatePalette iblockstatepalette = this.field_186022_c;
        this.func_186012_b(p_186008_1_);

        for (int i = 0; i < bitarray.size(); ++i)
        {
            IBlockState iblockstate = iblockstatepalette.getBlockState(bitarray.func_188142_a(i));

            if (iblockstate != null)
            {
                this.set(i, iblockstate);
            }
        }

        return this.field_186022_c.func_186041_a(p_186008_2_);
    }

    public void set(int x, int y, int z, IBlockState state)
    {
        this.set(getIndex(x, y, z), state);
    }

    protected void set(int p_186014_1_, IBlockState state)
    {
        int i = this.field_186022_c.func_186041_a(state);
        this.bits.func_188141_a(p_186014_1_, i);
    }

    public IBlockState get(int x, int y, int z)
    {
        return this.get(getIndex(x, y, z));
    }

    protected IBlockState get(int p_186015_1_)
    {
        IBlockState iblockstate = this.field_186022_c.getBlockState(this.bits.func_188142_a(p_186015_1_));
        return iblockstate == null ? AIR_BLOCK_STATE : iblockstate;
    }

    @SideOnly(Side.CLIENT)
    public void read(PacketBuffer buf)
    {
        int i = buf.readByte();

        if (this.field_186024_e != i)
        {
            this.func_186012_b(i);
        }

        this.field_186022_c.read(buf);
        buf.readLongArray(this.bits.func_188143_a());
    }

    public void write(PacketBuffer buf)
    {
        buf.writeByte(this.field_186024_e);
        this.field_186022_c.write(buf);
        buf.writeLongArray(this.bits.func_188143_a());
    }

    public NibbleArray getDataForNBT(byte[] p_186017_1_, NibbleArray p_186017_2_)
    {
        NibbleArray nibblearray = null;

        for (int i = 0; i < 4096; ++i)
        {
            int j = Block.BLOCK_STATE_IDS.get(this.get(i));
            int k = i & 15;
            int l = i >> 8 & 15;
            int i1 = i >> 4 & 15;

            if ((j >> 12 & 15) != 0)
            {
                if (nibblearray == null)
                {
                    nibblearray = new NibbleArray();
                }

                nibblearray.set(k, l, i1, j >> 12 & 15);
            }

            p_186017_1_[i] = (byte)(j >> 4 & 255);
            p_186017_2_.set(k, l, i1, j & 15);
        }

        return nibblearray;
    }

    public void setDataFromNBT(byte[] p_186019_1_, NibbleArray p_186019_2_, NibbleArray p_186019_3_)
    {
        for (int i = 0; i < 4096; ++i)
        {
            int j = i & 15;
            int k = i >> 8 & 15;
            int l = i >> 4 & 15;
            int i1 = p_186019_3_ == null ? 0 : p_186019_3_.get(j, k, l);
            int j1 = i1 << 12 | (p_186019_1_[i] & 255) << 4 | p_186019_2_.get(j, k, l);
            this.set(i, (IBlockState)Block.BLOCK_STATE_IDS.getByValue(j1));
        }
    }

    public int func_186018_a()
    {
        int i = this.bits.size();
        return 1 + this.field_186022_c.func_186040_a() + PacketBuffer.getVarIntSize(i) + i * 8;
    }
}