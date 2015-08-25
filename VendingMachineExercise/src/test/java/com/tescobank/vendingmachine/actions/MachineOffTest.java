package com.tescobank.vendingmachine.actions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tescobank.vendingmachine.VendingMachine;
import com.tescobank.vendingmachine.actions.Action;
import com.tescobank.vendingmachine.actions.MachineOff;
import com.tescobank.vendingmachine.actions.ManageMachine;
import com.tescobank.vendingmachine.exceptions.UserException;
import com.tescobank.vendingmachine.model.Coin;

public class MachineOffTest {

	private static List<Coin> coins = null;
	
	
	private VendingMachine machine = null;
	
	
	private Action action = null;
	

	@Before
	public void init() {
		machine = new VendingMachine();
		
		
		coins = new ArrayList<Coin>();
		coins.add(Coin.Pound1);
		
		
		machine.setMoneyCoins(coins);
		
		action = new MachineOff(machine);
		machine.setAction(action);
	}

	@Test(expected = UserException.class)
	public void testCancelingSelection() throws UserException {
		assertTrue(machine.getAction() instanceof MachineOff);
		action.cancelSelectedItem();
	}
	
	@Test(expected = UserException.class)
	public void testSelectedItem() throws UserException {
		assertTrue(machine.getAction() instanceof MachineOff);
		action.selectedItem();
	}

	
	@Test(expected = UserException.class)
	public void testMachineIsOff() throws UserException {
		action.machineIsOff();
	}
	
	@Test
	public void testMachineIsOffAction() {
		assertTrue(machine.getAction() instanceof MachineOff);
	}
	
	@Test
	public void testMachineIsOn() throws UserException {
		action.machineIsOn();
		assertFalse(machine.getAction() instanceof MachineOff);
		assertTrue(machine.getAction() instanceof ManageMachine);
		
	}
}
