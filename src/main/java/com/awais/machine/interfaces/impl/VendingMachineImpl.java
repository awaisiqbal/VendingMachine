package com.awais.machine.interfaces.impl;

import java.util.Arrays;
import java.util.Collection;

import com.awais.machine.exceptions.InvalidProductException;
import com.awais.machine.exceptions.NotEnoughChangeException;
import com.awais.machine.exceptions.NotEnoughMoneyException;
import com.awais.machine.exceptions.SoldOutException;
import com.awais.machine.interfaces.CashManager;
import com.awais.machine.interfaces.VendingMachine;
import com.awais.machine.models.Coin;
import com.awais.machine.models.Product;
import com.awais.machine.utils.Pair;
import com.awais.machine.utils.Stock;

/**
 * Implementation of the vending machine.
 * 
 * @author Awais Iqbal
 *
 */
public class VendingMachineImpl implements VendingMachine {

	/**
	 * Module used to manage the money
	 */
	private CashManager cashModule;

	/**
	 * Stock of all the products available
	 */
	private Stock<Product> productsInStock;

	/**
	 * Currently selected product
	 */
	private Product selectedProduct;

	/**
	 * Value of the currently provided coins
	 */
	private long insertedCoinsValue;

	/**
	 * Default construct which initialize all the possibles values of the enums
	 * {@link Coin} and {@link Product}
	 */
	public VendingMachineImpl(CashManager cashModule) {

		this.cashModule = cashModule;

		productsInStock = new Stock<>();
		Arrays.stream(Product.values()).forEach(c -> productsInStock.initializeKey(c));
	}

	@Override
	public void addCoin(final Coin coin) {
		if (coin != null) {
			insertedCoinsValue += coin.getValue();
			cashModule.add(coin);
		}
	}

	@Override
	public long getCurrentMoney() {
		return insertedCoinsValue;
	}

	@Override
	public long getProductPrice(final Product product) throws InvalidProductException {
		if (product == null) {
			throw new InvalidProductException("The selected product is invalid");
		}
		return product.getPrice();
	}

	@Override
	public void refill(final Pair<Collection<Product>, Collection<Coin>> refilling) {
		// Refill products
		refilling.getFirstItem().stream().forEach(p -> productsInStock.add(p));

		// Refill Coins
		refilling.getSecondItem().stream().forEach(c -> cashModule.add(c));

	}

	@Override
	public Pair<Product, Collection<Coin>> selectProduct(final Product selectedProduct)
			throws NotEnoughMoneyException, SoldOutException, NotEnoughChangeException, InvalidProductException {
		if (selectedProduct == null) {
			throw new InvalidProductException("The selected product is invalid");
		}
		this.selectedProduct = selectedProduct;

		// Check the availability of the requested product

		checkProductAvailable();

		// Check enough change
		checkEnoughMoney();

		// Calculate the change
		Collection<Coin> coinsToReturn = calculateChange(insertedCoinsValue - selectedProduct.getPrice());

		// Remove that item from the stock
		productsInStock.removeItem(this.selectedProduct);

		this.selectedProduct = null;
		// Remove all the coins of the change from the stock
		coinsToReturn.stream().forEach(c -> cashModule.redraw(c));

		return new Pair<>(selectedProduct, coinsToReturn);
	}

	@Override
	public void reset() {
		cashModule.reset();
		this.productsInStock = new Stock<>();
		this.insertedCoinsValue = 0;
	}

	@Override
	public Collection<Coin> cancelRequest() throws NotEnoughChangeException {
		Collection<Coin> coinsToReturn = calculateChange(insertedCoinsValue);
		cashModule.redraw(coinsToReturn);
		return coinsToReturn;
	}

	/**
	 * Check if the selected product is available, otherwise throw
	 * {@link SoldOutException}
	 * 
	 * @throws SoldOutException When the machine don't have any existence
	 */
	private void checkProductAvailable() throws SoldOutException {
		if (!productsInStock.containsMoreThenOne(selectedProduct)) {
			throw new SoldOutException("Sorry, product already sold out");
		}
	}

	/**
	 * Given the value to return, returns the list of coins to return to the machine
	 * user
	 * 
	 * @param changeToReturn Money to be converted in coins
	 * @return List of the coins representing the requested value, otherwise return
	 *         {@link NotEnoughChangeException}
	 * @throws NotEnoughChangeException When the stock don't have enough money to
	 *                                  convert all the value
	 */
	private Collection<Coin> calculateChange(final long changeToReturn) throws NotEnoughChangeException {
		long moneyLeftToConvert = changeToReturn;
		Collection<Coin> coinToReturn = cashModule.calculateChange(moneyLeftToConvert);
		cashModule.redraw(coinToReturn);
		return coinToReturn;
	}

	/**
	 * Check if the inserted money is enough for the selected product
	 * 
	 * @throws NotEnoughMoneyException
	 */
	private void checkEnoughMoney() throws NotEnoughMoneyException {
		if (selectedProduct.getPrice() > insertedCoinsValue) {
			throw new NotEnoughMoneyException(selectedProduct.getName() + " price is " + selectedProduct.getPrice()
					+ " And inserted coins are " + insertedCoinsValue);
		}
	}

}
