package de.comeight.crystallogy.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrystalGlas extends BaseBlockGlass {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public final static String ID = "crystalGlas";
	
	//0=red, 1=blue, 2=green, 3=yellow
	public static final PropertyInteger COLOR = PropertyInteger.create("color", 0, 3);
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystalGlas() {
		super(Material.GLASS, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------    
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, java.util.List<ItemStack> list) {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 1));
		list.add(new ItemStack(this, 1, 2));
		list.add(new ItemStack(this, 1, 3));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(COLOR, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(COLOR);
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(COLOR);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {COLOR});
	}
	
}
