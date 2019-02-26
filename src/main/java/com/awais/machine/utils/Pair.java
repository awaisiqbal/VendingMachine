package com.awais.machine.utils;

/**
 * This class represent a pair of objects
 * 
 * @author Awais Iqbal
 *
 * @param <T> First item of the pair
 * @param <U> Second item of the pair
 */
public class Pair<T, U> {

	/**
	 * First item of the pair
	 */
	private T firstItem;

	/**
	 * Second item of the pair
	 */
	private U secondItem;

	public Pair(T firstItem, U secondItem) {
		this.firstItem = firstItem;
		this.secondItem = secondItem;
	}

	public T getFirstItem() {
		return firstItem;
	}

	public void setFirstItem(T firstItem) {
		this.firstItem = firstItem;
	}

	public U getSecondItem() {
		return secondItem;
	}

	public void setSecondItem(U secondItem) {
		this.secondItem = secondItem;
	}

}
