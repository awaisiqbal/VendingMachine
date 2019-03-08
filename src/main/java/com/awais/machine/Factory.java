package com.awais.machine;

import com.awais.machine.interfaces.CashManager;
import com.awais.machine.interfaces.VendingMachine;
import com.awais.machine.interfaces.impl.CashManagerImpl;
import com.awais.machine.interfaces.impl.ClassicStrategy;
import com.awais.machine.interfaces.impl.VendingMachineImpl;

/**
 * Factory to create different instances of the machine.
 * 
 * @author Awais Iqbal
 *
 */
public class Factory {

	/**
	 * Private constructor
	 */
	private Factory() {

	}

	/**
	 * This method creates a new instance of the vending machine.
	 * 
	 * @return A new instance of a vending machine
	 */
	public static final VendingMachine createVendingMachine() {
		CashManager cashManager = Factory.createCashManager();
		return new VendingMachineImpl(cashManager);
	}

	/**
	 * This method creates a new instance of the Cash Manager.
	 * 
	 * @return A new instance of a cash manager
	 */
	public static final CashManager createCashManager() {
		return new CashManagerImpl(new ClassicStrategy());
	}

}
