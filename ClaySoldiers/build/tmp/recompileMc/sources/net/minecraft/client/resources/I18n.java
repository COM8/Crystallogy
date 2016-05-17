package net.minecraft.client.resources;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class I18n
{
    private static Locale i18nLocale;

    static void setLocale(Locale i18nLocaleIn)
    {
        i18nLocale = i18nLocaleIn;
    }

    /**
     * Translates the given string and then formats it. Equivilant to String.format(translate(key), parameters).
     *  
     * @param translateKey The translation key
     * @param parameters Varargs list of parameters for use when formating the string.
     */
    public static String format(String translateKey, Object... parameters)
    {
        return i18nLocale.formatMessage(translateKey, parameters);
    }

    public static boolean hasKey(String p_188566_0_)
    {
        return i18nLocale.hasKey(p_188566_0_);
    }
}