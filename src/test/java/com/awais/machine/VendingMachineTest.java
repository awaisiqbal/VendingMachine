package com.awais.machine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.awais.machine.exceptions.InvalidProductException;
import com.awais.machine.exceptions.NotEnoughChangeException;
import com.awais.machine.exceptions.NotEnoughMoneyException;
import com.awais.machine.exceptions.SoldOutException;
import com.awais.machine.interfaces.VendingMachine;
import com.awais.machine.models.Coin;
import com.awais.machine.models.Product;
import com.awais.machine.utils.Pair;

public class VendingMachineTest {

	private VendingMachine machine;

	@Before
	public void setUp() {
		machine = Factory.createVendingMachine();
	}

	// Tests to check add coins
	@Test
	public void insertFiftyCentsCoin() {
		machine.addCoin(Coin.FIFTY_CENTS);
		assertEquals(Coin.FIFTY_CENTS.getValue(), machine.getCurrentMoney(), 0);
	}

	@Test
	public void insertFiveCentsCoin() {
		machine.addCoin(Coin.FIVE_CENTS);
		assertEquals(Coin.FIVE_CENTS.getValue(), machine.getCurrentMoney(), 0);
	}

	@Test
	public void insertOneEuroCoin() {
		machine.addCoin(Coin.ONE_EURO);
		assertEquals(Coin.ONE_EURO.getValue(), machine.getCurrentMoney(), 0);
	}

	@Test
	public void insertTenCentsCoin() {
		machine.addCoin(Coin.TEN_CENTS);
		assertEquals(Coin.TEN_CENTS.getValue(), machine.getCurrentMoney(), 0);
	}

	@Test
	public void insertTwentyCentsCoin() {
		machine.addCoin(Coin.TWENTY_CENTS);
		assertEquals(Coin.TWENTY_CENTS.getValue(), machine.getCurrentMoney(), 0);
	}

	@Test
	public void insertTwoEuroCoin() {
		machine.addCoin(Coin.TWO_EURO);
		assertEquals(Coin.TWO_EURO.getValue(), machine.getCurrentMoney(), 0);
	}

	/**
	 * Try to buy a product without enough change, but with enough money inserted
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws SoldOutException
	 * @throws NotEnoughChangeException
	 * @throws InvalidProductException
	 */
	@Test(expected = NotEnoughChangeException.class)
	public void checkChange()
			throws NotEnoughMoneyException, SoldOutException, NotEnoughChangeException, InvalidProductException {
		List<Product> listProduct = new ArrayList<>();
		listProduct.add(Product.WATER);
		List<Coin> listCoin = new ArrayList<>();
		listCoin.add(Coin.TWENTY_CENTS);
		machine.refill(new Pair<Collection<Product>, Collection<Coin>>(listProduct, listCoin));

		machine.addCoin(Coin.TWO_EURO);
		machine.selectProduct(Product.WATER);
	}

	/**
	 * Bought a product with exact price
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws SoldOutException
	 * @throws NotEnoughChangeException
	 * @throws InvalidProductException
	 */
	@Test
	public void checkExactPrice()
			throws NotEnoughMoneyException, SoldOutException, NotEnoughChangeException, InvalidProductException {
		List<Product> listProduct = new ArrayList<>();
		listProduct.add(Product.WATER);
		List<Coin> listCoin = new ArrayList<>();
		machine.refill(new Pair<Collection<Product>, Collection<Coin>>(listProduct, listCoin));

		machine.addCoin(Coin.FIFTY_CENTS);
		machine.addCoin(Coin.TWENTY_CENTS);
		machine.addCoin(Coin.TWENTY_CENTS);

		Pair<Product, Collection<Coin>> ret = machine.selectProduct(Product.WATER);
		assertEquals(Product.WATER, ret.getFirstItem());
		long totalValue = ret.getSecondItem().stream().mapToLong(Coin::getValue).sum();
		assertEquals(0, totalValue);
	}

