package de.comeight.crystallogy.tabs;

import java.util.Collections;
import java.util.List;

import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CrystallogyMainTab extends CreativeTabs{
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystallogyMainTab() {
		super(CreativeTabs.getNextID(), "crystallogy");
		
		setBackgroundImageName("item_search.png");
		
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
    {
		return Item.getItemFromBlock(BlockHandler.crystall_green);
    }
	
	@Override
	public boolean hasSearchBar() {
		return true;
	}
	
	@Override
	public void displayAllRelevantItems(List<ItemStack> list) {
		super.displayAllRelevantItems(sortList(list));
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private List<ItemStack> sortList(List<ItemStack> list){
		while(!sorted(list)){
		}
		return list;
	}
	
	private boolean sorted(List<ItemStack> list){
		boolean sorted = true;
		System.out.println(list.size());
		for (int i = 0; i < list.size() - 1; i++) {
			String name1 = list.get(i).getDisplayName();
			String name2 = list.get(i + 1).getDisplayName();
			if(name1.compareTo(name2) < 0){
				System.out.println("sort");
				ItemStack temp1 = list.get(i);
				ItemStack temp2 = list.get(i + 1);
				
				list.set(i, temp2);
				list.set(i + 1, temp1);
				sorted = false;
			}
		}
		return sorted;
	}
	
	public int compare(String str1, String str2) {
        int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
        if (res == 0) {
            res = str1.compareTo(str2);
        }
        return res;
    }
	
}
