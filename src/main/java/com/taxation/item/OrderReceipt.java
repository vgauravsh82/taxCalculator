package com.taxation.item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.taxation.service.Charges;

public class OrderReceipt
{
	private static final String STRING_NEWLINE = "\n";
	private static final String TOTAL = "Total: ";
	private static final String SALES_TAXES = "Sales Taxes: ";
	
	private List<ItemReceipt> itemReceipts = new ArrayList<ItemReceipt>();
	private BigDecimal totalSalesTax = BigDecimal.ZERO.setScale(2);
	private BigDecimal totalCost = BigDecimal.ZERO.setScale(2);

	public void addOrderItem(List<OrderItem> orderItems, Charges charges)
	{
		orderItems.stream().forEach(orderItem -> itemReceipts.add(new ItemReceipt(orderItem,charges)));
	}
	
	public String orderReceiptSummary(){
		StringBuilder receiptSummary = new StringBuilder();
		
		for (ItemReceipt receipt : itemReceipts)
		{
			receiptSummary.append(receipt.itemReceipt()+STRING_NEWLINE);
			totalSalesTax = totalSalesTax.add(receipt.getSalesTax());
			totalCost = totalCost.add(receipt.getTotalPrice());	
		}
		receiptSummary.append(SALES_TAXES + totalSalesTax.doubleValue()+STRING_NEWLINE);
		receiptSummary.append(TOTAL + totalCost.doubleValue()+STRING_NEWLINE);
		return receiptSummary.toString();
	}
}
