package net.minecraft.client.multiplayer;

import io.netty.buffer.Unpooled;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketEnchantItem;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerBlockPlacement;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlayerControllerMP
{
    /** The Minecraft instance. */
    private final Minecraft mc;
    private final NetHandlerPlayClient netClientHandler;
    private BlockPos currentBlock = new BlockPos(-1, -1, -1);
    /** The Item currently being used to destroy a block */
    private ItemStack currentItemHittingBlock;
    /** Current block damage (MP) */
    private float curBlockDamageMP;
    /** Tick counter, when it hits 4 it resets back to 0 and plays the step sound */
    private float stepSoundTickCounter;
    /** Delays the first damage on the block after the first click on the block */
    private int blockHitDelay;
    /** Tells if the player is hitting a block */
    private boolean isHittingBlock;
    /** Current game type for the player */
    private WorldSettings.GameType currentGameType = WorldSettings.GameType.SURVIVAL;
    /** Index of the current item held by the player in the inventory hotbar */
    private int currentPlayerItem;

    public PlayerControllerMP(Minecraft mcIn, NetHandlerPlayClient netHandler)
    {
        this.mc = mcIn;
        this.netClientHandler = netHandler;
    }

    public static void clickBlockCreative(Minecraft mcIn, PlayerControllerMP playerController, BlockPos pos, EnumFacing facing)
    {
        if (!mcIn.theWorld.extinguishFire(mcIn.thePlayer, pos, facing))
        {
            playerController.func_187103_a(pos);
        }
    }

    /**
     * Sets player capabilities depending on current gametype. params: player
     */
    public void setPlayerCapabilities(EntityPlayer player)
    {
        this.currentGameType.configurePlayerCapabilities(player.capabilities);
    }

    /**
     * None
     */
    public boolean isSpectator()
    {
        return this.currentGameType == WorldSettings.GameType.SPECTATOR;
    }

    /**
     * Sets the game type for the player.
     */
    public void setGameType(WorldSettings.GameType type)
    {
        this.currentGameType = type;
        this.currentGameType.configurePlayerCapabilities(this.mc.thePlayer.capabilities);
    }

    /**
     * Flips the player around.
     */
    public void flipPlayer(EntityPlayer playerIn)
    {
        playerIn.rotationYaw = -180.0F;
    }

    public boolean shouldDrawHUD()
    {
        return this.currentGameType.isSurvivalOrAdventure();
    }

    public boolean func_187103_a(BlockPos pos)
    {
        if (this.currentGameType.isAdventure())
        {
            if (this.currentGameType == WorldSettings.GameType.SPECTATOR)
            {
                return false;
            }

            if (!this.mc.thePlayer.isAllowEdit())
            {
                ItemStack itemstack = this.mc.thePlayer.getHeldItemMainhand();

                if (itemstack == null)
                {
                    return false;
                }

                if (!itemstack.canDestroy(this.mc.theWorld.getBlockState(pos).getBlock()))
                {
                    return false;
                }
            }
        }

        ItemStack stack = mc.thePlayer.getHeldItemMainhand();
        if (stack != null && stack.getItem() != null && stack.getItem().onBlockStartBreak(stack, pos, mc.thePlayer))
        {
            return false;
        }

        if (this.currentGameType.isCreative() && this.mc.thePlayer.getHeldItemMainhand() != null && this.mc.thePlayer.getHeldItemMainhand().getItem() instanceof ItemSword)
        {
            return false;
        }
        else
        {
            World world = this.mc.theWorld;
            IBlockState iblockstate = world.getBlockState(pos);
            Block block = iblockstate.getBlock();

            if (block instanceof BlockCommandBlock && !this.mc.thePlayer.canCommandSenderUseCommand(2, ""))
            {
                return false;
            }
            else if (iblockstate.getMaterial() == Material.air)
            {
                return false;
            }
            else
            {
                world.playAuxSFX(2001, pos, Block.getStateId(iblockstate));

                this.currentBlock = new BlockPos(this.currentBlock.getX(), -1, this.currentBlock.getZ());

                if (!this.currentGameType.isCreative())
                {
                    ItemStack itemstack1 = this.mc.thePlayer.getHeldItemMainhand();

                    if (itemstack1 != null)
                    {
                        itemstack1.onBlockDestroyed(world, iblockstate, pos, this.mc.thePlayer);

                        if (itemstack1.stackSize <= 0)
                        {
                            net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(this.mc.thePlayer, itemstack1, EnumHand.MAIN_HAND);
                            this.mc.thePlayer.setHeldItem(EnumHand.MAIN_HAND, (ItemStack)null);
                        }
                    }
                }

                boolean flag = block.removedByPlayer(iblockstate, world, pos, mc.thePlayer, false);

                if (flag)
                {
                    block.onBlockDestroyedByPlayer(world, pos, iblockstate);
                }
                return flag;
            }
        }
    }

    /**
     * Called when the player is hitting a block with an item.
     */
    public boolean clickBlock(BlockPos loc, EnumFacing face)
    {
        if (this.currentGameType.isAdventure())
        {
            if (this.currentGameType == WorldSettings.GameType.SPECTATOR)
            {
                return false;
            }

            if (!this.mc.thePlayer.isAllowEdit())
            {
                ItemStack itemstack = this.mc.thePlayer.getHeldItemMainhand();

                if (itemstack == null)
                {
                    return false;
                }

                if (!itemstack.canDestroy(this.mc.theWorld.getBlockState(loc).getBlock()))
                {
                    return false;
                }
            }
        }

        if (!this.mc.theWorld.getWorldBorder().contains(loc))
        {
            return false;
        }
        else
        {
            if (this.currentGameType.isCreative())
            {
                this.netClientHandler.addToSendQueue(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, loc, face));
                clickBlockCreative(this.mc, this, loc, face);
                this.blockHitDelay = 5;
            }
            else if (!this.isHittingBlock || !this.isHittingPosition(loc))
            {
                if (this.isHittingBlock)
                {
                    this.netClientHandler.addToSendQueue(new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.currentBlock, face));
                }

                this.netClientHandler.addToSendQueue(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, loc, face));
                net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock event = net.minecraftforge.common.ForgeHooks.onLeftClickBlock(this.mc.thePlayer, loc, face, net.minecraftforge.common.ForgeHooks.rayTraceEyeHitVec(this.mc.thePlayer, getBlockReachDistance() + 1));

                IBlockState iblockstate = this.mc.theWorld.getBlockState(loc);
                boolean flag = iblockstate.getMaterial() != Material.air;

                if (flag && this.curBlockDamageMP == 0.0F)
                {
                    if (event.getUseBlock() != net.minecraftforge.fml.common.eventhandler.Event.Result.DENY)
                    iblockstate.getBlock().onBlockClicked(this.mc.theWorld, loc, this.mc.thePlayer);
                }
                if (event.getUseItem() == net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) return true;
                if (flag && iblockstate.getPlayerRelativeBlockHardness(this.mc.thePlayer, this.mc.thePlayer.worldObj, loc) >= 1.0F)
                {
                    this.func_187103_a(loc);
                }
                else
                {
                    this.isHittingBlock = true;
                    this.currentBlock = loc;
                    this.currentItemHittingBlock = this.mc.thePlayer.getHeldItemMainhand();
                    this.curBlockDamageMP = 0.0F;
                    this.stepSoundTickCounter = 0.0F;
                    this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.getEntityId(), this.currentBlock, (int)(this.curBlockDamageMP * 10.0F) - 1);
                }
            }

            return true;
        }
    }

    /**
     * Resets current block damage and field_78778_j
     */
    public void resetBlockRemoving()
    {
        if (this.isHittingBlock)
        {
            this.netClientHandler.addToSendQueue(new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.currentBlock, EnumFacing.DOWN));
            this.isHittingBlock = false;
            this.curBlockDamageMP = 0.0F;
            this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.getEntityId(), this.currentBlock, -1);
            this.mc.thePlayer.resetCooldown();
        }
    }

    public boolean onPlayerDamageBlock(BlockPos posBlock, EnumFacing directionFacing)
    {
        this.syncCurrentPlayItem();

        if (this.blockHitDelay > 0)
        {
            --this.blockHitDelay;
            return true;
        }
        else if (this.currentGameType.isCreative() && this.mc.theWorld.getWorldBorder().contains(posBlock))
        {
            this.blockHitDelay = 5;
            this.netClientHandler.addToSendQueue(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, posBlock, directionFacing));
            clickBlockCreative(this.mc, this, posBlock, directionFacing);
            return true;
        }
        else if (this.isHittingPosition(posBlock))
        {
            IBlockState iblockstate = this.mc.theWorld.getBlockState(posBlock);
            Block block = iblockstate.getBlock();

            if (iblockstate.getMaterial() == Material.air)
            {
                this.isHittingBlock = false;
                return false;
            }
            else
            {
                this.curBlockDamageMP += iblockstate.getPlayerRelativeBlockHardness(this.mc.thePlayer, this.mc.thePlayer.worldObj, posBlock);

                if (this.stepSoundTickCounter % 4.0F == 0.0F)
                {
                    SoundType soundtype = block.getStepSound();
                    this.mc.getSoundHandler().playSound(new PositionedSoundRecord(soundtype.getHitSound(), SoundCategory.NEUTRAL, (soundtype.getVolume() + 1.0F) / 8.0F, soundtype.getPitch() * 0.5F, posBlock));
                }

                ++this.stepSoundTickCounter;

                if (this.curBlockDamageMP >= 1.0F)
                {
                    this.isHittingBlock = false;
                    this.netClientHandler.addToSendQueue(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, posBlock, directionFacing));
                    this.func_187103_a(posBlock);
                    this.curBlockDamageMP = 0.0F;
                    this.stepSoundTickCounter = 0.0F;
                    this.blockHitDelay = 5;
                }

                this.mc.theWorld.sendBlockBreakProgress(this.mc.thePlayer.getEntityId(), this.currentBlock, (int)(this.curBlockDamageMP * 10.0F) - 1);
                return true;
            }
        }
        else
        {
            return this.clickBlock(posBlock, directionFacing);
        }
    }

    /**
     * player reach distance = 4F
     */
    public float getBlockReachDistance()
    {
        return this.currentGameType.isCreative() ? 5.0F : 4.5F;
    }

    public void updateController()
    {
        this.syncCurrentPlayItem();

        if (this.netClientHandler.getNetworkManager().isChannelOpen())
        {
            this.netClientHandler.getNetworkManager().processReceivedPackets();
        }
        else
        {
            this.netClientHandler.getNetworkManager().checkDisconnected();
        }
    }

    private boolean isHittingPosition(BlockPos pos)
    {
        ItemStack itemstack = this.mc.thePlayer.getHeldItemMainhand();
        boolean flag = this.currentItemHittingBlock == null && itemstack == null;

        if (this.currentItemHittingBlock != null && itemstack != null)
        {
            flag = itemstack.getItem() == this.currentItemHittingBlock.getItem() && ItemStack.areItemStackTagsEqual(itemstack, this.currentItemHittingBlock) && (itemstack.isItemStackDamageable() || itemstack.getMetadata() == this.currentItemHittingBlock.getMetadata());
        }

        return pos.equals(this.currentBlock) && flag;
    }

    /**
     * Syncs the current player item with the server
     */
    private void syncCurrentPlayItem()
    {
        int i = this.mc.thePlayer.inventory.currentItem;

        if (i != this.currentPlayerItem)
        {
            this.currentPlayerItem = i;
            this.netClientHandler.addToSendQueue(new CPacketHeldItemChange(this.currentPlayerItem));
        }
    }

    public EnumActionResult processRightClickBlock(EntityPlayerSP player, WorldClient worldIn, ItemStack stack, BlockPos pos, EnumFacing facing, Vec3d vec, EnumHand hand)
    {
        this.syncCurrentPlayItem();
        float f = (float)(vec.xCoord - (double)pos.getX());
        float f1 = (float)(vec.yCoord - (double)pos.getY());
        float f2 = (float)(vec.zCoord - (double)pos.getZ());
        boolean flag = false;

        if (!this.mc.theWorld.getWorldBorder().contains(pos))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock event = net.minecraftforge.common.ForgeHooks
                    .onRightClickBlock(player, hand, stack, pos, facing, net.minecraftforge.common.ForgeHooks.rayTraceEyeHitVec(player, getBlockReachDistance() + 1));
            if (event.isCanceled())
            {
                // Give the server a chance to fire event as well. That way server event is not dependant on client event.
                this.netClientHandler.addToSendQueue(new CPacketPlayerTryUseItem(pos, facing, hand, f, f1, f2));
                return EnumActionResult.PASS;
            }
            EnumActionResult result = EnumActionResult.PASS;

            if (this.currentGameType != WorldSettings.GameType.SPECTATOR)
            {
                net.minecraft.item.Item item = stack == null ? null : stack.getItem();
                EnumActionResult ret = item == null ? EnumActionResult.PASS : item.onItemUseFirst(stack, player, worldIn, pos, facing, f, f1, f2, hand);
                if (ret != EnumActionResult.PASS) return ret;

                IBlockState iblockstate = worldIn.getBlockState(pos);
                boolean bypass = true;
                for (ItemStack s : new ItemStack[]{player.getHeldItemMainhand(), player.getHeldItemOffhand()}) //TODO: Expand to more hands? player.inv.getHands()?
                    bypass = bypass && (s == null || s.getItem().doesSneakBypassUse(s, worldIn, pos, player));

                if (!player.isSneaking() || bypass || event.getUseBlock() == net.minecraftforge.fml.common.eventhandler.Event.Result.ALLOW)
                {
                    if(event.getUseBlock() != net.minecraftforge.fml.common.eventhandler.Event.Result.DENY)
                    flag = iblockstate.getBlock().onBlockActivated(worldIn, pos, iblockstate, player, hand, stack, facing, f, f1, f2);
                    if(flag) result = EnumActionResult.SUCCESS;
                }

                if (!flag && stack != null && stack.getItem() instanceof ItemBlock)
                {
                    ItemBlock itemblock = (ItemBlock)stack.getItem();

                    if (!itemblock.canPlaceBlockOnSide(worldIn, pos, facing, player, stack))
                    {
                        return EnumActionResult.FAIL;
                    }
                }
            }

            this.netClientHandler.addToSendQueue(new CPacketPlayerTryUseItem(pos, facing, hand, f, f1, f2));

            if (!flag && this.currentGameType != WorldSettings.GameType.SPECTATOR || event.getUseItem() == net.minecraftforge.fml.common.eventhandler.Event.Result.ALLOW)
            {
                if (stack == null)
                {
                    return EnumActionResult.PASS;
                }
                else if (player.getCooldownTracker().hasCooldown(stack.getItem()))
                {
                    return EnumActionResult.PASS;
                }
                else if (stack.getItem() instanceof ItemBlock && ((ItemBlock)stack.getItem()).getBlock() instanceof BlockCommandBlock && !player.canCommandSenderUseCommand(2, ""))
                {
                    return EnumActionResult.FAIL;
                }
                else if (this.currentGameType.isCreative())
                {
                    int i = stack.getMetadata();
                    int j = stack.stackSize;
                    if (event.getUseItem() != net.minecraftforge.fml.common.eventhandler.Event.Result.DENY) {
                    EnumActionResult enumactionresult = stack.onItemUse(player, worldIn, pos, hand, facing, f, f1, f2);
                    stack.setItemDamage(i);
                    stack.stackSize = j;
                    return enumactionresult;
                    } else return result;
                }
                else
                {
                    if (event.getUseItem() != net.minecraftforge.fml.common.eventhandler.Event.Result.DENY)
                    result = stack.onItemUse(player, worldIn, pos, hand, facing, f, f1, f2);
                    if (stack.stackSize <= 0) net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, stack, hand);
                    return result;
                }
            }
            else
            {
                return EnumActionResult.SUCCESS;
            }
        }
    }

    public EnumActionResult processRightClick(EntityPlayer player, World worldIn, ItemStack stack, EnumHand hand)
    {
        if (this.currentGameType == WorldSettings.GameType.SPECTATOR)
        {
            return EnumActionResult.PASS;
        }
        else
        {
            this.syncCurrentPlayItem();
            this.netClientHandler.addToSendQueue(new CPacketPlayerBlockPlacement(hand));

            if (player.getCooldownTracker().hasCooldown(stack.getItem()))
            {
                return EnumActionResult.PASS;
            }
            else
            {
                if (net.minecraftforge.common.ForgeHooks.onItemRightClick(player, hand, stack)) return net.minecraft.util.EnumActionResult.PASS;
                int i = stack.stackSize;
                ActionResult<ItemStack> actionresult = stack.useItemRightClick(worldIn, player, hand);
                ItemStack itemstack = (ItemStack)actionresult.getResult();

                if (itemstack != stack || itemstack.stackSize != i)
                {
                    player.setHeldItem(hand, itemstack);

                    if (itemstack.stackSize <= 0)
                    {
                        player.setHeldItem(hand, (ItemStack)null);
                        net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, itemstack, hand);
                    }
                }

                return actionresult.getType();
            }
        }
    }

    public EntityPlayerSP createClientPlayer(World worldIn, StatFileWriter statWriter)
    {
        return new EntityPlayerSP(this.mc, worldIn, this.netClientHandler, statWriter);
    }

    /**
     * Attacks an entity
     */
    public void attackEntity(EntityPlayer playerIn, Entity targetEntity)
    {
        this.syncCurrentPlayItem();
        this.netClientHandler.addToSendQueue(new CPacketUseEntity(targetEntity));

        if (this.currentGameType != WorldSettings.GameType.SPECTATOR)
        {
            playerIn.attackTargetEntityWithCurrentItem(targetEntity);
            playerIn.resetCooldown();
        }
    }

    public EnumActionResult func_187097_a(EntityPlayer player, Entity p_187097_2_, ItemStack p_187097_3_, EnumHand hand)
    {
        this.syncCurrentPlayItem();
        this.netClientHandler.addToSendQueue(new CPacketUseEntity(p_187097_2_, hand));
        return this.currentGameType == WorldSettings.GameType.SPECTATOR ? EnumActionResult.PASS : player.interact(p_187097_2_, p_187097_3_, hand);
    }

    public EnumActionResult func_187102_a(EntityPlayer player, Entity p_187102_2_, RayTraceResult p_187102_3_, ItemStack p_187102_4_, EnumHand hand)
    {
        this.syncCurrentPlayItem();
        Vec3d vec3d = new Vec3d(p_187102_3_.hitVec.xCoord - p_187102_2_.posX, p_187102_3_.hitVec.yCoord - p_187102_2_.posY, p_187102_3_.hitVec.zCoord - p_187102_2_.posZ);
        this.netClientHandler.addToSendQueue(new CPacketUseEntity(p_187102_2_, hand, vec3d));
        return this.currentGameType == WorldSettings.GameType.SPECTATOR ? EnumActionResult.PASS : p_187102_2_.applyPlayerInteraction(player, vec3d, p_187102_4_, hand);
    }

    public ItemStack func_187098_a(int p_187098_1_, int p_187098_2_, int p_187098_3_, ClickType p_187098_4_, EntityPlayer player)
    {
        short short1 = player.openContainer.getNextTransactionID(player.inventory);
        ItemStack itemstack = player.openContainer.func_184996_a(p_187098_2_, p_187098_3_, p_187098_4_, player);
        this.netClientHandler.addToSendQueue(new CPacketClickWindow(p_187098_1_, p_187098_2_, p_187098_3_, p_187098_4_, itemstack, short1));
        return itemstack;
    }

    /**
     * GuiEnchantment uses this during multiplayer to tell PlayerControllerMP to send a packet indicating the
     * enchantment action the player has taken.
     */
    public void sendEnchantPacket(int windowID, int button)
    {
        this.netClientHandler.addToSendQueue(new CPacketEnchantItem(windowID, button));
    }

    /**
     * Used in PlayerControllerMP to update the server with an ItemStack in a slot.
     */
    public void sendSlotPacket(ItemStack itemStackIn, int slotId)
    {
        if (this.currentGameType.isCreative())
        {
            this.netClientHandler.addToSendQueue(new CPacketCreativeInventoryAction(slotId, itemStackIn));
        }
    }

    /**
     * Sends a Packet107 to the server to drop the item on the ground
     */
    public void sendPacketDropItem(ItemStack itemStackIn)
    {
        if (this.currentGameType.isCreative() && itemStackIn != null)
        {
            this.netClientHandler.addToSendQueue(new CPacketCreativeInventoryAction(-1, itemStackIn));
        }
    }

    public void onStoppedUsingItem(EntityPlayer playerIn)
    {
        this.syncCurrentPlayItem();
        this.netClientHandler.addToSendQueue(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
        playerIn.stopActiveHand();
    }

    public boolean gameIsSurvivalOrAdventure()
    {
        return this.currentGameType.isSurvivalOrAdventure();
    }

    /**
     * Checks if the player is not creative, used for checking if it should break a block instantly
     */
    public boolean isNotCreative()
    {
        return !this.currentGameType.isCreative();
    }

    /**
     * returns true if player is in creative mode
     */
    public boolean isInCreativeMode()
    {
        return this.currentGameType.isCreative();
    }

    /**
     * true for hitting entities far away.
     */
    public boolean extendedReach()
    {
        return this.currentGameType.isCreative();
    }

    /**
     * Checks if the player is riding a horse, used to chose the GUI to open
     */
    public boolean isRidingHorse()
    {
        return this.mc.thePlayer.isRiding() && this.mc.thePlayer.getRidingEntity() instanceof EntityHorse;
    }

    public boolean isSpectatorMode()
    {
        return this.currentGameType == WorldSettings.GameType.SPECTATOR;
    }

    public WorldSettings.GameType getCurrentGameType()
    {
        return this.currentGameType;
    }

    /**
     * Return isHittingBlock
     */
    public boolean getIsHittingBlock()
    {
        return this.isHittingBlock;
    }

    public void pickItem(int index)
    {
        this.netClientHandler.addToSendQueue(new CPacketCustomPayload("MC|PickItem", (new PacketBuffer(Unpooled.buffer())).writeVarIntToBuffer(index)));
    }
}