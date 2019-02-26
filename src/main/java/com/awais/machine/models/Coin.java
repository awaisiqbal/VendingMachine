package com.awais.machine.models;

/**
 * This enum represents the coins allowed by this vending machine. the value of
 * the coin it should be represented in cents.
 * 
 * @author Awais Iqbal
 *
 */
public enum Coin {
	TWO_EURO(200), ONE_EURO(100), FIFTY_CENTS(50), TWENTY_CENTS(20), TEN_CENTS(10), FIVE_CENTS(5);

	private final long value;

	private Coin(long value) {
		this.value = value;
	}

	public long getValue() {
		return value;
	}

}
