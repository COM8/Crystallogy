package de.comeight.crystallogy.entity;

import de.comeight.crystallogy.entity.ai.EntityAiBaseSerializable;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityMagicStoneOfForgetfulness extends EntityThrowable{
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityMagicStoneOfForgetfulness(World worldIn)
    {
        super(worldIn);
    }

    public EntityMagicStoneOfForgetfulness(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityMagicStoneOfForgetfulness(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null)
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 1.0F);
            if(Utilities.getRandInt(0, 2) == 0 && result.entityHit instanceof EntityLiving){
            	removeAi((EntityLiving) result.entityHit);
            }
            spawnHitParticle();
        }
		else if (!worldObj.isRemote)
        {
        	if(Utilities.getRandInt(0, 10, rand) >= 3){
        		IBlockState state = worldObj.getBlockState(result.getBlockPos());
            	if(state != null && state.getMaterial() == Material.GLASS){
            		worldObj.destroyBlock(result.getBlockPos(), true);
            	}
        	}
        	else{
        		setDead();
        		worldObj.playSound((EntityPlayer)null, posX, posY, posZ, SoundEvents.ENTITY_ARMORSTAND_HIT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (rand.nextFloat() * 0.4F + 0.8F));
        	}
        }
		spawnBreakParticle();
	}
	
	private void removeAi(EntityLiving entity){
		boolean foundOne = false;
		
		do {
			foundOne = false;
			for(EntityAITasks.EntityAITaskEntry task: entity.tasks.taskEntries){
				if(task.action instanceof EntityAiBaseSerializable){
					entity.tasks.removeTask(task.action);
					foundOne = true;
					EntityLivingBase e = getThrower();
					e.addChatMessage(new TextComponentString("Successfully removed custom Ai!"));
					break;
				}
			}	
		} while (foundOne);
	}
	
	private void spawnBreakParticle(){
		if(!worldObj.isRemote){
			return;
		}
		for (int k = 0; k < 8; ++k)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, new int[] {Item.getIdFromItem(Item.getItemFromBlock(Blocks.COBBLESTONE))});
        }
	}
	
	private void spawnHitParticle(){
		if(!worldObj.isRemote){
			return;
		}
		for (int k = 0; k < 20; ++k)
        {
            this.worldObj.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, ((double)this.rand.nextFloat() - 0.5D) * 0.08D, new int[] {Item.getIdFromItem(Items.APPLE)});
        }
	}
	
}
