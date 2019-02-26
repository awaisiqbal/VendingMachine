package com.awais.machine.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a inventory object
 * 
 * @author Awais Iqbal
 *
 * @param <T>
 */
public class Stock<T> {
	private Map<T, Integer> currentStock = new HashMap<>();

	/**
	 * Add a instance of the item by 1
	 * 
	 * @param item
	 */
	public void add(T item) {
		int count = currentStock.get(item);
		currentStock.put(item, count + 1);
	}

	/**
	 * Reduce the number of item by -1, of the given item
	 * 
	 * @param item Item which should be reduced by 1
	 */
	public void removeItem(T item) {
		int countInt = currentStock.get(item);
		if (countInt != 0) {
			currentStock.put(item, countInt - 1);
		}
	}

	/**
	 * THis method removes all the items given by the parameter
	 * 
	 * @param items Items to be removed
	 */
	public void removeItems(Collection<T> items) {
		items.stream().forEach(this::removeItem);
	}

	/**
	 * Count the existences of the given key
	 * 
	 * @param key
	 * @return number of instances
	 */
	public int getCount(T key) {
		Integer count = currentStock.get(key);
		return count == null ? 0 : count;
	}

	/**
	 * This method check if the given key has one or more counts in this stock
	 * 
	 * @param key
	 * @return True if this inventory has one or more item, otherwise return false
	 */
	public boolean containsMoreThenOne(T key) {
		return getCount(key) >= 1;
	}

	/**
	 * This method is used to initialize the count of the given item
	 * 
	 * @param item
	 */
	public void initializeKey(T item) {
		currentStock.put(item, 0);
	}

	/**
	 * This method refill all the collection given by the parameter
	 * 
	 * @param collection Items to be added
	 */
	public void refill(Collection<T> collection) {
		collection.stream().forEach(this::add);
	}

	/**
	 * Reset all the items in the stock with 0 existences
	 */
	public void reset() {
		Set<T> keys = currentStock.keySet();
		currentStock = new HashMap<>();
		keys.stream().forEach(key -> currentStock.put(key, 0));
	}

	/**
	 * Get all the items in the stock
	 * 
	 * @return All the items in stock
	 */
	public Map<T, Integer> getCurrentStockItems() {
		return currentStock;
	}

}
