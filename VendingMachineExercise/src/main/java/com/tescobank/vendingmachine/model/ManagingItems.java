package com.tescobank.vendingmachine.model;

import java.util.ArrayList;
import java.util.List;

public class ManagingItems {

	/**
	 * To manage items
	 * 
	 * @author Javier Fornells
	 */
	
	private ManagingItems() {
	}

	private static final ManagingItems items = new ManagingItems();
	private static List<Item> listItems = new ArrayList<Item>();
	// listing 6 items
	static {
		listItems.add(Item.A);
		listItems.add(Item.A);
		listItems.add(Item.B);
		listItems.add(Item.B);
		listItems.add(Item.C);
		listItems.add(Item.C);
	}
	
	public static ManagingItems getItems() {
		return items;
	}

	public List<Item> getListItems() {
		return listItems;
	}
	
	public void deleteItem(Item p) {
		listItems.remove(p);
	}

	
}
