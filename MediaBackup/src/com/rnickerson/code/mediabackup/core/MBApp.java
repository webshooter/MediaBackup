package com.rnickerson.code.mediabackup.core;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class MBApp {
	
	public static Logger logger = Logger.getLogger(MBApp.class.getName());
	public static String FILESEP = System.getProperty("file.separator");
	
	private static String propsFile;
	private static Properties appProps;
	private static String sourcePath;
	private static String destPath;
	private static String logFile;
	private static boolean backupEmptyFolders;
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Process CLAs and load properties file
		propsFile = System.getProperty("user.dir") + FILESEP + "mb.properties";
		if (args.length > 0) {
			propsFile = processArgs(args);
		}
		try {
			if (true) { throw new Exception("TEST"); }
			appProps = new Properties();
			appProps.load(new FileInputStream(propsFile));
		} catch (Exception e) {
			if (propsFile == null) {
				handleException(e, "Configuration properties file is NULL", true);
			} else {
				handleException(e, "Error loading configuration properties file: " + propsFile, true);
			}
		}
		
		// Load property values from properties object
		try {
			sourcePath = appProps.getProperty("sourcepath");
			destPath = appProps.getProperty("destpath");
			logFile = appProps.getProperty("logfile", FILESEP + "mb.log");
			backupEmptyFolders = (appProps.getProperty("backupemptyfolders", "false").equalsIgnoreCase("true"))?true:false;
		} catch (Exception e) {
			handleException(e, "", true);
		}
		
		// Setup log4j logger
		Properties log4jProperties = new Properties();
		try {
			log4jProperties.load(MBApp.class.getClass().getResourceAsStream("/log4j.properties"));
			log4jProperties.setProperty("log4j.appender.logAppender.file", logFile);
		} catch (Exception e) {
			handleException(e, "Error loading log4j properties", true);
		}
		PropertyConfigurator.configure(log4jProperties);
		
		
		
	}
	
	public static void handleException(Throwable e, String msg, boolean exit) {
		String excptionMsg = "-- EXCEPTION OCCURED --";
		if (exit) {
			logger.fatal(excptionMsg);
			logger.fatal(msg, e);
			logger.fatal("Exiting application...");
			System.exit(0);
		} else {
			logger.error(excptionMsg);
			logger.error(msg, e);
		}
	}
	
	public static void out(String msg) {
		System.out.println(msg);
	}
	
	private static String processArgs(String[] clas) {
		if (clas.length == 2) {
			if (clas[0].equalsIgnoreCase("-p")) {
				return clas[1];
			}
		}
		return null;
	}

}