	/**
	 * Try to bought a product with enough money but without enough change
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws SoldOutException
	 * @throws NotEnoughChangeException
	 * @throws InvalidProductException
	 */
	@Test(expected = NotEnoughChangeException.class)
	public void checkNotEnoghChange()
			throws NotEnoughMoneyException, SoldOutException, NotEnoughChangeException, InvalidProductException {
		Collection<Product> listProduct = new ArrayList<>();
		listProduct.add(Product.WATER);
		Collection<Coin> listCoin = new ArrayList<>();
		machine.refill(new Pair<Collection<Product>, Collection<Coin>>(listProduct, listCoin));

		machine.addCoin(Coin.FIFTY_CENTS);
		machine.addCoin(Coin.TWENTY_CENTS);
		machine.addCoin(Coin.FIFTY_CENTS);
		machine.selectProduct(Product.WATER);
	}

	/**
	 * Buy a product without any existance
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws SoldOutException
	 * @throws NotEnoughChangeException
	 * @throws InvalidProductException
	 */
	@Test(expected = SoldOutException.class)
	public void checkBuySoldout()
			throws NotEnoughMoneyException, SoldOutException, NotEnoughChangeException, InvalidProductException {
		List<Product> listProduct = new ArrayList<>();
		List<Coin> listCoin = new ArrayList<>();
		machine.refill(new Pair<Collection<Product>, Collection<Coin>>(listProduct, listCoin));

		machine.addCoin(Coin.FIFTY_CENTS);
		machine.addCoin(Coin.TWENTY_CENTS);
		machine.addCoin(Coin.FIFTY_CENTS);
		machine.selectProduct(Product.WATER);
	}

	/**
	 * Check the functionality of the cancel button
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws SoldOutException
	 * @throws NotEnoughChangeException
	 */
	@Test
	public void checkCorrectCancel() throws NotEnoughChangeException {
		machine.addCoin(Coin.FIFTY_CENTS);
		machine.addCoin(Coin.TWENTY_CENTS);
		machine.addCoin(Coin.FIFTY_CENTS);
		Collection<Coin> l = machine.cancelRequest();
		long sum = l.stream().mapToLong(Coin::getValue).sum();
		assertEquals(120, sum, 0);
	}

	/**
	 * Check the reset button
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws SoldOutException
	 * @throws NotEnoughChangeException
	 */
	@Test
	public void checkReset() {

		machine.addCoin(Coin.FIFTY_CENTS);
		machine.addCoin(Coin.TWENTY_CENTS);
		machine.addCoin(Coin.FIFTY_CENTS);

		assertEquals(120, machine.getCurrentMoney(), 0);

		machine.reset();
		assertEquals(0, machine.getCurrentMoney(), 0);
	}

	/**
	 * Check the price of a given product
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws SoldOutException
	 * @throws NotEnoughChangeException
	 * @throws InvalidProductException
	 */
	@Test
	public void checkProductPrice() throws InvalidProductException {

		machine.addCoin(Coin.FIFTY_CENTS);
		machine.addCoin(Coin.TWENTY_CENTS);
		machine.addCoin(Coin.FIFTY_CENTS);

		assertEquals(Product.COKE.getPrice(), machine.getProductPrice(Product.COKE), 0);
	}

	/**
	 * Try to buy a product without enough money
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws SoldOutException
	 * @throws NotEnoughChangeException
	 * @throws InvalidProductException
	 */
	@Test(expected = NotEnoughMoneyException.class)
	public void checkNotEnoughMoney()
			throws NotEnoughMoneyException, SoldOutException, NotEnoughChangeException, InvalidProductException {
		List<Product> listProduct = new ArrayList<>();
		listProduct.add(Product.WATER);
		List<Coin> listCoin = new ArrayList<>();
		machine.refill(new Pair<Collection<Product>, Collection<Coin>>(listProduct, listCoin));

		machine.addCoin(Coin.FIFTY_CENTS);
		machine.selectProduct(Product.WATER);
	}

