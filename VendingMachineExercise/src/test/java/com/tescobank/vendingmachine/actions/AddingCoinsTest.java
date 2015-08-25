package com.tescobank.vendingmachine.actions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tescobank.vendingmachine.VendingMachine;
import com.tescobank.vendingmachine.actions.Action;
import com.tescobank.vendingmachine.actions.AddingCoins;
import com.tescobank.vendingmachine.actions.MachineOff;
import com.tescobank.vendingmachine.exceptions.CoinException;
import com.tescobank.vendingmachine.exceptions.UserException;
import com.tescobank.vendingmachine.model.Coin;
import com.tescobank.vendingmachine.model.Item;


/**
 * 
 * Testing Adding coins
 * 
 * @author Javier Fornells
 *
 */

public class AddingCoinsTest {

	private static List<Coin> coins = null;
	
	private VendingMachine machine = null;
	
	private Action action = null;
	

	@Before
	public void init() {
		machine = new VendingMachine();
		
		machine.setItem(Item.B);
		
		coins = new ArrayList<Coin>();
		coins.add(Coin.Pence20);
		this.machine.setMoneyCoins(coins);
		
		machine.setAction(action);
		action = new AddingCoins(machine);
	}

	public void testSelectProduct() throws UserException {
		assertTrue(machine.getItem().equals(Item.A));
	}

	/*
	 * To buy product B costing 1.00 with 0.20  
	 */
	@Test
	public void testEnterAmountForSelectedProduct()
			throws UserException, CoinException {
		boolean willDispatch = action.dispatchItem();
		assertFalse(willDispatch);
		
		//add the rest of the coins, now the result must be true
		coins.add(Coin.Pence10);
		coins.add(Coin.Pence20);
		coins.add(Coin.Pence50);
		willDispatch = action.dispatchItem();
		assertTrue(willDispatch);
	}
	
	@Test
	public void testMachineIsOff() throws UserException {
		action.machineIsOff();
		assertTrue(machine.getAction() instanceof MachineOff);
	}
	
	@Test(expected = UserException.class)
	public void testMachineIsOn() throws UserException {
		action.machineIsOn();
	}

}
