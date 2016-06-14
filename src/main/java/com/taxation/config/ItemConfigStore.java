package com.taxation.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ItemConfigStore
{
	private static Map<String, String> itemConfig = new HashMap<String, String>();

	static
	{
		if (itemConfig.size() == 0)
		{
			loadConfig();
		}
	}

	private static void loadConfig()
	{
		BufferedReader br = new BufferedReader(
				new InputStreamReader(ItemConfigStore.class.getResourceAsStream("/itemConfig.csv")));
		try
		{
			String line;
			while ((line = br.readLine()) != null)
			{
				String[] carrot = line.split("\\|");
				itemConfig.put(carrot[0], carrot[1]);
			}
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}

	public static String getCategory(String itemName)
	{
		return itemConfig.get(itemName);
	}
}
