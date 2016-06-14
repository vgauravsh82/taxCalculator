package com.taxation.calculate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import com.taxation.item.Item;
import com.taxation.item.OrderItem;
import com.taxation.service.OrderReceiptService;
import com.taxation.service.OrderSummaryToItemsAdapter;

public class TaxCalculatorTest
{
	@Test
	public void calculate(){
		String input = "1 book at 12.49\n"
				+ "1 music CD at 14.99\n"
				+ "1 chocolate bar at 0.85\n";
		
		String input1 = "1 imported box of chocolates at 10.00\n"
				+ "1 imported bottle of perfume at 47.50\n";

		String input2 = "1 imported bottle of perfume at 27.99\n"
				+ "1 bottle of perfume at 18.99\n"
				+ "1 packet of headache pills at 9.75\n"
				+ "1 box of imported chocolates at 11.25\n";
		
		
		Pattern p = Pattern.compile("(\\d+)\\s(.+)\\sat\\s(.+)");
		Matcher matcher = p.matcher(input1);

		List<OrderItem> items = new ArrayList<OrderItem>();
		while(matcher.find()){
			OrderItem orderItem = new OrderItem();
			orderItem.setQuantity(Integer.valueOf(matcher.group(1)));
			Item item = new Item();
			item.setName(matcher.group(2));
			orderItem.setItem(item);
			orderItem.setItemPrice(new BigDecimal(matcher.group(3)));
			items.add(orderItem);
		}
		OrderSummaryToItemsAdapter orderSummaryToItemsAdapter = new OrderSummaryToItemsAdapter();
		OrderReceiptService receiptService = new OrderReceiptService(orderSummaryToItemsAdapter);
		System.out.println(receiptService.generateReceipt(items));
		
	}
	
	

}