	/**
	 * This method test all the possible change given
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws SoldOutException
	 * @throws NotEnoughChangeException
	 * @throws InvalidProductException
	 */
	@Test
	public void testAllThePosibleChangeReturn()
			throws NotEnoughMoneyException, SoldOutException, NotEnoughChangeException, InvalidProductException {
		List<Product> listProduct = new ArrayList<>();
		listProduct.add(Product.COKE);
		List<Coin> listCoin = new ArrayList<>();
		listCoin.add(Coin.TWO_EURO);
		machine.refill(new Pair<Collection<Product>, Collection<Coin>>(listProduct, listCoin));

		machine.addCoin(Coin.TWO_EURO);
		machine.addCoin(Coin.ONE_EURO);
		machine.addCoin(Coin.FIFTY_CENTS);
		Pair<Product, Collection<Coin>> ret = machine.selectProduct(Product.COKE);
		assertEquals(Product.COKE, ret.getFirstItem());
		assertTrue(ret.getSecondItem().contains(Coin.TWO_EURO));

	}

	/**
	 * This method test one euro change returned
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws SoldOutException
	 * @throws NotEnoughChangeException
	 * @throws InvalidProductException
	 */
	@Test
	public void testOneEuroReturn()
			throws NotEnoughMoneyException, SoldOutException, NotEnoughChangeException, InvalidProductException {
		List<Product> listProduct = new ArrayList<>();
		listProduct.add(Product.SPRITE);
		List<Coin> listCoin = new ArrayList<>();
		listCoin.add(Coin.TWO_EURO);
		listCoin.add(Coin.ONE_EURO);
		machine.refill(new Pair<Collection<Product>, Collection<Coin>>(listProduct, listCoin));

		machine.addCoin(Coin.ONE_EURO);
		machine.addCoin(Coin.ONE_EURO);
		machine.addCoin(Coin.TWENTY_CENTS);
		machine.addCoin(Coin.TWENTY_CENTS);
		Pair<Product, Collection<Coin>> ret = machine.selectProduct(Product.SPRITE);
		assertEquals(Product.SPRITE, ret.getFirstItem());
		assertTrue(ret.getSecondItem().contains(Coin.ONE_EURO));
	}

	/**
	 * This method test ten cents change return
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws SoldOutException
	 * @throws NotEnoughChangeException
	 * @throws InvalidProductException
	 */
	@Test
	public void testTenCentsReturn()
			throws NotEnoughMoneyException, SoldOutException, NotEnoughChangeException, InvalidProductException {
		Collection<Product> listProduct = new ArrayList<>();
		listProduct.add(Product.COKE);
		Collection<Coin> listCoin = new ArrayList<>();
		machine.refill(new Pair<Collection<Product>, Collection<Coin>>(listProduct, listCoin));

		machine.addCoin(Coin.ONE_EURO);
		machine.addCoin(Coin.FIFTY_CENTS);
		machine.addCoin(Coin.TEN_CENTS);
		Pair<Product, Collection<Coin>> ret = machine.selectProduct(Product.COKE);
		assertEquals(Product.COKE, ret.getFirstItem());
		assertTrue(ret.getSecondItem().contains(Coin.TEN_CENTS));
	}

	/**
	 * This method test five cents change return
	 * 
	 * @throws NotEnoughMoneyException
	 * @throws SoldOutException
	 * @throws NotEnoughChangeException
	 * @throws InvalidProductException
	 */
	@Test
	public void testFiveCentsReturn()
			throws NotEnoughMoneyException, SoldOutException, NotEnoughChangeException, InvalidProductException {
		Collection<Product> listProduct = new ArrayList<>();
		listProduct.add(Product.COKE);
		Collection<Coin> listCoin = new ArrayList<>();
		machine.refill(new Pair<Collection<Product>, Collection<Coin>>(listProduct, listCoin));

		machine.addCoin(Coin.ONE_EURO);
		machine.addCoin(Coin.FIFTY_CENTS);
		machine.addCoin(Coin.FIVE_CENTS);
		Pair<Product, Collection<Coin>> ret = machine.selectProduct(Product.COKE);
		assertEquals(Product.COKE, ret.getFirstItem());
		assertTrue(ret.getSecondItem().contains(Coin.FIVE_CENTS));
	}

	@Test(expected = InvalidProductException.class)
	public void testNullSelectedProduct()
			throws NotEnoughMoneyException, SoldOutException, NotEnoughChangeException, InvalidProductException {
		machine.selectProduct(null);
	}

	@Test(expected = InvalidProductException.class)
	public void testNullGetProductPrice() throws InvalidProductException {
		machine.getProductPrice(null);
	}

}
