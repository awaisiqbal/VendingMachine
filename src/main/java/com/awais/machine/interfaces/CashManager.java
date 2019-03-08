package com.awais.machine.interfaces;

import java.util.Collection;

import com.awais.machine.exceptions.NotEnoughChangeException;
import com.awais.machine.models.Coin;

public interface CashManager {

	/**
	 * Add a coin the stock
	 * 
	 * @param coin Coin to be added
	 */
	public void add(Coin c);

	/**
	 * Given a collection of coins add them to the stock (refilling)
	 * 
	 * @param coinsToBeRefilled Coin to be added
	 */
	public void refill(Collection<Coin> coinsToBeRefilled);

	/**
	 * Remove a coin from the stock
	 * 
	 * @param coin Coin to be removed
	 */
	public void redraw(Coin coin);

	/**
	 * Remove a collection of coins from the inventory
	 * 
	 * @param coins Coins to be removed
	 */
	public void redraw(Collection<Coin> coins);

	/**
	 * Check if we have any existence of the given coin
	 * 
	 * @param coin Coin to be checked
	 * @return True if the given coin have any existence in the inventory
	 */
	public boolean containsMoreThenOne(Coin coin);

	/**
	 * Reset all the existences in the inventory
	 */
	public void reset();

	/**
	 * Get total value of the coins inserted
	 * 
	 * @return Value of all the coins
	 */
	public long getCurrentMoney();

	/**
	 * Calculate change
	 * 
	 * @param moneyLeftToConvert
	 * @return
	 * @throws NotEnoughChangeException
	 */
	public Collection<Coin> calculateChange(long moneyLeftToConvert) throws NotEnoughChangeException;

}
