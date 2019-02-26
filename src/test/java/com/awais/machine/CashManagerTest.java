package com.awais.machine;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.awais.machine.interfaces.CashManager;
import com.awais.machine.models.Coin;

public class CashManagerTest {

	private CashManager cashManager;

	@Before
	public void setUp() {
		cashManager = Factory.createCashManager();
	}

	// Tests to check add coins
	@Test
	public void addNullCoin() {
		cashManager.add(null);
		assertEquals(0, cashManager.getCurrentMoney(), 0);
	}

	@Test
	public void insertFiftyCentsCoin() {
		cashManager.add(Coin.FIFTY_CENTS);
		assertEquals(Coin.FIFTY_CENTS.getValue(), cashManager.getCurrentMoney(), 0);
	}

	@Test
	public void insertFiveCentsCoin() {
		cashManager.add(Coin.FIVE_CENTS);
		assertEquals(Coin.FIVE_CENTS.getValue(), cashManager.getCurrentMoney(), 0);
	}

	@Test
	public void insertOneEuroCoin() {
		cashManager.add(Coin.ONE_EURO);
		assertEquals(Coin.ONE_EURO.getValue(), cashManager.getCurrentMoney(), 0);
	}

	@Test
	public void insertTenCentsCoin() {
		cashManager.add(Coin.TEN_CENTS);
		assertEquals(Coin.TEN_CENTS.getValue(), cashManager.getCurrentMoney(), 0);
	}

	@Test
	public void insertTwentyCentsCoin() {
		cashManager.add(Coin.TWENTY_CENTS);
		assertEquals(Coin.TWENTY_CENTS.getValue(), cashManager.getCurrentMoney(), 0);
	}

	@Test
	public void insertTwoEuroCoin() {
		cashManager.add(Coin.TWO_EURO);
		assertEquals(Coin.TWO_EURO.getValue(), cashManager.getCurrentMoney(), 0);
	}

	@Test
	public void testRefill() {
		List<Coin> coins = new ArrayList<>();
		coins.add(Coin.TWO_EURO);
		coins.add(Coin.ONE_EURO);
		coins.add(Coin.FIFTY_CENTS);
		coins.add(Coin.TWENTY_CENTS);
		coins.add(Coin.TEN_CENTS);
		coins.add(Coin.FIVE_CENTS);
		cashManager.refill(coins);
		assertEquals(385, cashManager.getCurrentMoney(), 0);
	}
}
