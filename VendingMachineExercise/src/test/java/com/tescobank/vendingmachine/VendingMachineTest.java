package com.tescobank.vendingmachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tescobank.vendingmachine.actions.Action;
import com.tescobank.vendingmachine.actions.AddingCoins;
import com.tescobank.vendingmachine.exceptions.CoinException;
import com.tescobank.vendingmachine.exceptions.UserException;
import com.tescobank.vendingmachine.model.Coin;
import com.tescobank.vendingmachine.model.Item;
import com.tescobank.vendingmachine.model.ManagingItems;

/**
 * Vending machine tests
 * 
 * @author Javier Fornells
 *
 */
public class VendingMachineTest {
	
	Coin coin = Coin.Pound1;
	Item item = Item.B;
	ManagingItems listItems = ManagingItems.getItems();
	
	
	/**
	 * Check if the coin entered is correct
	 * 
	 * @throws CoinException
	 */
	@Test(expected = CoinException.class)
	public void noValidCoin() throws CoinException {
		Coin.getValidCoin("2.00");
	}

	/**
	 * Now we check if the coin is valid
	 * 
	 * @throws CoinException
	 */
	@Test
	public void validCoin() throws CoinException {
		Coin.getValidCoin("0.10");
	}

	
	@Test
	public void insertCoinAndCheckChange() throws UserException, CoinException {
		VendingMachine returnChange = new VendingMachine();
		
		Action newState = new AddingCoins(returnChange);

		returnChange.setItem(Item.C);


		List<Coin> newCoins = new ArrayList<Coin>();
		//3.30 pounds
		newCoins.add(Coin.Pound1);
		newCoins.add(Coin.Pence50);
		newCoins.add(Coin.Pence50);
		newCoins.add(Coin.Pence10);
		returnChange.setMoneyCoins(newCoins);

		boolean willDispatch = newState.dispatchItem();
		assertTrue(willDispatch);

	}

	@Test
	public void deleteItem() {
		listItems.deleteItem(Item.C);

		List<Item> list = listItems.getListItems();
		assertEquals(3, list.size());
		assertFalse(list.contains(Item.C));
	}

	@Test
	public void testObtainQuantity() {
		assertTrue(Item.A.getQuantity().equals(new BigDecimal("0.60")));
	}
}
