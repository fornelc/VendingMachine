package com.tescobank.vendingmachine.model;

import java.math.BigDecimal;

/**
 * Products A,B,C
 * 
 * @author Javier Fornells
 *
 */
public enum Item {

	A(new BigDecimal("0.60"), "A"), 
	B(new BigDecimal("1.00"), "B"), 
	C(new BigDecimal("1.70"), "C");

	Item(BigDecimal quantity, String item) {
		this.quantity = quantity;
		this.item = item;
	}

	private BigDecimal quantity;
	private String item;

	public BigDecimal getQuantity() {
		return quantity;
	}

	public String getItem() {
		return item;
	}

	
}