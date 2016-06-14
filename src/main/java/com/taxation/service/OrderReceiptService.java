package com.taxation.service;

import java.math.BigDecimal;
import java.util.List;
import com.taxation.config.Configuration;
import com.taxation.item.OrderItem;
import com.taxation.item.OrderReceipt;

/**
 * 
 * Order Receipt service will generate receipt from given order items.
 * 
 * @author GauravV
 *
 */
public class OrderReceiptService
{

	private static final String _0_1 = "0.1";
	private static final String _0_05 = "0.05";
	private static final String KEY_SALES_TAX = "salesTax";
	private static final String KEY_IMPORT_DUTY = "importDuty";
	private OrderSummaryToItemsAdapter adapter;

	public OrderReceiptService(OrderSummaryToItemsAdapter adapter)
	{
		this.adapter = adapter;
	}

	public String generateReceipt(String orderSummmary)
	{
		List<OrderItem> orderItems = adapter.adapt(orderSummmary);
		return generateReceipt(orderItems);
	}

	public String generateReceipt(List<OrderItem> orderItems)
	{
		OrderReceipt orderReceipt = new OrderReceipt();
		orderReceipt.addOrderItem(orderItems, getCharges());

		StringBuilder receiptSummary = new StringBuilder();
		receiptSummary.append(orderReceipt.orderReceiptSummary());
		return receiptSummary.toString();
	}

	private Charges getCharges()
	{
		String importDuty = Configuration.getProperty(KEY_IMPORT_DUTY, _0_05);
		String salesCharge = Configuration.getProperty(KEY_SALES_TAX, _0_1);

		BigDecimal dutyCharges = new BigDecimal(importDuty);
		BigDecimal salesTax = new BigDecimal(salesCharge);
		Charges charges = new Charges(dutyCharges, salesTax);
		return charges;
	}

}