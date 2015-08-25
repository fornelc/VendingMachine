package com.tescobank.vendingmachine.actions;

import static org.junit.Assert.assertEquals;
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
import com.tescobank.vendingmachine.actions.ManageMachine;
import com.tescobank.vendingmachine.exceptions.UserException;
import com.tescobank.vendingmachine.model.Coin;
import com.tescobank.vendingmachine.model.Item;

public class ManageMachineTest {

	private static List<Coin> coins = null;
	
	private VendingMachine machine = null;
	
	private Action action = null;
	

	@Before
	public void init() {
		machine = new VendingMachine();
		
		coins = new ArrayList<Coin>();
		coins.add(Coin.Pence20);
		machine.setMoneyCoins(coins);
		
		machine.setItem(Item.B);
		
		action = new ManageMachine(machine);
		machine.setAction(action);
	}

	@Test(expected = UserException.class)
	public void testMachineIsOn() throws UserException {
		assertTrue(machine.getAction() instanceof ManageMachine);
		action.machineIsOn();
	}

	@Test
	public void testSelectItem() throws UserException {
		Item B = action.selectedItem();
		assertTrue(machine.getAction() instanceof AddingCoins);
		assertEquals(machine.getItem(), B);
	}

	@Test(expected = UserException.class)
	public void testCancelingSelection() throws UserException {
		assertTrue(machine.getAction() instanceof ManageMachine);
		action.cancelSelectedItem();
	}
	
	@Test
	public void testMachineIsOff() throws UserException {
		action.machineIsOff();
		
		assertTrue(machine.getAction() instanceof MachineOff);
		
		assertFalse(machine.getAction() instanceof ManageMachine);
		
	}
}
