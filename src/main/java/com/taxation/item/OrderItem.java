package com.taxation.item;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import com.taxation.config.ItemConfig;
import com.taxation.config.ItemConfigStore;

public class OrderItem
{
	private Item item;
	private Integer quantity;
	private BigDecimal itemPrice;

	public BigDecimal getItemPrice()
	{
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice)
	{
		this.itemPrice = itemPrice;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}

	public Item getItem()
	{
		return item;
	}

	public void setItem(Item item)
	{
		this.item = item;
	}

	public BigDecimal calculateImportDuty(BigDecimal dutyCharges)
	{
		if(item.getName().contains("imported")){
			return this.getItemPrice().multiply(dutyCharges);	
		}else{
			return BigDecimal.ZERO;
		}
		
	}

	public BigDecimal calculateSalesTax(BigDecimal salesTax)
	{
		if(itemTaxable(this.item.getName())){
			return this.getItemPrice().multiply(salesTax);	
		}else{
			return BigDecimal.ZERO;
		}
		
	}
	

	private boolean itemTaxable(String name)
	{
		return (!ItemConfigStore.getCategory(name).equals("book") &&
				!ItemConfigStore.getCategory(name).equals("medicine")&& 
						!ItemConfigStore.getCategory(name).equals("food")); 
	}

	public BigDecimal cost(BigDecimal dutyCharges, BigDecimal salesTax)
	{
		BigDecimal totalSalesTax = totalSalesTax(dutyCharges, salesTax);
		return totalSalesTax
				.add(this.getItemPrice())
				.multiply(BigDecimal.valueOf(quantity));
	}

	public BigDecimal totalSalesTax(BigDecimal dutyCharges, BigDecimal salesTax)
	{
		MathContext mc = new MathContext(3, RoundingMode.HALF_UP);
		BigDecimal val = this.calculateImportDuty(dutyCharges).add(this.calculateSalesTax(salesTax)).round(mc);
		BigDecimal totalSalesTax = rounding(val);
		return totalSalesTax;
	}

	private BigDecimal rounding(BigDecimal val)
	{
		return val.multiply(BigDecimal.valueOf(20)).setScale(0, RoundingMode.UP).divide(BigDecimal.valueOf(20));
	}

}
