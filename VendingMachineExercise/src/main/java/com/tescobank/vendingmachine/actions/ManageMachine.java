package com.tescobank.vendingmachine.actions;

import java.util.logging.Logger;

import com.tescobank.vendingmachine.VendingMachine;
import com.tescobank.vendingmachine.exceptions.CoinException;
import com.tescobank.vendingmachine.exceptions.UserException;
import com.tescobank.vendingmachine.model.Item;

/**
 * In this class we manage if the machine is not turned on, then the user can't select or dispatch an item
 * 
 * @author Javier Fornells
 *
 */
public class ManageMachine implements Action {

	private final static Logger log = Logger.getLogger(ManageMachine.class.getName());

	private VendingMachine machine;

	public ManageMachine(VendingMachine machine) {
		this.machine = machine;
	}

	/**
	 * to control if the user doesn't select any item
	 */
	@Override
	public void cancelSelectedItem() throws UserException {
		throw new UserException("First select a product!!");
	}
	
	/**
	 * Turning off machine
	 */
	@Override
	public void machineIsOff() throws UserException {
		log.warning("Machine turning off!!");
		this.machine.setAction(new MachineOff(machine));
	}

	/**
	 * machine already switched on
	 */
	@Override
	public void machineIsOn() throws UserException {
		throw new UserException("Machine is already switched on!");
	}

	/**
	 * get the selected item
	 */
	@Override
	public Item selectedItem() throws UserException {
		this.machine.setAction(new AddingCoins(machine));
		
		this.machine.setItem(this.machine.getItem());
		
		return this.machine.getItem();
	}

	@Override
	public boolean dispatchItem() throws CoinException{
		throw new CoinException("Select firs a product, your coins will be returned.");
	}

}
