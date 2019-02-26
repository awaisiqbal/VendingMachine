package com.awais.machine.models;

/**
 * This Enum representing the product that can be bought in this vending machine
 * 
 * @author Awais Iqbal
 *
 */
public enum Product {
	COKE("Coke", 150), SPRITE("Sprite", 140), WATER("Water", 90);

	private final String name;
	private final long price;

	private Product(String name, long price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public long getPrice() {
		return price;
	}

}
