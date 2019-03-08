package com.awais.machine.interfaces.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.awais.machine.exceptions.NotEnoughChangeException;
import com.awais.machine.interfaces.ChangeStrategy;
import com.awais.machine.models.Coin;
import com.awais.machine.utils.Stock;

public class ClassicStrategy implements ChangeStrategy {

	private Stock<Coin> coinsAvailable;

	@Override
	public Collection<Coin> calculateChange(long moneyLeftToConvert, Stock<Coin> coinsAvailable)
			throws NotEnoughChangeException {
		Collection<Coin> coinsToReturn = new ArrayList<>();
		this.coinsAvailable = coinsAvailable;
		// while the money left is not 0, try to get a coin from the biggest to the
		// smallest coin
		while (moneyLeftToConvert > 0) {
			if (checkCoinAvailabity(moneyLeftToConvert, Coin.TWO_EURO)) {
				moneyLeftToConvert = collectReturnAndRemoveCoin(coinsToReturn, moneyLeftToConvert, Coin.TWO_EURO);
			} else if (checkCoinAvailabity(moneyLeftToConvert, Coin.ONE_EURO)) {
				moneyLeftToConvert = collectReturnAndRemoveCoin(coinsToReturn, moneyLeftToConvert, Coin.ONE_EURO);
			} else if (checkCoinAvailabity(moneyLeftToConvert, Coin.FIFTY_CENTS)) {
				moneyLeftToConvert = collectReturnAndRemoveCoin(coinsToReturn, moneyLeftToConvert, Coin.FIFTY_CENTS);
			} else if (checkCoinAvailabity(moneyLeftToConvert, Coin.TWENTY_CENTS)) {
				moneyLeftToConvert = collectReturnAndRemoveCoin(coinsToReturn, moneyLeftToConvert, Coin.TWENTY_CENTS);
			} else if (checkCoinAvailabity(moneyLeftToConvert, Coin.TEN_CENTS)) {
				moneyLeftToConvert = collectReturnAndRemoveCoin(coinsToReturn, moneyLeftToConvert, Coin.TEN_CENTS);
			} else if (checkCoinAvailabity(moneyLeftToConvert, Coin.FIVE_CENTS)) {
				moneyLeftToConvert = collectReturnAndRemoveCoin(coinsToReturn, moneyLeftToConvert, Coin.FIVE_CENTS);
			} else {
				throw new NotEnoughChangeException("Not enough change.");
			}
		}
		return coinsToReturn;
	}

	/**
	 * Add the given coin in to the coins to be returned, reduce money left with the
	 * given coin value and finally remove that coin from the stock
	 * 
	 * @param coinsToReturn
	 * @param moneyLeftToConvert
	 * @param coin
	 * @return Money
	 */
	private long collectReturnAndRemoveCoin(Collection<Coin> coinsToReturn, final long moneyLeftToConvert,
			final Coin coin) {
		coinsToReturn.add(coin);
		coinsAvailable.removeItem(coin);
		return moneyLeftToConvert - coin.getValue();
	}

	/**
	 * This method checks if the cash module contains that coin to be redraw
	 * 
	 * @param moneyLeftToConvert Money left to be returned
	 * @param coin               Coin to check
	 * @return True is the module has the coin available
	 */
	private boolean checkCoinAvailabity(final long moneyLeftToConvert, final Coin coin) {
		return moneyLeftToConvert >= coin.getValue() && coinsAvailable.containsMoreThenOne(coin);
	}

}
