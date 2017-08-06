package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.util.Const;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public abstract class CrystalKnife extends BaseItemShovel{
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalKnife(String id) {
        super(CustomToolMaterials.CRYSTAL_RED, id);
        damageVsEntity = 0.5F;
        attackSpeed = 0F;
        setMaxDamage(5);
        setNoRepair();
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------
    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getBoolean(Const.CRYSTAL_KNIFE_HAS_ENTITY);
    }

    protected void setEntity(ItemStack stack, Entity e) {
        if(!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound nbt = stack.getTagCompound();
        nbt.setInteger(Const.CRYSTAL_KNIFE_ENTITY_ID, e.getEntityId());
        nbt.setBoolean(Const.CRYSTAL_KNIFE_HAS_ENTITY, true);
    }

    protected boolean hasEntity(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getBoolean(Const.CRYSTAL_KNIFE_HAS_ENTITY);
    }

    protected void removeEntity(ItemStack stack) {
        if(!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound nbt = stack.getTagCompound();
        nbt.setBoolean(Const.CRYSTAL_KNIFE_HAS_ENTITY, false);
    }

    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if(!hasEntity(stack)) {
            setEntity(stack, entity);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(playerIn.isSneaking()) {
            removeEntity(playerIn.getHeldItem(handIn));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    //-----------------------------------------------Events:------------------------------------------------

}