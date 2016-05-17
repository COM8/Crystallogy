package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.AnvilConverterException;
import net.minecraft.client.Minecraft;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.SaveFormatComparator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SideOnly(Side.CLIENT)
public class GuiListWorldSelection extends GuiListExtended
{
    private static final Logger LOGGER = LogManager.getLogger();
    private final GuiWorldSelection worldSelectionObj;
    private final List<GuiListWorldSelectionEntry> field_186799_w = Lists.<GuiListWorldSelectionEntry>newArrayList();
    /** Index to the currently selected world */
    private int selectedIdx = -1;

    public GuiListWorldSelection(GuiWorldSelection p_i46590_1_, Minecraft clientIn, int p_i46590_3_, int p_i46590_4_, int p_i46590_5_, int p_i46590_6_, int p_i46590_7_)
    {
        super(clientIn, p_i46590_3_, p_i46590_4_, p_i46590_5_, p_i46590_6_, p_i46590_7_);
        this.worldSelectionObj = p_i46590_1_;
        this.func_186795_e();
    }

    public void func_186795_e()
    {
        ISaveFormat isaveformat = this.mc.getSaveLoader();
        List<SaveFormatComparator> list;

        try
        {
            list = isaveformat.getSaveList();
        }
        catch (AnvilConverterException anvilconverterexception)
        {
            LOGGER.error((String)"Couldn\'t load level list", (Throwable)anvilconverterexception);
            this.mc.displayGuiScreen(new GuiErrorScreen("Unable to load worlds", anvilconverterexception.getMessage()));
            return;
        }

        Collections.sort(list);

        for (SaveFormatComparator saveformatcomparator : list)
        {
            this.field_186799_w.add(new GuiListWorldSelectionEntry(this, saveformatcomparator, this.mc.getSaveLoader()));
        }
    }

    /**
     * Gets the IGuiListEntry object for the given index
     */
    public GuiListWorldSelectionEntry getListEntry(int index)
    {
        return (GuiListWorldSelectionEntry)this.field_186799_w.get(index);
    }

    protected int getSize()
    {
        return this.field_186799_w.size();
    }

    protected int getScrollBarX()
    {
        return super.getScrollBarX() + 20;
    }

    /**
     * Gets the width of the list
     */
    public int getListWidth()
    {
        return super.getListWidth() + 50;
    }

    public void selectWorld(int idx)
    {
        this.selectedIdx = idx;
        this.worldSelectionObj.selectWorld(this.getSelectedWorld());
    }

    /**
     * Returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int slotIndex)
    {
        return slotIndex == this.selectedIdx;
    }

    public GuiListWorldSelectionEntry getSelectedWorld()
    {
        return this.selectedIdx >= 0 && this.selectedIdx < this.getSize() ? this.getListEntry(this.selectedIdx) : null;
    }

    public GuiWorldSelection getGuiWorldSelection()
    {
        return this.worldSelectionObj;
    }
}