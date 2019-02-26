package com.awais.machine.interfaces.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import com.awais.machine.interfaces.CashManager;
import com.awais.machine.models.Coin;
import com.awais.machine.utils.Stock;

public class CashManagerImpl implements CashManager {

	/**
	 * All the coins in the vending machine available to be used.
	 */
	private Stock<Coin> coinsInStock;

	public CashManagerImpl() {
		coinsInStock = new Stock<>();
		Arrays.stream(Coin.values()).forEach(c -> coinsInStock.initializeKey(c));
	}

	@Override
	public void add(Coin c) {
		if (c != null) {
			coinsInStock.add(c);
		}
	}

	@Override
	public void refill(Collection<Coin> coinsToBeRefilled) {
		coinsInStock.refill(coinsToBeRefilled);
	}

	@Override
	public void redraw(Coin c) {
		coinsInStock.removeItem(c);
	}

	@Override
	public boolean containsMoreThenOne(Coin c) {
		return coinsInStock.containsMoreThenOne(c);
	}

	@Override
	public void redraw(Collection<Coin> coins) {
		coinsInStock.removeItems(coins);
	}

	@Override
	public void reset() {
		coinsInStock.reset();
	}

	@Override
	public long getCurrentMoney() {
		Map<Coin, Integer> allTheStock = coinsInStock.getCurrentStockItems();
		long sum = 0;
		for (Map.Entry<Coin, Integer> entry : allTheStock.entrySet()) {
			sum += (entry.getKey().getValue() * entry.getValue());
		}
		return sum;
	}

}
