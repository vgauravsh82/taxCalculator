package com.taxation.service;

import java.math.BigDecimal;

public class Charges
{
	private BigDecimal dutyCharges = BigDecimal.valueOf(0.05);
	private BigDecimal salesTax = BigDecimal.valueOf(0.1);
	
	public Charges(BigDecimal dutyCharges, BigDecimal salesTax)
	{
		this.dutyCharges = dutyCharges;
		this.salesTax = salesTax;
	}
	
	public BigDecimal getDutyCharges()
	{
		return dutyCharges;
	}
	public BigDecimal getSalesTax()
	{
		return salesTax;
	}
	
}
