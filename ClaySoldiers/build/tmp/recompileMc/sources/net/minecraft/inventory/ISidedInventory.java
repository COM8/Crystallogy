package net.minecraft.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public interface ISidedInventory extends IInventory
{
    int[] getSlotsForFace(EnumFacing side);

    /**
     * Returns true if automation can insert the given item in the given slot from the given side.
     *  
     * @param index The slot index to test insertion into
     * @param itemStackIn The item to test insertion of
     * @param direction The direction to test insertion from
     */
    boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction);

    /**
     * Returns true if automation can extract the given item in the given slot from the given side.
     *  
     * @param index The slot index to test extraction from
     * @param stack The item to try to extract
     * @param direction The direction to extract from
     */
    boolean canExtractItem(int index, ItemStack stack, EnumFacing direction);
}