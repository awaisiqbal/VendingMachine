package com.awais.machine.interfaces;

import java.util.Collection;

import com.awais.machine.exceptions.InvalidProductException;
import com.awais.machine.exceptions.NotEnoughChangeException;
import com.awais.machine.exceptions.NotEnoughMoneyException;
import com.awais.machine.exceptions.SoldOutException;
import com.awais.machine.models.Coin;
import com.awais.machine.models.Product;
import com.awais.machine.utils.Pair;

/**
 * This interface contains all the public methods of the vending machine.
 * 
 * @author Awais Iqbal
 *
 */
public interface VendingMachine {

	/**
	 * Given a selected product returns the price of that product.
	 * 
	 * @param product Product to check
	 * @return price of the product in cents
	 * @throws InvalidProductException Thrown when the selected product is invalid
	 */
	public long getProductPrice(Product product) throws InvalidProductException;

	/**
	 * Add a coin in the vending machine.
	 * 
	 * @param coin Coin to be inserted in the machine
	 */
	public void addCoin(Coin coin);

	/**
	 * This method represents a selection of a product.
	 * 
	 * @param productSelected Product selected by the user
	 * @return The request Product and List of all the coins returned to the user
	 * @throws NotEnoughMoneyException  Thrown when the inserted money is less than
	 *                                  the price of the product
	 * @throws SoldOutException         Thrown when the selected product is sold out
	 * @throws NotEnoughChangeException Thrown when the machine don't have enough
	 *                                  change for the given operation
	 * @throws InvalidProductException 
	 */
	public Pair<Product, Collection<Coin>> selectProduct(Product productSelected)
			throws NotEnoughMoneyException, SoldOutException, NotEnoughChangeException, InvalidProductException;

	/**
	 * THis method reset the inserted coins value, and returns the inserted coins
	 * 
	 * @return List of all the coins returned
	 */
	public Collection<Coin> cancelRequest() throws NotEnoughChangeException;

	/**
	 * Operation used to refill all the beverages & coins for change
	 * 
	 * @param refilling Pair of products and coins to be refilled
	 */
	public void refill(Pair<Collection<Product>, Collection<Coin>> refilling);

	/**
	 * Check the current inserted coins
	 * 
	 * @return Value of the inserted coins in cents
	 */
	public long getCurrentMoney();

	/**
	 * Reset the vending machine
	 */
	public void reset();

}
