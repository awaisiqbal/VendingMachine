package com.awais.machine.exceptions;

public class SoldOutException extends Exception {

	private static final long serialVersionUID = 739287744743227470L;

	public SoldOutException(String message) {
		super(message);
	}

}
