package de.comeight.crystallogy.items;

import java.util.List;

import de.comeight.crystallogy.blocks.EnumCrystalColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CrystallSwordBlade extends BaseItem {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "crystallSwordBlade";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystallSwordBlade() {
		super(ID);
		setHasSubtypes(true);
        setMaxDamage(0);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	/**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
	@Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "_" + EnumCrystalColor.byMetadata(stack.getMetadata()).getName();
    }
	
	/**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (int i = 0; i < 4; ++i)
        {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }
	
    
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
    
	
}
