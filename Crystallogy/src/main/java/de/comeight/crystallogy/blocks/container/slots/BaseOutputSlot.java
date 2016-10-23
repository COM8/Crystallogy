package de.comeight.crystallogy.blocks.container.slots;

import javax.annotation.Nullable;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

public abstract class BaseOutputSlot extends Slot{
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected int removeCount;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseOutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public boolean isItemValid(@Nullable ItemStack stack)
    {
        return false;
    }
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public ItemStack decrStackSize(int amount)
    {
        if (this.getHasStack())
        {
            this.removeCount += Math.min(amount, this.getStack().stackSize);
        }

        return super.decrStackSize(amount);
    }
	
	
	@Override
    public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack)
    {
        onCrafting(stack);
        spawnXP(playerIn, stack);
        super.onPickupFromSlot(playerIn, stack);
    }

	@Override
    protected void onCrafting(ItemStack stack, int amount)
    {
        this.removeCount += amount;
        this.onCrafting(stack);
    }
	
	protected void spawnXP(EntityPlayer playerIn, ItemStack stack){
		if (!playerIn.worldObj.isRemote)
        {
			System.out.println(removeCount);
            int count = this.removeCount;
            float xp = getXPFromRecipeHandler(stack);
            
            if (xp == 0.0F)
            {
                count = 0;
            }
            else if (xp < 1.0F)
            {
                int j = MathHelper.floor_float((float)count * xp);

                if (j < MathHelper.ceiling_float_int((float)count * xp) && Math.random() < (double)((float)count * xp - (float)j))
                {
                    ++j;
                }

                count = j;
            }
            while (count > 0)
            {
                int k = EntityXPOrb.getXPSplit(count);
                count -= k;
                playerIn.worldObj.spawnEntityInWorld(new EntityXPOrb(playerIn.worldObj, playerIn.posX, playerIn.posY + 0.5D, playerIn.posZ + 0.5D, k));
            }
        }

        this.removeCount = 0;
	}
	
	protected abstract float getXPFromRecipeHandler(ItemStack stack);
	
}
