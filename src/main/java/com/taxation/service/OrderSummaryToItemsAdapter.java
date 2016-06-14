package com.taxation.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.taxation.item.Item;
import com.taxation.item.OrderItem;

public class OrderSummaryToItemsAdapter
{
	private static final Pattern orderSummaryPattern = Pattern.compile("(\\d+)\\s(.+)\\sat\\s(.+)");

	public List<OrderItem> adapt(String orderSummary)
	{
		Matcher matcher = orderSummaryPattern.matcher(orderSummary);

		List<OrderItem> items = new ArrayList<OrderItem>();
		while (matcher.find())
		{
			OrderItem orderItem = new OrderItem();
			orderItem.setQuantity(Integer.valueOf(matcher.group(1)));
			Item item = new Item();
			item.setName(matcher.group(2));
			orderItem.setItem(item);
			orderItem.setItemPrice(new BigDecimal(matcher.group(3)));
			items.add(orderItem);
		}
		return items;
	}
}
