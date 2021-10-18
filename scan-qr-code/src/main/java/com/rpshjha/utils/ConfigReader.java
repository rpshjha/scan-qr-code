/*

 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rpshjha.utils;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author rupeshkumar
 *
 */
public class ConfigReader {

	private static Properties prop;
	private static ConfigReader reader;
	private static HashMap<String, String> propertyMap = new HashMap<>();

	private Path sitProperties = Paths.get("src/main/resources/application.properties");

	private ConfigReader() {

		try (FileChannel nc = FileChannel.open(sitProperties, StandardOpenOption.READ)) {
			prop = new Properties();
			prop.load(Channels.newInputStream(nc));
		} catch (IOException ex) {
			Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
		}
		prop.stringPropertyNames().forEach(key -> propertyMap.put(key, prop.getProperty(key)));
	}

	public static String getProperty(String propertyName) {
		if (reader == null) {
			reader = new ConfigReader();
		}
		return propertyMap.get(propertyName);
	}
}
