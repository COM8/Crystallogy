package de.comeight.crystallogy.util;

import org.apache.logging.log4j.LogManager;

import de.comeight.crystallogy.CrystallogyBase;

public final class Log {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(CrystallogyBase.MODNAME);
	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	/**
	 * Adds a message to the log as a "warn" message
	 * 
	 * @param msg the message that should get added to the Minecraft Log
	 */
	public static void warn(String msg) {
		LOGGER.warn(msg);
	}

	/**
	 * Adds a message to the log as an "error" message
	 * 
	 * @param msg the message that should get added to the Minecraft Log
	 */
	public static void error(String msg) {
		LOGGER.error(msg);
	}

	/**
	 * Adds a message to the log as an "info" message
	 * 
	 * @param msg the message that should get added to the Minecraft Log
	 */
	public static void info(String msg) {
		LOGGER.info(msg);
	}

	/**
	 * Adds a message to the log as a "debug" message
	 * 
	 * @param msg the message that should get added to the Minecraft Log
	 */
	public static void debug(String msg) {
		LOGGER.debug(msg);
	}
	
}
