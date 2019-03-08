package com.awais.machine.interfaces;

import java.util.Collection;

import com.awais.machine.exceptions.NotEnoughChangeException;
import com.awais.machine.models.Coin;
import com.awais.machine.utils.Stock;

public interface ChangeStrategy {

	public Collection<Coin> calculateChange(long moneyLeftToConvert, Stock<Coin> coinsAvailable) throws NotEnoughChangeException;

}
