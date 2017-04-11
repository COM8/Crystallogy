package de.comeight.crystallogy.gui.bookOfKnowledge;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookMultiItemRenderer {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private ItemStack[] items;
	private long lastFrameInc;
	private int frame;
	private int perFrameTime;
	private float scale;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookMultiItemRenderer(ItemStack[] items, int perFrameTime, float scale) {
		this.items = items;
		this.perFrameTime = perFrameTime;
		this.scale = scale;
		this.lastFrameInc = System.currentTimeMillis();
		this.frame = 0;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void drawItem(int x, int y){
		GuiBookUtilities.renderItem(items[frame], x, y, scale);
		decFrameTime();
	}
	
	private void decFrameTime(){
		if(System.currentTimeMillis() >= lastFrameInc + perFrameTime){
			frame++;
			lastFrameInc = System.currentTimeMillis();
			if(frame >= items.length){
				frame = 0;
			}
		}
	}
	
}
