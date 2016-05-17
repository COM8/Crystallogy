package net.minecraft.server.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JComponent;
import javax.swing.Timer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class StatsComponent extends JComponent
{
    private static final DecimalFormat FORMATTER = new DecimalFormat("########0.000");
    private int[] field_120038_b = new int[256];
    private int field_120039_c;
    private String[] field_120036_d = new String[11];
    private final MinecraftServer server;

    public StatsComponent(MinecraftServer serverIn)
    {
        this.server = serverIn;
        this.setPreferredSize(new Dimension(456, 246));
        this.setMinimumSize(new Dimension(456, 246));
        this.setMaximumSize(new Dimension(456, 246));
        (new Timer(500, new ActionListener()
        {
            public void actionPerformed(ActionEvent p_actionPerformed_1_)
            {
                StatsComponent.this.func_120034_a();
            }
        })).start();
        this.setBackground(Color.BLACK);
    }

    private void func_120034_a()
    {
        long i = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.gc();
        this.field_120036_d[0] = "Memory use: " + i / 1024L / 1024L + " mb (" + Runtime.getRuntime().freeMemory() * 100L / Runtime.getRuntime().maxMemory() + "% free)";
        this.field_120036_d[1] = "Avg tick: " + FORMATTER.format(this.mean(this.server.tickTimeArray) * 1.0E-6D) + " ms";
        this.repaint();
    }

    private double mean(long[] values)
    {
        long i = 0L;

        for (int j = 0; j < values.length; ++j)
        {
            i += values[j];
        }

        return (double)i / (double)values.length;
    }

    public void paint(Graphics p_paint_1_)
    {
        p_paint_1_.setColor(new Color(16777215));
        p_paint_1_.fillRect(0, 0, 456, 246);

        for (int i = 0; i < 256; ++i)
        {
            int j = this.field_120038_b[i + this.field_120039_c & 255];
            p_paint_1_.setColor(new Color(j + 28 << 16));
            p_paint_1_.fillRect(i, 100 - j, 1, j);
        }

        p_paint_1_.setColor(Color.BLACK);

        for (int k = 0; k < this.field_120036_d.length; ++k)
        {
            String s = this.field_120036_d[k];

            if (s != null)
            {
                p_paint_1_.drawString((String)s, 32, 116 + k * 16);
            }
        }
    }
}