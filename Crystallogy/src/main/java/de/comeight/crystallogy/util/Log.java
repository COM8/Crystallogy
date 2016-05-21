package de.comeight.crystallogy.util;

import org.apache.logging.log4j.LogManager;

import de.comeight.crystallogy.CrystallogyBase;

public final class Log {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(CrystallogyBase.MODID);
	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void warn(String msg) {
		LOGGER.warn(msg);
	}

	public static void error(String msg) {
		LOGGER.error(msg);
	}

	public static void info(String msg) {
		LOGGER.info(msg);
	}

	public static void debug(String msg) {
		LOGGER.debug(msg);
	}
	
}
