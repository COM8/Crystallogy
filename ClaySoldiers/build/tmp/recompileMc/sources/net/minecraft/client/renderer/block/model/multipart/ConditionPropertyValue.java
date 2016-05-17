package net.minecraft.client.renderer.block.model.multipart;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import java.util.List;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ConditionPropertyValue implements ICondition
{
    private static final Splitter SPLITTER = Splitter.on('|').omitEmptyStrings();
    private final String key;
    private final String value;

    public ConditionPropertyValue(String p_i46565_1_, String p_i46565_2_)
    {
        this.key = p_i46565_1_;
        this.value = p_i46565_2_;
    }

    public Predicate<IBlockState> getPredicate(BlockStateContainer blockState)
    {
        final IProperty<?> iproperty = blockState.getProperty(this.key);

        if (iproperty == null)
        {
            throw new RuntimeException(this.toString() + ": Definition: " + blockState + " has no property: " + this.key);
        }
        else
        {
            String s = this.value;
            boolean flag = !s.isEmpty() && s.charAt(0) == 33;

            if (flag)
            {
                s = s.substring(1);
            }

            List<String> list = SPLITTER.splitToList(s);

            if (list.isEmpty())
            {
                throw new RuntimeException(this.toString() + ": has an empty value: " + this.value);
            }
            else
            {
                Predicate<IBlockState> predicate;

                if (list.size() == 1)
                {
                    predicate = this.makePredicate(iproperty, s);
                }
                else
                {
                    predicate = Predicates.or(Iterables.transform(list, new Function<String, Predicate<IBlockState>>()
                    {
                        public Predicate<IBlockState> apply(String p_apply_1_)
                        {
                            return ConditionPropertyValue.this.makePredicate(iproperty, p_apply_1_);
                        }
                    }));
                }

                return flag ? Predicates.not(predicate) : predicate;
            }
        }
    }

    private Predicate<IBlockState> makePredicate(final IProperty<?> p_188123_1_, String p_188123_2_)
    {
        final Optional<?> optional = p_188123_1_.parseValue(p_188123_2_);

        if (!optional.isPresent())
        {
            throw new RuntimeException(this.toString() + ": has an unknown value: " + this.value);
        }
        else
        {
            return new Predicate<IBlockState>()
            {
                public boolean apply(IBlockState p_apply_1_)
                {
                    return p_apply_1_ != null && p_apply_1_.getValue(p_188123_1_).equals(optional.get());
                }
            };
        }
    }

    public String toString()
    {
        return Objects.toStringHelper(this).add("key", this.key).add("value", this.value).toString();
    }
}