package com.taxation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration
{
	private static Properties prop = new Properties();

	public static String getProperty(String key, String defaultVal)
	{
		if (prop.containsKey(key))
		{
			return prop.getProperty(key);
		}
		return defaultVal;
	}

	static
	{
		loadConfig();
	}

	public static void loadConfig()
	{

		String propFileName = "configuration.properties";

		InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null)
		{
			try
			{
				prop.load(inputStream);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("property file '" + propFileName + "' not found in the classpath");
		}
	}

}
