package de.comeight.crystallogy.items;

import java.util.List;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.network.MessageToClient;
import de.comeight.crystallogy.network.MessageToServer;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.BaseParticle;
import de.comeight.crystallogy.particles.ParticleHandler;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Vaporizer extends BaseItem {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "vaporizer";
	
	private final String particleType;
	private final RGBColor color;
	private final int maxTime;
	private final int numberOfParticle;
	private final Vec3d size;
	
	private boolean activated;

	//-----------------------------------------------Constructor:-------------------------------------------
	public Vaporizer() {
		super(ID);
		this.setMaxStackSize(1);
		
		this.particleType = "a";
		this.color = new RGBColor(0.0F, 0.0F, 0.0F);
		this.maxTime = 60;
		this.numberOfParticle = 10;
		this.size = new Vec3d(1.0, 2.0, 1.0);
		this.activated = false;
	}
	
	public Vaporizer(String ID) {
		super(ID);
		this.setMaxStackSize(1);
		
		this.particleType = "a";
		this.color = new RGBColor(0.0F, 0.0F, 0.0F);
		this.maxTime = 60;
		this.numberOfParticle = 10;
		this.size = new Vec3d(1.0, 2.0, 1.0);
		this.activated = false;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public int getNumberOfParticle() {
		return numberOfParticle;
	}

	public Vec3d getSize() {
		return size;
	}
	
	public String getParticleType() {
		return particleType;
	}

	public RGBColor getColor() {
		return color;
	}

	public int getMaxTime() {
		return maxTime;
	}

	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
		NBTTagCompound c = stack.getTagCompound();
		if(c == null){
			return false;
		}
        return c.getBoolean("activated");
    }
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		if(stack.getTagCompound() == null){
			return;
		}
		if(stack.getTagCompound().getBoolean("activated") == true){
			if(Utilities.getRandInt(0, 3) == 0){
				Vec3d coords = entityIn.getPositionEyes(1.0F).subtract(0, entityIn.getEyeHeight(), 0);
				spawnParticles(coords, stack, worldIn);
			}
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(!worldIn.isRemote){
			return new ActionResult(EnumActionResult.PASS, itemStackIn);
		}
		else{
			if(playerIn.isSneaking()){
				NBTTagCompound c = itemStackIn.getTagCompound();
				if(c == null){
					itemStackIn = saveNBT(itemStackIn);
				}
				c.setBoolean("activated", !c.getBoolean("activated"));
			}
			else{
				Vec3d coords = playerIn.getPositionEyes(1.0F).subtract(0, playerIn.getEyeHeight(), 0);
				spawnParticles(coords, itemStackIn, worldIn);
			}
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		}
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}
	
	public void spawnParticles(Vec3d coords, ItemStack stack, World worldIn){
		NBTTagCompound c = stack.getTagCompound();
		if(c == null){
			stack = saveNBT(stack);
		}
		
		String type = c.getString("particleType");
		
		BaseParticle p = ParticleHandler.getBaseParticleFromType(type);
		p.setPosition(coords.xCoord, coords.yCoord, coords.zCoord);
		p.setParticleMaxAge(c.getInteger("maxTime"));
		NetworkParticle nP = new NetworkParticle(p, type);
		nP.getParticle().setRBGColorF(c.getFloat("r"), c.getFloat("g"), c.getFloat("b"));
		nP.setSize(new Vec3d(c.getDouble("sizeX"), c.getDouble("sizeY"), c.getDouble("sizeZ")));
		nP.setNumberOfParticle(c.getInteger("numberOfParticle"));
		
		MessageToServer message = new MessageToClient(nP);
		CommonProxy.NETWORKWRAPPER.sendToServer(message);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		NBTTagCompound nbtTagCompound = stack.getTagCompound();
		if(nbtTagCompound == null){
			stack = saveNBT(stack);
			tooltip.add("Type: -");
			tooltip.add("Color: R= -, G= -, B= -");
			tooltip.add("Max particle age: -");
			tooltip.add("Size: X= -, Y= -, Z= -");
			tooltip.add("Number of particles: -");
			tooltip.add("Activated: -");
			tooltip.add("§4THIS IS A BUG!!§r");
		}
		else{
			if(nbtTagCompound.getBoolean("activated")){
				tooltip.add("Activated: §2true§r");
			}
			else{
				tooltip.add("Activated: §4false§r");
			}
			if(GuiScreen.isShiftKeyDown()){
				tooltip.add("");
				tooltip.add("Type: " + nbtTagCompound.getString("particleType"));
				tooltip.add("Color: §4R§7=" + 255 * nbtTagCompound.getFloat("r") + ", §2G§7=" + 255 * nbtTagCompound.getFloat("g") + ", §9B§7=" + 255 * nbtTagCompound.getFloat("b"));
				tooltip.add("Max particle age: " + nbtTagCompound.getInteger("maxTime"));
				tooltip.add("Size: X= +-" + nbtTagCompound.getDouble("sizeX") + ", Y= +" + nbtTagCompound.getDouble("sizeY") + ", Z= +-" + nbtTagCompound.getDouble("sizeZ"));
				tooltip.add("Number of particles: " + nbtTagCompound.getInteger("numberOfParticle"));
			}
			else{
				tooltip.add(TextFormatting.YELLOW + ">>Press SHIFT for more informations!<<");
			}
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
	public ItemStack saveNBT(ItemStack stack){
		NBTTagCompound nbtTagCompound = stack.getTagCompound();
		if(nbtTagCompound == null){
			nbtTagCompound = new NBTTagCompound();
		}
		
		nbtTagCompound.setString("particleType", particleType);
		
		nbtTagCompound.setFloat("r", color.r);
		nbtTagCompound.setFloat("g", color.g);
		nbtTagCompound.setFloat("b", color.b);
		
		nbtTagCompound.setInteger("maxTime", maxTime);
		
		nbtTagCompound.setInteger("numberOfParticle", numberOfParticle);
		
		nbtTagCompound.setDouble("sizeX", size.xCoord);
		nbtTagCompound.setDouble("sizeY", size.yCoord);
		nbtTagCompound.setDouble("sizeZ", size.zCoord);
		
		nbtTagCompound.setBoolean("activated", activated);
		
		stack.setTagCompound(nbtTagCompound);
		return stack;
	}
	
	public ItemStack getItemStack(){
		return this.saveNBT(new ItemStack(this));
	}
	
}
