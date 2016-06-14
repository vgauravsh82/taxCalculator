package com.taxation.item;

import java.math.BigDecimal;
import com.taxation.service.Charges;

public class ItemReceipt
{
	private static final String ORDER_ITEM_RECEIPT = "%s %s :%s";

	private OrderItem orderItem;
	private BigDecimal salesTax;
	private BigDecimal totalPrice;

	public ItemReceipt(OrderItem orderItem, Charges charges)
	{
		this.orderItem = orderItem;
		this.calculateSalesTax(charges);
	}

	public BigDecimal getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public BigDecimal getSalesTax()
	{
		return salesTax;
	}

	public void setSalesTax(BigDecimal salesTax)
	{
		this.salesTax = salesTax;
	}

	public OrderItem getOrderItem()
	{
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem)
	{
		this.orderItem = orderItem;
	}

	public void calculateSalesTax(Charges charges)
	{
		this.salesTax = orderItem.totalSalesTax(charges.getDutyCharges(), charges.getSalesTax());
		this.totalPrice = this.orderItem.getItemPrice().add(this.salesTax);
	}

	public String itemReceipt()
	{
		return String.format(ORDER_ITEM_RECEIPT, this.getOrderItem().getQuantity(), this.getOrderItem().getItem().getName(),
				this.getTotalPrice());
	}
}
